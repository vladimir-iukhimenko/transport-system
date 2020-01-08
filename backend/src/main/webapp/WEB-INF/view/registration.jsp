<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 06.01.2020
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form:form action="/register" method="POST">
    <p> <label for="username">Логин</label>
        <input type="text" name="username" id="username">
    </p>
    <p> <label for="password">Пароль</label>
        <input type="text" name="password" id="password">
    </p>
    <p> <label for="enabled">Активен</label>
        <input type="checkbox" name="enabled" id="enabled">
    </p>
    <p><label for="employeeid">Связать с сотрудником</label>
        <select id="employeeid" name="employeeid"></p>
    <c:forEach var="employee" items="${employees}">
    <p><option value="${employee.id}">${employee.name} ${employee.surname}</option></p>
    </c:forEach>
    </select>
    <p><input type="submit" value="Добавить"></p>
</form:form>
</body>
</html>
