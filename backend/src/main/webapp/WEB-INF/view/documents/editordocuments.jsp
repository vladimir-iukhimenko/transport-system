<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 18.11.2019
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <c:set var = "type" value = "${type}"/>
    <c:if test="${empty document.documenttype}">
        <title>Добавить документ</title>
    </c:if>
    <c:if test="${!empty document.documenttype}">
        <title>Редактировать документ</title>
    </c:if>
</head>
<body>
<c:if test="${empty document.documenttype && fn:contains(type,'DocumentTransport')}">
    <c:url value="/transportdocs/add" var="var"/>
</c:if>
<c:if test="${empty document.documenttype && fn:contains(type,'DocumentEmployee')}">
    <c:url value="/employeedocs/add" var="var"/>
</c:if>
<c:if test="${!empty document.documenttype && fn:contains(type,'DocumentTransport')}">
    <c:url value="/transportdocs/edit" var="var"/>
</c:if>
<c:if test="${!empty document.documenttype && fn:contains(type,'DocumentEmployee')}">
    <c:url value="/employeedocs/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty document.documenttype}">
        <input type="hidden" name="id" value="${document.id}">
    </c:if>
    <p><label for="documenttype">Тип документа</label>
        <input type="text" name="documenttype" id="documenttype" value="${document.documenttype}"></p>
    <p><label for="series">Серия</label>
        <input type="text" name="series" id="series" value ="${document.series}"></p>
    <p><label for="number">Номер</label>
        <input type="number" name="number" id="number" value ="${document.number}"></p>
    <p><label for="issuedby">Выпущено</label>
        <input type="text" name="issuedby" id="issuedby" value="${document.issuedby}"></p>
    <p><label for="issueddate">Дата выпуска</label>
        <input type="date" name="issueddate" id="issueddate" value="${document.issueddate}"></p>
<c:if test="${fn:contains(type,'DocumentTransport')}">
    <p><label for="expiredate">Дата истечения</label>
        <input type="date" name="expiredate" id="expiredate" value="${document.expiredate}"></p>
    <p><label for="transport">Транспорт</label>
        <select id="transport" name="transportid"></p>
    <c:forEach var="transport" items="${transports}">
        <p><option value="${transport.id}">${transport.number}</option></p>
    </c:forEach>
    </select>
    </c:if>
<c:if test="${fn:contains(type,'DocumentEmployee')}">
    <p><label for="employee">Сотрудник</label>
        <select id="employee" name="employeeid"></p>
    <c:forEach var="employee" items="${employees}">
        <p><option value="${employee.id}">${employee.name} ${employee.surname}</option></p>
    </c:forEach>
    </select>
</c:if>
    <c:if test="${empty document.documenttype}">
    <p><input type="submit" value="Добавить"></p>
    </c:if>
    <c:if test="${!empty document.documenttype}">
    <p> <input type="submit" value="Редактировать"></p>
    </c:if>
</form>
<p><a href="/">На главную</a></p>
</body>
</html>