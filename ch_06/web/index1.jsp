<%--
  Author: Ilya Varlamov aka privr@tnik
  Date: 15.11.12
  Time: 16:26
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exchange rate</title>
</head>
<body>


    <form action="index1.jsp" method="get" name="auth">
        <table border="1">
            <tr>
                <td><label for="user">User Name:</label></td>
                <td><input type="text" value="" name="userName" id="user" /></td>
            </tr>
            <tr>
                <td><label for="passwd">Password:</label></td>
                <td><input type="password" value="" name="userPasswd" id="passwd" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="sendName" value="Send" /></td>
            </tr>
        </table>
    </form>

</body>
</html>