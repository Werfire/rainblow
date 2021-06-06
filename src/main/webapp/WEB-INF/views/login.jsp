<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/styles/style.css' %>
</style>
<html>
<head>
    <meta charset="UTF-8">
    <title>RainBlow: Пейнтбольный магазин</title>
    <link rel="shortcut icon" href="../../resources/rainblow-icon.ico"/>
</head>
<body>
<a class="main-caption" href="index">
    <span style="color: #4c95ff">Rain</span
    ><span style="color: #59c169">Blow</span>
</a>
<form>
    <div class="centered">
        <h1 style="text-align: center; margin-bottom: 12px">Вход</h1>
        <label for="login" style="display: block; margin-bottom: 6px">Логин</label>
        <input type="text" id="login" name="login" required minlength="3"
               maxlength="32" size="32" style="display: block; margin-bottom: 20px">
        <label for="password" style="display: block; margin-bottom: 6px">Пароль</label>
        <input type="password" id="password" name="password" required minlength="6"
               maxlength="32" size="32" style="display: block; margin-bottom: 20px">
        <button type="submit" style="display: block; margin: auto">Войти</button>
    </div>
</form>
</body>