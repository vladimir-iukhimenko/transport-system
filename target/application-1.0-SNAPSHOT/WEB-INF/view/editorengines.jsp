<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 26.11.2019
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty engine.name}">
        <title>Добавить двигатель</title>
    </c:if>
    <c:if test="${!empty engine.name}">
        <title>Редактировать двигатель</title>
    </c:if>
</head>
<body>
<c:if test="${empty engine.name}">
    <c:url value="/addengine" var="var"/>
</c:if>
<c:if test="${!empty engine.name}">
    <c:url value="/editengine" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty engine.name}">
        <input type="hidden" name="id" value="${engine.id}">
    </c:if>
    <p> <label for="name">Наименование</label>
        <input type="text" name="name" id="name" value="${engine.name}"></p>
    <p><label for="volume">Объем</label>
        <input type="text" name="volume" id="volume" value ="${engine.volume}"></p>
    <p><label for="fuel">Топливо</label>
        <input type="text" name="fuel" id="fuel" value ="${engine.fuel}"></p>
    <c:if test="${empty engine.name}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty engine.name}">
        <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
