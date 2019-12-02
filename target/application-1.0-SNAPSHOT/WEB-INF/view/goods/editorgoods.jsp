<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 02.12.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty goods.id}">
        <title>Добавить товар</title>
    </c:if>
    <c:if test="${!empty goods.id}">
        <title>Редактировать товар</title>
    </c:if>
</head>
<body>
<c:if test="${empty goods.id}">
    <c:url value="/goods/add" var="var"/>
</c:if>
<c:if test="${!empty goods.id}">
    <c:url value="/goods/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty goods.id}">
        <input type="hidden" name="id" value="${goods.id}">
    </c:if>
    <p> <label for="name">Наименование</label>
        <input type="text" name="name" id="name" value="${goods.name}"></p>
    <p><label for="amount">Количество</label>
        <input type="text" name="amount" id="amount" value ="${goods.amount}"></p>
    <p><label for="length">Длина</label>
        <input type="text" name="length" id="length" value ="${goods.length}"></p>
    <p><label for="height">Высота</label>
        <input type="text" name="height" id="height" value ="${goods.height}"></p>
    <p><label for="weight">Вес</label>
        <input type="text" name="weight" id="weight" value ="${goods.weight}"></p>
    <p><label for="nomenclature">Связанная номенклатура</label>
        <select id="nomenclature" name="nomenclatureid"></p>
    <c:forEach var="nomenclature" items="${nomenclatures}">
        <p><option value="${nomenclature.id}">${nomenclature.number}</option></p>
    </c:forEach>

    </select>
    <c:if test="${empty goods.id}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty goods.id}">
        <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
