<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 27.11.2019
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <c:if test="${empty employee.surname}">
        <title>Добавить сотрудника</title>
    </c:if>
    <c:if test="${!empty employee.surname}">
        <title>Редактировать сотрудника</title>
    </c:if>
</head>
<body>
<c:if test="${!empty employee.id}">
    <c:url value="/employees/edit" var="var"/>
</c:if>
<c:if test="${empty employee.id}">
    <c:url value="/employees/add" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty employee.id}">
        <input type="hidden" name="id" value="${employee.id}">
    </c:if>
    <p><label for="surname">Фамилия</label>
        <input type="text" name="surname" id="surname" value="${employee.surname}"></p>
    <p><label for="name">Имя</label>
        <input type="text" name="name" id="name" value ="${employee.name}"></p>
    <p><label for="patronymic">Отчество</label>
        <input type="text" name="patronymic" id="patronymic" value ="${employee.patronymic}"></p>
    <p><label for="department">Подразделение</label>
        <input type="text" name="department" id="department" value="${employee.department}"></p>
    <p><label for="position">Должность</label>
        <input type="text" name="position" id="position" value="${employee.position}"></p>
    <p><label for="address">Адрес</label>
        <input type="text" name="address" id="address" value="${employee.address}"></p>
    <p><label for="telephonenumber">Контактный номер</label>
        <input type="text" name="telephonenumber" id="telephonenumber" value="${employee.telephonenumber}"></p>
    <p><label for="dateofreceipt">Дата приема</label>
        <input type="date" name="dateofreceipt" id="dateofreceipt" value="${employee.dateofreceipt}"></p>
    <p><label for="dateofdismissal">Дата увольнения</label>
        <input type="date" name="dateofdismissal" id="dateofdismissal" value="${employee.dateofdismissal}"></p>

    <c:if test="${empty employee.surname}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty employee.surname}">
        <p> <input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
