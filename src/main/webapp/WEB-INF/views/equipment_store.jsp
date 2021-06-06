<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file='/WEB-INF/styles/style.css' %>
</style>
<script>

</script>
<html>
<head>
    <meta charset="UTF-8">
    <title>RainBlow: Пейнтбольный магазин</title>
    <link rel="shortcut icon" href="../../resources/rainblow-icon.ico"/>
</head>
<body>
<div class="header-container">
    <a class="main-caption" href="equipment_store">
    <span style="color: #4c95ff">Rain</span
    ><span style="color: #59c169">Blow</span>
    </a>
    <div class="top-right">
        <div class="cart">
            <a href="shopping_cart">Корзина (0)</a>
        </div>
        <div class="login-register">
            <a href="login">Вход</a>
            /
            <a href="register">Регистрация</a>
        </div>
    </div>
</div>
<div class="topnav">
    <a class="active" href="equipment_store">Магазин экипировки</a>
    <a href="site_reservation">Аренда площадок</a>
</div>
<div class="equipment-block" style="margin-top: 30px">
    <label for="equipment-type">Тип товара</label>
    <select name="equipment-type" id="equipment-type">
        <option value="all" selected>Все</option>
        <option value="clothing">Одежда</option>
        <option value="marker">Маркеры</option>
        <option value="balloon">Баллоны</option>
        <option value="balls">Пейнтбольный шарики</option>
    </select>
    <div class="equipment-grid" style="margin-top: 60px">
        <c:forEach items="${equipments}" var="equipment">
            <%--<form:form method="POST" action="addToCart">--%>
            <div class="equipment-element">
                <span>
                    ${equipment.name}<br>
                    ${equipment.price}₽<br>
                    Количество:
                </span>
                <input type="number" name="quantity" value="1" min="1" max="${equipment.quantity}">
                <input type="button" value="В корзину" style="margin: 0.5em auto;
                display: block; background-color: palegreen">
            </div>
            <%--</form:form>--%>
        </c:forEach>
        <!--<div class="equipment-element">
            <span>
                Название товара<br>
                Стоимость<br>
                Количество:
            </span>
            <input type="number" name="quantity" value="1" min="1" max="5">
            <input type="button" value="В корзину" style="margin: 0.5em auto;
            display: block; background-color: palegreen">
        </div>
        <div class="equipment-element">
            <span>
                Название товара<br>
                Стоимость<br>
                Количество:
            </span>
            <input type="number" name="quantity" value="1" min="1" max="5">
            <input type="button" value="В корзину" style="margin: 0.5em auto;
            display: block; background-color: palegreen">
        </div>
        <div class="equipment-element">
            <span>
                Название товара<br>
                Стоимость<br>
                Количество:
            </span>
            <input type="number" name="quantity" value="1" min="1" max="5">
            <input type="button" value="В корзину" style="margin: 0.5em auto;
            display: block; background-color: palegreen">
        </div>
        <div class="equipment-element">
            <span>
                Название товара<br>
                Стоимость<br>
                Количество:
            </span>
            <input type="number" name="quantity" value="1" min="1" max="5">
            <input type="button" value="В корзину" style="margin: 0.5em auto;
            display: block; background-color: palegreen">
        </div>-->
    </div>
</div>
</body>
</html>