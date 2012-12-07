package client;

import org.jetbrains.annotations.NotNull;
import sevrice.RMIService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 15.11.12
 * Time: 16:15
 */

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginForm(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginForm(request, response);
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RMIService rmiService = (RMIService) request.getServletContext().getAttribute("RMI_SERVICE");

        String authError = null;

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out = response.getWriter();

        String userName = request.getParameter("userName");
        String userPasswd = request.getParameter("userPasswd");

        if(userName != null && userPasswd != null){
            if(rmiService.login(userName, userPasswd, request)){
                response.sendRedirect("result");
            }else{
                authError = "Error";
            }
        }

        out.print("<html>");
        out.print("<head>");
        out.print("<title>Exchange rate</title>");
        out.print("</head>");
        out.print("<body>");

        out.print("<form method='post'>");
        out.print("<table border='1'>");

        out.print("<tr>" +
                "<td><label for='user'>User Name:</label></td>" +
                "<td><input type='text' value='' name='userName' id='user' /></td>" +
                "</tr>");

        out.print("<tr>" +
                "<td><label for='passwd'>Password:</label></td>" +
                "<td><input type='password' value='' name='userPasswd' id='passwd' /></td>" +
                "</tr>");

        out.print("<tr>" +
                "<td colspan='2'><input type='submit' name='sendName' value='Send' /></td>" +
                "</tr>");

        out.print("</table>");

        if(authError!=null){
            out.print(authError);
        }

        out.print("</form>");
        out.print("</body>");
        out.print("</html>");

    }

}
