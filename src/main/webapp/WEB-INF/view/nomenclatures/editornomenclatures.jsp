<%--
  Created by IntelliJ IDEA.
  User: Владимир
  Date: 10.11.2019
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty nomenclature.id}">
        <title>Добавить номенклатуру</title>
    </c:if>
    <c:if test="${!empty nomenclature.id}">
        <title>Редактировать номенклатуру</title>
    </c:if>
</head>
<body>
<c:if test="${empty nomenclature.id}">
    <c:url value="/nomenclatures/add" var="var"/>
</c:if>
<c:if test="${!empty nomenclature.id}">
    <c:url value="/nomenclatures/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty nomenclature.id}">
        <input type="hidden" name="id" value="${nomenclature.id}">
    </c:if>
    <p> <label for="number">Номер</label>
        <input type="text" name="number" id="number" value="${nomenclature.number}"></p>
    <p><label for="length">Длина</label>
        <input type="text" name="length" id="length" value ="${nomenclature.length}"></p>
    <p><label for="width">Ширина</label>
        <input type="text" name="width" id="width" value ="${nomenclature.width}"></p>
    <p><label for="height">Высота</label>
        <input type="text" name="height" id="height" value ="${nomenclature.height}"></p>
    <p><label for="weight">Вес</label>
        <input type="text" name="weight" id="weight" value ="${nomenclature.weight}"></p>
    <p><label for="comment">Комментарий</label>
        <input type="text" name="comment" id="comment" value ="${nomenclature.comment}"></p>
    <c:if test="${empty nomenclature.id}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty nomenclature.id}">
        <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>

