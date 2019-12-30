<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Двигатели</title>
</head>
<body>

<h2>Двигатели</h2>
<table>
    <tr>
        <th>Наименование</th>
        <th>Объем</th>
        <th>Топливо</th>
        <th>Модели автомобиля с данным двигаталем</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="engine" items="${engines}">
        <tr>
            <td>${engine.name}</td>
            <td>${engine.volume}</td>
            <td>${engine.fuel}</td>
            <td>
                <a href="/boundedtransportmodels/${engine.id}">Показать</a>
            </td>
            <td>
                <a href="/engines/edit/${engine.id}">Редактировать</a>
                <a href="/engines/delete/${engine.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>


<p><a href="/engines/add">Добавить двигатель</a></p>
<p><a href="/">На главную</a></p>