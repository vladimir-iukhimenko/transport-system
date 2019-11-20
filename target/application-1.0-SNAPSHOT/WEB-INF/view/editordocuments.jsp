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
    <<c:if test="${!empty document.documenttype}">
        <title>Редактировать документ</title>
    </c:if>
</head>
<body>
<c:if test="${empty document.documenttype && fn:contains(type,'DocumentTransport')}">
    <c:url value="/addtransportdoc" var="var"/>
</c:if>
<c:if test="${empty document.documenttype && fn:contains(type,'DocumentEmployee')}">
    <c:url value="/addemployeedoc" var="var"/>
</c:if>
<c:if test="${!empty document.documenttype && fn:contains(type,'DocumentTransport')}">
    <c:url value="/editemployeedoc" var="var"/>
</c:if>
<c:if test="${!empty document.documenttype && fn:contains(type,'DocumentEmployee')}">
    <c:url value="/editemployeedoc" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <<c:if test="${!empty document.documenttype}">
        <input type="hidden" name="id" value="${document.id}">
    </c:if>
    <label for="documenttype">Тип документа</label>
        <input type="text" name="documenttype" id="documenttype" value="${document.documenttype}">
    <label for="series">Серия</label>
        <input type="text" name="series" id="series" value ="${document.series}">
    <label for="number">Номер</label>
        <input type="text" name="number" id="number" value ="${document.number}">
    <label for="issuedby">Выпущено</label>
        <input type="text" name="issuedby" id="issuedby" value="${document.issuedby}">
    <label for="issueddate">Дата выпуска</label>
        <input type="text" name="issueddate" id="issueddate" value="${document.issueddate}">
<c:if test="${fn:contains(type,'DocumentTransport')}">
    <label for="expiredate">Дата истечения</label>
        <input type="text" name="expiredate" id="expiredate" value="${document.expiredate}">
    </c:if>
<c:if test="${fn:contains(type,'DocumentEmployee')}">
    <label for="employeeid">Сотрудник</label>
    <input type="text" name="employeeid" id="employeeid" value="${document.employeeid}">
</c:if>

    <c:if test="${empty document.documenttype}">
        <input type="submit" value="Добавить">
    </c:if>
    <<c:if test="${!empty document.documenttype}">
        <input type="submit" value="Редактировать">
    </c:if>
</form>
</body>
</html>
