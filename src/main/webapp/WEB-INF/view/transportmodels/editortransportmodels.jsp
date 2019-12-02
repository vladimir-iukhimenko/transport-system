<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 26.11.2019
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty transportmodel.name}">
        <title>Добавить модель транспорта</title>
    </c:if>
    <c:if test="${!empty transportmodel.name}">
        <title>Редактировать модель транспорта</title>
    </c:if>
</head>
<body>
<c:if test="${empty transportmodel.name}">
    <c:url value="/transportmodels/add" var="var"/>
</c:if>
<c:if test="${!empty transportmodel.name}">
    <c:url value="/transportmodels/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty transportmodel.name}">
        <input type="hidden" name="id" value="${transportmodel.id}">
    </c:if>
    <p> <label for="name">Наименование модели</label>
        <input type="text" name="name" id="name" value="${transportmodel.name}"></p>
    <p><label for="color">Цвет</label>
        <input type="text" name="color" id="color" value ="${transportmodel.color}"></p>
    <p><label for="producer">Производитель</label>
        <input type="text" name="producer" id="producer" value ="${transportmodel.producer}"></p>
    <p><label for="maxweight">Максимальный вес</label>
        <input type="text" name="maxweight" id="maxweight" value ="${transportmodel.maxweight}"></p>
    <p><label for="engineid">Двигатель</label>
    <select id="engineid" name="engineid"></p>
    <c:forEach var="engine" items="${engines}">
        <p><option value="${engine.id}">${engine.name}</option></p>
    </c:forEach>
    </select>
    <p><label for="enginepower">Мощность двигателя</label>
        <input type="text" name="enginepower" id="enginepower" value="${transportmodel.enginepower}"></p>
    <c:if test="${empty transportmodel.name}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty transportmodel.name}">
        <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
