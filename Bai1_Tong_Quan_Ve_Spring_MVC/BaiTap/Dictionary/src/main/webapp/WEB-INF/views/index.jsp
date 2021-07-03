<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/8/2021
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/convert" method="get">
    <label for="input">Nhập từ vựng cần tìm</label>
    <input type="text" id="input" name="word"><br><br>
    <input type="submit" value="Tìm kiếm">
</form>
<label >Kết quả: ${result}</label>
</body>
</html>
