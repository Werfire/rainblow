<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <%--<c:if test="${isAdmin}">
        <div>Режим администратора</div>
    </c:if>--%>
    <div class="top-right">
        <div class="cart">
            <a href="shopping_cart">Корзина (${cartItems})</a>
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
    <c:choose>
        <c:when test="${fn:length(equipments)==0}">
            <h1 style="text-align: center; margin-top: 3em">Товары отсутствуют</h1>
        </c:when>
        <c:otherwise>
            <div class="equipment-grid" style="margin-top: 60px">
                    <c:forEach items="${equipments}" var="equipment">
                        <form:form method="POST" action="addToCart">
                        <div class="equipment-element">
                            <span>
                                ${equipment.name}<br>
                                ${equipment.price}₽<br>
                                Количество:
                            </span>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" max="${equipment.quantity}">
                            <input type="hidden" id="equipmentId" name="equipmentId" value="${equipment.id}">
                            <button type="submit" name="addToCart" style="margin: 0.5em auto;
                            display: block; background-color: palegreen">В корзину</button>
                        </div>
                        </form:form>
                    </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>