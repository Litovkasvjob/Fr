<%--
  Created by IntelliJ IDEA.
  User: Serg
  Date: 23.08.2017
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write Data</title>
</head>
<body>
<form action="home" method="post">
    <table>
        <tr>
            <td>Введите номер COM порта</td>
            <td><input type="text" name="com"></td>
        </tr>
        <tr>
            <td>Введите имя файла</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr class="radio">
            <td><input type="radio" name="option" value="online" >ONLINE</td>
        </tr>
        <tr class="radio">
            <td><input type="radio" name="option" value="offline" checked>OFFLINE</td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="SEND">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
