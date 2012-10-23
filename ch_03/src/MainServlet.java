import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 17.10.12
 * Time: 3:31
 */

public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        form(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        form(req, resp);
    }

    private void form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        try{

            String text = parserValue(req.getParameter("field_test"));

            out.append("<html>");
            out.append("<head>");
            out.append("<title>My Servlet</title>");
            out.append("</head>");
            out.append("<body>");
            out.append("<h1>" + text + "</h1>");
            out.append("</body>");
            out.append("</html>");

        }finally {
            out.close();
        }
    }

    private String parserValue (@NotNull String str){

        String result;

        try{
            result = String.valueOf(Integer.parseInt(str) * 2);

        }catch (NumberFormatException e){
            result = str.trim();
        }

        return result;
    }
}
