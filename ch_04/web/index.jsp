<%--
    Author: Ilya Varlamov aka privr@tnik
    Date: 26.10.12
    Time: 12:54
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>

  <form action="load" method="post">
  <table border="1">
      <tr>
          <td colspan="2" style="text-align: center"><h1>Данные</h1></td>
      </tr>
      <tr>
          <td><input type="submit" name="load_submit" value="Создать таблицы и загрузить данные"></td>
      </tr>
  </table>
  </form>

  <form action="result" method="post">
      <table border="1">
          <tr>
              <td colspan="2" style="text-align: center"><h1>Работа с данными</h1></td>
          </tr>
          <tr>
              <td><input type="submit" name="data_submit" value="Поехали"></td>
          </tr>
      </table>
  </form>

  </body>
</html>