<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 04.12.2019
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty transportorder.id}">
        <title>Добавить транспортный заказ</title>
    </c:if>
    <c:if test="${!empty transportorder.id}">
        <title>Редактировать транспортный заказ</title>
    </c:if>
</head>
<body>
<c:if test="${empty transportorder.id}">
    <c:url value="/transportorders/add" var="var"/>
</c:if>
<c:if test="${!empty transportorder.id}">
    <c:url value="/transportorders/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty transportorder.id}">
        <input type="hidden" name="id" value="${transportorder.id}">
        <input type="hidden" name="ordernumber" value="${transportorder.ordernumber}">
        <input type="hidden" name="orderdate" value="${transportorder.orderdate}">
    </c:if>
    <p> <label for="transportpresentingdate">Дата исполнения</label>
        <input type="date" name="transportpresentingdate" id="transportpresentingdate" value="${transportorder.transportpresentingdate}"></p>
    <p><label for="loadingplace">Место погрузки</label>
        <input type="text" name="loadingplace" id="loadingplace" value ="${transportorder.loadingplace}"></p>
    <p><label for="unloadingplace">Место разгрузки</label>
        <input type="text" name="unloadingplace" id="unloadingplace" value="${transportorder.unloadingplace}"></p>
    <p><label for="placemethod">Метод погрузки</label>
        <input type="text" name="placemethod" id="placemethod" value="${transportorder.placemethod}"></p>
    <p><label for="transport">Транспорт</label>
        <select id="transport" name="transport"></p>
    <c:forEach var="transport" items="${transports}">
        <p><option value="${transport.id}">${transport.number}</option></p>
    </c:forEach>
    </select>
    <p><label for="employeeresponsible">Водитель</label>
        <select id="employeeresponsible" name="employeeresponsible"></p>
            <c:forEach var="employee" items="${employees}">
                <p><option value="${employee.id}">${employee.name} ${employee.surname}</option></p>
            </c:forEach>
        </select>
    <p><label for="telephonenumber">Контактный номер телефона</label>
        <input type="number" name="telephonenumber" id="telephonenumber" value="${transportorder.telephonenumber}"></p>
    <p><label for="employeecustomer">Заказчик</label>
        <select id="employeecustomer" name="employeecustomer"></p>
            <c:forEach var="employee" items="${employees}">
                <p><option value="${employee.id}">${employee.name} ${employee.surname}</option></p>
            </c:forEach>
        </select>
    <p><label for="comment">Комментарий</label>
        <input type="text" name="comment" id="comment"></p>
    <c:if test="${empty transportorder.id}">
        <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty transportorder.id}">
        <p><input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>

