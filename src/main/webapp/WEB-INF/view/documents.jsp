<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 18.11.2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <c:set var = "class" value = "${documents[0]['class'].name}"/>
    <c:if test="${fn:contains(class,'DocumentTransport')}">
        <title>Транспортные документы</title>
    </c:if>
    <c:if test="${fn:contains(class,'DocumentEmployee')}">
        <title>Документы сотрудников</title>
    </c:if>
</head>
<body>

<h2>Документ</h2>
<table>
    <tr>
        <th>Тип документа</th>
        <th>Серия</th>
        <th>Номер</th>
        <th>Выпущено</th>
        <th>Дата выпуска</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="document" items="${documents}">
        <tr>
            <td>${document.documenttype}</td>
            <td>${document.series}</td>
            <td>${document.number}</td>
            <td>${document.issuedby}</td>
            <td>${document.issueddate}</td>
            <c:if test="${fn:contains(class,'DocumentTransport')}">
                <td>${document.expiredate}</td>
            </c:if>
            <c:if test="${fn:contains(class,'DocumentEmployee')}">
                <td>${document.employeeid}</td>
            </c:if>
            <td>
                <a href="/edit/${document.id}">edit</a>
                <a href="/delete/${document.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:url value="/addtransportdoc" var="add"/>
<a href="${add}">Добавить</a>