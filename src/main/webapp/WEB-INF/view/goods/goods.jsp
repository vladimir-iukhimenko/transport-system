<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Товар</title>
</head>
<body>

<h2>Товар</h2>
<table>
    <tr>
        <th>Наименование</th>
        <th>Номер номенклатуры товара</th>
        <th>Количество</th>
        <th>Длина</th>
        <th>Высота</th>
        <th>Вес</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="good" items="${goods}">
        <tr>
            <td>${good.name}</td>
            <td>${good.nomenclature.number}</td>
            <td>${good.amount}</td>
            <td>${good.length}</td>
            <td>${good.height}</td>
            <td>${good.weight}</td>
            <td>
                <a href="/goods/edit/${goods.id}">Редактировать</a>
                <a href="/goods/delete/${goods.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="/goods/add">Добавить товар</a></p>
<p><a href="/">На главную</a></p>
