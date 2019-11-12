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
    <title>Редактировать транспорт</title>
</head>
<body>
<c:url value="/edit" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${transport.id}">
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
    <%--<input type="hidden" name="model" value="${(transportsystem.model.TransportModel)transport.model}">--%>
    <label for="producedyear">Год выпуска</label>
    <input type="text" name="producedyear" id="producedyear">
    <label for="startupdate">Ввод в эксплуатацию</label>
    <input type="hidden" name="startupdate" id="startupdate" value="${transport.getStartUpDate()}">
    <input type="submit" value="Редактировать">
</form>
</body>
</html>
