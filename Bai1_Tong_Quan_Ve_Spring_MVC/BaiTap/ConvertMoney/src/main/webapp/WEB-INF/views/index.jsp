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
    <label for="input">Nhập tiền cần chuyển</label>
    <input type="number" id="input" name="money"><br><br>
    <input type="submit" value="Chuyển">
</form>
<label >Kết quả: ${result}</label>
</body>
</html>
