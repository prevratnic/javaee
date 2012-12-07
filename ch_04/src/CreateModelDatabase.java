
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 29.10.12
 * Time: 18:55
 */

public class CreateModelDatabase extends HttpServlet {

    private List<Users> userList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initDatabase(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initDatabase(req, resp);
    }

    private void initDatabase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        if(createModel()){
            out.println("<h1>Модель создана</h1>");

            if(insertData()){
                out.println("<h2>Данные загружены</h2>");
            }else{
                out.println("<h2>Ошибки загрузки данных</h2>");
            }
        }else{
            out.println("<h1>Ошибки в создание модели</h1>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    private boolean createModel(){

        Connection connection = ConnectionManager.getConnection();
        BufferedReader bufferedReader = null;

        try {

            String path = loadProperties().getProperty("sqlModelPath");
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null){
                builder.append(str);
            }

            String[] result = builder.toString().split(";");

            Statement statement = connection.createStatement();

            for(int i = 0; i < result.length; i++){
                statement.executeUpdate(result[i]);
            }

            connection.commit();

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null) closeIO(bufferedReader);
            ConnectionManager.close(connection);
        }
        return false;
    }

    private boolean insertData(){

        parseFileData();

        String sqlUsers = "insert into users(id, login) values(?, ?)";
        String sqlRegistration = "insert into registration(code, id, role, date1) values(?, ?, ?, ?)";

        Connection connection = ConnectionManager.getConnection();
        int userId = 0;
        int regId = 0;

        try {
            for(Users user: userList){
                userId += 100;

                PreparedStatement statementUser = connection.prepareStatement(sqlUsers);
                statementUser.setInt(1, userId);
                statementUser.setString(2, user.getLogin());

                statementUser.executeUpdate();

                for(Registration reg : user.getRegistrationList()){
                    regId += 10;

                    PreparedStatement statementReg = connection.prepareStatement(sqlRegistration);
                    statementReg.setInt(1, regId);
                    statementReg.setInt(2, userId);
                    statementReg.setString(3, reg.getRole());
                    statementReg.setTimestamp(4, reg.getDataTime());

                    statementReg.executeUpdate();
                }

            }

            connection.commit();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.close(connection);
        }

        return false;
    }

    private void parseFileData(){

        String path = loadProperties().getProperty("sqlInsertPath");
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));

            int count = 0;
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null){
                if(count > 0){
                    addToList(parseLine(tmp));
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                closeIO(bufferedReader);
            }
        }

    }

    private Properties loadProperties(){
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(getInitParameter("sqlFilePath"));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                closeIO(inputStream);
            }
        }
        return properties;
    }

    private String[] parseLine(@NotNull String input){
        return input.trim().split("\\s+");
    }

    private void addToList(@NotNull String[] str){
        Users users = null;
        try {
            if(userList == null){
                userList = new ArrayList<Users>();
            }

            if(userList.size() > 0){
                for(Users appUser: userList){
                    if(appUser.getLogin().equals(str[0])){
                        appUser.addRegistrationData(str[1], parseDate(str[2] + " " + str[3]));
                        return;
                    }
                }
            }

            users = new Users(str[0]);
            users.addRegistrationData(str[1], parseDate(str[2] + " " + str[3]));

            userList.add(users);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Timestamp parseDate(@NotNull String str) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return new Timestamp(dateFormat.parse(str).getTime());
    }

    private void closeIO(@NotNull Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
