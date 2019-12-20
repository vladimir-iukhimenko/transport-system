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
        <th>Действия</th>
        <th> <form action="/search?q=${query}" method="GET">
            <input type="hidden" name="context" value="${requestScope['javax.servlet.forward.servlet_path']}">
            <input type="search" name="query" id="search">
            <input type="submit" value="Поиск">
            </form>
        </th>
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
                <a href="/employees/boundeddocuments/${employee.id}">Документы сотрудника</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p>
    <c:url value="/employeedocs" var="employeedocs"/>
    <a href="${employeedocs}">Документы сотрудников</a>
</p>
<p>
<a href="/employees/add">Добавить</a>
<p><a href="/">На главную</a></p>
