import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 29.10.12
 * Time: 0:07
 */

public class ResultQuery extends HttpServlet {

    private String sql1 = "select u.login, r.role, to_char(r.date1, 'DD.MM.YYYY HH24:MI:SS') from users u, registration r " +
                          "where u.id = r.id and r.date1 >= to_date('01.01.2012', 'DD.MM.YYYY')";

    private String sql2 = "select t1.login, t1.role, to_char(t1.date1, 'DD.MM.YYYY HH24:MI:SS') from" +
                          "  (select u.login, r.role, r.date1 from users u, registration r where u.id = r.id) t1, " +
                          "  (select u.login, r.role, r.date1 from users u, registration r where u.id = r.id) t2 " +
                          "where t1.login = t2.login and t1.role <> t2.role";

    private String sql3 = "select t1.login, t1.role, to_char(t1.date1, 'DD.MM.YYYY HH24:MI:SS') from" +
                          "  (select u.login, r.role, r.date1 from users u, registration r where u.id = r.id) t1, " +
                          "  (select u.login, r.role, r.date1 from users u, registration r where u.id = r.id) t2 " +
                          "where t1.login <> t2.login and t1.date1 = t2.date1";

    private String sql4 = "select u.login, r.role, to_char(r.date1, 'DD.MM.YYYY HH24:MI:SS') from users u, registration r " +
                          "where u.id = r.id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dialogForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dialogForm(req, resp);
    }

    private void dialogForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Выбрать всех пользователей, которые регистрировались (входили в систему) не ранее заданной даты</h3>");
        searchQuery(out, sql1);
        out.println("<br/>");

        out.println("<h3>Выбрать всех пользователей, которые регистрировались с разными ролями</h3>");
        searchQuery(out, sql2);
        out.println("<br/>");

        out.println("<h3>Выбрать все даты, в которые регистрировалось не менее двух пользователей</h3>");
        searchQuery(out, sql3);
        out.println("<br/>");

        out.println("<h3>Выбрать все регистрационные записи</h3>");
        searchQuery(out, sql4);
        out.println("<br/>");

        out.println("</body>");
        out.println("<html>");
        out.println("</html>");

    }

    private void searchQuery(PrintWriter out, String sql) {

        Connection connection = ConnectionManager.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            out.println("<table border='1'>");

            while (resultSet.next()){
                out.println("<tr>");
                out.println("<td>" + resultSet.getString(1) + "</td>");
                out.println("<td>" + resultSet.getString(2) + "</td>");
                out.println("<td>" + resultSet.getString(3) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.close(connection);
        }
    }

}
