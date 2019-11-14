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
            <td>${transportmodels.get((transport.transportmodelid)-1).getName()}</td>
            <td>${transport.producedyear}</td>
            <td>${transport.startupdate}</td>
            <td>
                <a href="/edit/${transport.id}">edit</a>
                <a href="/delete/${transport.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Добавить</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add</a>
</body>
</html>