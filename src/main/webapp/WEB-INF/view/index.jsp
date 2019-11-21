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
<p>
<c:url value="/employeedocs" var="employeedocs"/>
<a href="${employeedocs}">Документы сотрудников</a>
</p>
</body>
</html>