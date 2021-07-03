<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/8/2021
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Menu Spice Sandwich</title>
</head>
<body>
<h1>MENU SPICE OF SANDWICH</h1>
<form action="/spice" method="post">
    <input type="checkbox" id="spice1" name="spice" value="Lettuce">
    <label for="spice1">Lettuce</label><br>
    <input type="checkbox" id="spice2" name="spice" value="Tomato">
    <label for="spice2">Tomato</label><br>
    <input type="checkbox" id="spice3" name="spice" value="Mustard">
    <label for="spice3"> Mustard</label><br>
    <input type="checkbox" id="spice4" name="spice" value="Sprouts">
    <label for="spice4"> Sprouts</label><br><br>
    <input type="submit" value="Submit">
</form>
<label>List spices chose: <span style="color: red">
   <c:if test="${listSpice.isEmpty()}">
       <c:out value="you need to choose at least one spice"/>
   </c:if>
    ${listSpice}
</span></label>
<label><c:out value="${msg}"/></label>
</body>
</html>
