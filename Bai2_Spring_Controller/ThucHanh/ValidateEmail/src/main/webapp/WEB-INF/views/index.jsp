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

<form action="/validate" method="post">
    <label for="input">Nhập email cần validate</label>
    <input type="text" id="input" name="email"><br><br>
    <input type="submit" value="Xác nhận">
</form>
<label >Kết quả: <span style="color: red"> ${result}</span> </label>
</body>
</html>
