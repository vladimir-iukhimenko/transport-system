<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 18.11.2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Транспортные документы</title>
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
    <c:forEach var="transportdocument" items="${transportdocuments}">
        <tr>
            <td>${transportdocument.documenttype}</td>
            <td>${transportdocument.series}</td>
            <td>${transportdocument.number}</td>
            <td>${transportdocument.issuedby}</td>
            <td>${transportdocument.issueddate}</td>
            <td>
                <a href="/edit/${transportdocument.id}">edit</a>
                <a href="/delete/${transportdocument.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:url value="/addtransportdoc" var="add"/>
<a href="${add}">Добавить</a>