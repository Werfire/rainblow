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
<a class="main-caption" href="equipment_store">
    <span style="color: #4c95ff">Rain</span
    ><span style="color: #59c169">Blow</span>
</a>
<form>
    <div class="centered" style="top: 50%">
        <h1 style="text-align: center; margin-bottom: 12px">Регистрация</h1>
        <label for="login" style="display: block; margin-bottom: 6px">Логин (от 3 до 32 символов)</label>
        <input type="text" id="login" name="login" required minlength="3"
               maxlength="32" size="46" style="display: block; margin-bottom: 20px">
        <label for="name" style="display: block; margin-bottom: 6px">Ваше имя</label>
        <input type="text" id="name" name="name" required
               maxlength="32" size="46" style="display: block; margin-bottom: 20px">
        <label for="email" style="display: block; margin-bottom: 6px">Электронная почта</label>
        <input type="text" id="email" name="email" required minlength="8"
               maxlength="128" size="46" style="display: block; margin-bottom: 20px">
        <label for="phone" style="display: block; margin-bottom: 6px">Номер телефона (опционально)</label>
        <input type="text" id="phone" name="phone" required minlength="3"
               maxlength="16" size="46" style="display: block; margin-bottom: 20px">
        <label for="address" style="display: block; margin-bottom: 6px">Адрес доставки (опционально)</label>
        <input type="text" id="address" name="address" required
               maxlength="256" size="46" style="display: block; margin-bottom: 20px">
        <label for="password" style="display: block; margin-bottom: 6px">Пароль</label>
        <input type="password" id="password" name="password" required minlength="6"
               maxlength="32" size="46" style="display: block; margin-bottom: 20px">
        <button type="submit" name="register" style="display: block; margin: auto">Зарегистрироваться!</button>
    </div>
</form>
</body>
</html>