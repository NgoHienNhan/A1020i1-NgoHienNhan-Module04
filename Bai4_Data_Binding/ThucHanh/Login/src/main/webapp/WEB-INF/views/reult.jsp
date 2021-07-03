<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/13/2021
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Infor user</h1>
<table border="1">
    <tr>
        <th>Account</th>
        <th>Name</th>
        <th>Email</th>
        <th>Age</th>
    </tr>
    <tr>
        <td>${user.account}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.age}</td>
    </tr>
</table>
</body>
</html>
