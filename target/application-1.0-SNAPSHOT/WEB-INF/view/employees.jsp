<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 27.11.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>

<h2>Сотрудники</h2>
<table>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Подразделение</th>
        <th>Должность</th>
        <th>Адрес</th>
        <th>Номер телефона</th>
        <th>Дата приема</th>
        <th>Дата увольнения</th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.surname}</td>
            <td>${employee.name}</td>
            <td>${employee.patronymic}</td>
            <td>${employee.department}</td>
            <td>${employee.position}</td>
            <td>${employee.address}</td>
            <td>${employee.telephonenumber}</td>
            <td>${employee.dateofreceipt}</td>
            <td>${employee.dateofdismissal}</td>
            <td>
                <a href="/employees/edit/${employee.id}">Редактировать</a>
                <a href="/employees/delete/${employee.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/employees/add">Добавить</a>
<p><a href="/">На главную</a></p>
