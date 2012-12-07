<%@ page import="java.rmi.NotBoundException" %>
<%@ page import="java.rmi.RemoteException" %>
<%@ page import="rmi.History" %>
<%@ page import="java.rmi.registry.LocateRegistry" %>
<%@ page import="java.rmi.registry.Registry" %>
<%--
  Author: Ilya Varlamov aka privr@tnik
  Date: 16.11.12
  Time: 12:10
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
    try {

        String userName = request.getParameter("userName");
        String userPasswd = request.getParameter("userPasswd");

        Registry registry = LocateRegistry.getRegistry();
        History skeleton = (History) registry.lookup("history");

        if(userName != null && userPasswd != null){
            out.print(skeleton.login(userName, userPasswd));
            String[] tmp = skeleton.getHistory(10);

            for(int i = 0; i < tmp.length; i++){
                out.println("<br/>");
                out.println(tmp[i]);
            }
        }

    } catch (RemoteException e) {
        e.printStackTrace();
    } catch (NotBoundException e) {
        e.printStackTrace();
    }
%>


    <form action="index1.jsp" method="get" name="history">
        <select >
            <option value="">sjhfkshf</option>
        </select>
        <input type="submit" name="sendCode" value="Code" />
    </form>

</body>
</html>