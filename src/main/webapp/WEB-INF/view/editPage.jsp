<%--
  Created by IntelliJ IDEA.
  User: Владимир
  Date: 10.11.2019
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty transport.number}">
        <title>Добавить транспорт</title>
    </c:if>
    <c:if test="${!empty transport.number}">
        <title>Редактировать транспорт</title>
    </c:if>
</head>
<body>
<c:if test="${empty transport.number}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty transport.number}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty transport.number}">
        <input type="hidden" name="id" value="${transport.id}">
    </c:if>
    <label for="number">Номер а/м</label>
    <input type="text" name="number" id="number">
    <label for="vin">VIN</label>
    <input type="text" name="vin" id="vin">
    <label for="modelid">Модель</label>
    <select id="modelid" name="transportmodelid">
        <c:forEach var="transportmodel" items="${transportmodels}">
            <option value="${transportmodel.id}">${transportmodel.name}</option>
        </c:forEach>

    </select>
    <label for="producedyear">Год выпуска</label>
    <input type="text" name="producedyear" id="producedyear">
    <label for="startupdate">Ввод в эксплуатацию</label>
    <c:if test="${!empty transport.number}">
        <input type="hidden" name="startupdate" id="startupdate" value="${transport.startupdate}">
    </c:if>
    <c:if test="${empty transport.number}">
        <input type="submit" value="Добавить">
    </c:if>
    <c:if test="${!empty transport.number}">
        <input type="submit" value="Редактировать">
    </c:if>
</form>
</body>
</html>
