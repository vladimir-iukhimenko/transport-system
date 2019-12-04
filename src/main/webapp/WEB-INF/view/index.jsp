<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>

<p><a href="/transports">Транспорт</a></p>
<p>
<c:url value="/transportdocs" var="transportdocs"/>
<a href="${transportdocs}">Транспортные документы</a>
</p>

    <c:url value="/goods" var="goods"/>
    <a href="${goods}">Товары</a>
</p>
<p>
    <c:url value="/nomenclatures" var="nomenclatures"/>
    <a href="${nomenclatures}">Номенклатуры</a>
</p>
<p>
    <c:url value="/employees" var="employees"/>
    <a href="${employees}">Сотрудники</a>
</p>
<p>
    <c:url value="/transportorders" var="transportorders"/>
    <a href="${transportorders}">Транспортные заказы</a>
</p>
</body>
</html>