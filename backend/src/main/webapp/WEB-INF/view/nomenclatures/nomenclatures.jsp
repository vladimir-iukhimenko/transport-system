<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Номенклатуры товара</title>
</head>
<body>

<h2>Номенклатуры товара</h2>
<table>
    <tr>
        <th>Номер</th>
        <th>Длина</th>
        <th>Ширина</th>
        <th>Высота</th>
        <th>Вес</th>
        <th>Комментарий</th>
        <th> <form action="/search?q=${query}" method="GET">
            <input type="hidden" name="context" value="nomenclatures">
            <input type="search" name="query" id="search">
            <input type="submit" value="Поиск">
        </form>
        </th>
    </tr>
    <c:forEach var="nomenclature" items="${nomenclatures}">
        <tr>
            <td>${nomenclature.number}</td>
            <td>${nomenclature.length}</td>
            <td>${nomenclature.width}</td>
            <td>${nomenclature.height}</td>
            <td>${nomenclature.weight}</td>
            <c:forEach var="comment" items="${nomenclature.comments.getAllComments()}">
                <tr>
                    <td>${comment}</td>
                </tr>

            </c:forEach>
            <td>
                <a href="/nomenclatures/edit/${nomenclature.id}">Редактировать</a>
                <a href="/nomenclatures/delete/${nomenclature.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="/nomenclatures/add">Добавить номенклатуру</a></p>
<p><a href="/">На главную</a></p>