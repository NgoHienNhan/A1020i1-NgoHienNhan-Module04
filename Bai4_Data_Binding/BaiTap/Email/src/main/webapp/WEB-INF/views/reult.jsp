<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/13/2021
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Infor Email</h1>
<table border="1">
    <tr>
        <th>Language</th>
        <th>pageSize</th>
        <th>spamFilters</th>
        <th>Signature</th>
    </tr>
    <c:forEach items="${listEmail}" var="email">
        <tr>
            <td>${email.languages}</td>
            <td>${email.pageSize}</td>
            <td>${email.spamFilters}</td>
            <td>${email.signature}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
