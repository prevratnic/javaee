package client;

import org.jetbrains.annotations.NotNull;
import sevrice.RMIService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 16.11.12
 * Time: 15:53
 */
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initData(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initData(request, response);
    }

    void initData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RMIService rmiService = (RMIService) request.getServletContext().getAttribute("RMI_SERVICE");

        if(request.getSession(false) == null){
            response.sendRedirect("login");
            return;
        }

        if(request.getParameter("sendLogoff") != null){
            rmiService.logout(request, response);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.print("<html>");
        out.print("<head>");
        out.print("<title>Exchange rate</title>");
        out.print("</head>");
        out.print("<body>");

        String[][] result = parseHistory(rmiService.getHistory(10, request));

        out.print("<table border='1'>");

        for(int i = 0; i < result.length; i++){
            out.print("<tr>");
            for(int j = 0; j < result[i].length; j++){
                out.print("<td>");
                out.print(result[i][j]);
                out.print("</td>");
            }
            out.print("</tr>");
        }

        out.print("</table>");

        out.print("<form method='post'>");
        out.print("<input type='submit' name='sendLogoff' value='Logoff' />");
        out.print("</form>");

        out.print("</body>");
        out.print("</html>");
    }

    private String[][] parseHistory(@NotNull String[] arg){
        String[][] tmp = new String[arg.length][5];

        for(int i = 0; i < arg.length; i++){
            tmp[i] = parseLine(arg[i]);
        }

        return tmp;
    }

    private String[] parseLine(@NotNull String input){
        return input.trim().split("\\s+");
    }

}
