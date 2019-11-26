<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Модели транспорта</title>
</head>
<body>

<h2>Модели транспорта</h2>
<table>
    <tr>
        <th>Наименование</th>
        <th>Цвет</th>
        <th>Производитель</th>
        <th>Максимальнвй вес</th>
        <th>Двигатель</th>
        <th>Мощность двигателя</th>
        <th>Имеющиеся автомобили в гараже</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="transportmodel" items="${transportmodels}">
        <tr>
            <td>${transportmodel.name}</td>
            <td>${transportmodel.color}</td>
            <td>${transportmodel.producer}</td>
            <td>${transportmodel.maxweight}</td>
            <td>${transportmodel.engine.name}</td>
            <td>${transportmodel.enginepower}</td>
            <td>
                <a href="/boundedtransports/${transportmodel.id}">Показать</a>
            </td>
            <td>
                <a href="/edittransportmodel/${transportmodel.id}">Редактировать</a>
                <a href="/deletetransportmodel/${transportmodel.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>


<p><a href="/addtransportmodel">Добавить модель транспорта</a></p>
<p><a href="/engines">Показать все двигатели</a></p>
<p><a href="/">На главную</a></p>