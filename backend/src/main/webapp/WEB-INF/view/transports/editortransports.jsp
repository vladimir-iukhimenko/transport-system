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
    <c:if test="${empty transport.id}">
        <title>Добавить транспорт</title>
    </c:if>
    <c:if test="${!empty transport.id}">
        <title>Редактировать транспорт</title>
    </c:if>
</head>
<body>
<c:if test="${empty transport.id}">
    <c:url value="/transports/add" var="var"/>
</c:if>
<c:if test="${!empty transport.id}">
    <c:url value="/transports/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty transport.number}">
        <input type="hidden" name="id" value="${transport.id}">
    </c:if>
    <p> <label for="number">Номер а/м</label>
    <input type="text" name="number" id="number" value="${transport.number}">
    <span class="validationError">
        <c:if test="${errors.hasFieldErrors('number')}">${errors.getFieldError('number').defaultMessage}</c:if>
    </span></p>
    <p><label for="vin">VIN</label>
    <input type="text" name="vin" id="vin" value ="${transport.vin}">
    <span class="validationError">
        <c:if test="${errors.hasFieldErrors('vin')}">${errors.getFieldError('vin').defaultMessage}</c:if>
    </span></p>
    <p><label for="modelid">Модель</label>
    <select id="modelid" name="transportmodelid"></p>
        <c:forEach var="transportmodel" items="${transportmodels}">
            <p><option value="${transportmodel.id}">${transportmodel.name}</option></p>
        </c:forEach>

    </select>
    <p><label for="producedyear">Год выпуска</label>
    <input type="text" name="producedyear" id="producedyear" value="${transport.producedyear}">
    <span class="validationError">
        <c:if test="${errors.hasFieldErrors('producedyear')}">${errors.getFieldError('producedyear').defaultMessage}</c:if>
    </span></p>
    <p><label for="startupdate">Ввод в эксплуатацию</label>
    <input type="date" name="startupdate" id="startupdate" value="${transport.startupdate}">
    <span class="validationError">
        <c:if test="${errors.hasFieldErrors('startupdate')}">${errors.getFieldError('startupdate').defaultMessage}</c:if>
    </span></p>
    <c:if test="${empty transport.id}">
    <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty transport.id}">
    <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>
