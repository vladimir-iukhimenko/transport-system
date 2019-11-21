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
    <c:url value="/addtransport" var="var"/>
</c:if>
<c:if test="${!empty transport.number}">
    <c:url value="/edittransport" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty transport.number}">
        <input type="hidden" name="id" value="${transport.id}">
    </c:if>
    <p> <label for="number">Номер а/м</label>
    <input type="text" name="number" id="number" value="${transport.number}"></p>
    <p><label for="vin">VIN</label>
    <input type="text" name="vin" id="vin" value ="${transport.vin}"></p>
    <p><label for="modelid">Модель</label>
    <select id="modelid" name="transportmodelid"></p>
        <c:forEach var="transportmodel" items="${transportmodels}">
            <p><option value="${transportmodel.id}">${transportmodel.name}</option></p>
        </c:forEach>

    </select>
    <p><label for="producedyear">Год выпуска</label>
    <input type="text" name="producedyear" id="producedyear" value="${transport.producedyear}"></p>
    <p><label for="startupdate">Ввод в эксплуатацию</label>
    <input type="date" name="startupdate" id="startupdate" value="${transport.startupdate}"></p>
    <c:if test="${empty transport.number}">
    <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty transport.number}">
    <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
