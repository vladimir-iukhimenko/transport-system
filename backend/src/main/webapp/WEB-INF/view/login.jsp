<%--
  Created by IntelliJ IDEA.
  User: DZCKJS0
  Date: 08.01.2020
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Вход</title>
</head>
<body>
<form:form action="/login" method="POST">
    <p> <label for="username">Логин</label>
        <input type="text" name="username" id="username">
    </p>
    <p> <label for="password">Пароль</label>
        <input type="text" name="password" id="password">
    <p><button type="submit">Войти</button></p>
</form:form>
<p><a href="/register">Регистрация</a></p>
</body>
</html>
