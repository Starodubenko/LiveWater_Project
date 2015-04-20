<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/shoppingCart" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <div class="goods">
        <c:forEach var="entry" items="${shoppingCart}">
            <t:good entry="${entry}"/>
        </c:forEach>
    </div>
    <div class="clear"></div>
    <div class="total-sum goods-font">
        <fmt:message key="message.total.cost"/>:<label id="total">${shoppingCart.getTotalSum()}</label>
    </div>
    <button class="button-continue-order">
        <fmt:message key="button.cont.order"/>
    </button>
</fmt:bundle>