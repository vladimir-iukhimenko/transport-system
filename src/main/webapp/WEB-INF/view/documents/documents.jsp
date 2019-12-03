<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 18.11.2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <c:set var = "inst" value = "${type}"/>
    <c:if test="${fn:contains(inst,'DocumentTransport')}">
        <title>Транспортные документы</title>
    </c:if>
    <c:if test="${fn:contains(inst,'DocumentEmployee')}">
        <title>Документы сотрудников</title>
    </c:if>
</head>
<body>
<h2>Документ <c:if test="${!empty employee.id}">
    ${employee.name} ${employee.surname}
            </c:if>
</h2>

<table>
    <tr>
        <th>Тип документа</th>
        <th>Серия</th>
        <th>Номер</th>
        <th>Выпущено</th>
        <th>Дата выпуска</th>
        <c:if test="${fn:contains(inst,'DocumentTransport')}">
        <th>Срок действия</th>
        </c:if>
        <c:if test="${fn:contains(inst,'DocumentEmployee')}">
        <th>Сотрудник</th>
        </c:if>
    </tr>
    <c:forEach var="document" items="${documents}">
        <tr>
            <td>${document.documenttype}</td>
            <td>${document.series}</td>
            <td>${document.number}</td>
            <td>${document.issuedby}</td>
            <td>${document.issueddate}</td>
            <c:if test="${fn:contains(inst,'DocumentTransport')}">
                <td>${document.expiredate}</td>
            </c:if>
            <c:if test="${fn:contains(inst,'DocumentEmployee')}">
                <td>${document.employee.name} ${document.employee.surname}</td>
            </c:if>
            <td>
                <c:if test="${fn:contains(inst,'DocumentTransport')}">
                    <a href="/transportdocs/edit/${document.id}">edit</a>
                    <a href="/transportdocs/delete/${document.id}">delete</a>
                </c:if>
                <c:if test="${fn:contains(inst,'DocumentEmployee')}">
                    <a href="/employeedocs/edit/${document.id}">edit</a>
                    <a href="/employeedocs/delete/${document.id}">delete</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${fn:contains(inst,'DocumentTransport')}">
    <c:url value="/transportdocs/add" var="add"/>
</c:if>
<c:if test="${fn:contains(inst,'DocumentEmployee')}">
    <c:url value="/employeedocs/add" var="add"/>
</c:if>
<a href="${add}">Добавить</a>
<p><a href="/">На главную</a></p>