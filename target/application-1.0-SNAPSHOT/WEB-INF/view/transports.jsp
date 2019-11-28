<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ТРАНСПОРТ</title>
</head>
<body>

<h2>Транспорт</h2>
<table>
    <tr>
        <th>Номер а/м</th>
        <th>VIN</th>
        <th>Модель</th>
        <th>Год выпуска</th>
        <th>Ввод в эксплуатацию</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="transport" items="${transports}">
        <tr>
            <td>${transport.number}</td>
            <td>${transport.vin}</td>
            <td>${transport.transportmodel.name}</td>
            <td>${transport.producedyear}</td>
            <td>${transport.startupdate}</td>
            <td>
                <a href="/transport/edit/${transport.id}">Редактировать</a>
                <a href="/transport/delete/${transport.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="/transport/add">Добавить транспорт</a></p>
<p><a href="/transportmodels">Модели транспортных средств</a></p>
<p><a href="/">На главную</a></p>