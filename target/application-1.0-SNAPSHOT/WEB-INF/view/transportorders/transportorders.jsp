<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Транспортные заказы</title>
</head>
<body>
<h2>Транспортные заказы</h2>
<table>
    <tr>
        <th>Номер заказа</th>
        <th>Дата заказа</th>
        <th>Дата исполнения</th>
        <th>Место погрузки</th>
        <th>Место разгрузки</th>
        <th>Метод погрузки</th>
        <th>Водитель</th>
        <th>Контактный номер телефона</th>
        <th>Заказчик</th>
        <th>Комментарии</th>
        <th>Действия</th>

    </tr>
    <c:forEach var="transportorder" items="${transportorders}">
        <tr>
            <td>${transportorder.ordernumber}</td>
            <td>${transportorder.orderdate}</td>
            <td>${transportorder.transportpresentingdate}</td>
            <td>${transportorder.loadingplace}</td>
            <td>${transportorder.unloadingplace}</td>
            <td>${transportorder.placemethod}</td>
            <td>${transportorder.employeeresponsible.name} ${transportorder.employeeresponsible.surname}</td>
            <td>${transportorder.telephonenumber}</td>
            <td>${transportorder.employeecustomer.name} ${transportorder.employeecustomer.surname}</td>
            <c:forEach var="comment" items="${transportorder.comments.getAllComments()}">
            <tr>
                <td>${comment}</td>
            </tr>
            </c:forEach>
            <td>
                <a href="/transportorders/edit/${transportorder.id}">Редактировать</a>
                <a href="/transportorders/delete/${transportorder.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="/transportorders/add">Добавить транспортный заказ</a></p>
<p><a href="/">На главную</a></p>