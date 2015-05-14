<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tShoppingCart" tagdir="/WEB-INF/tags/shoppingCart" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="farm.cart"/></title>
        <link rel='stylesheet' href='<c:url value="/style/viewShoppingCart.css"/>'>
    </t:gHead>
    <t:gbody>
        <div class="client-info">
            <t:payment/>
        </div>

        <div class="goods-list-edit panel panel-default">
            <div class="goods">
                <c:forEach var="entry" items="${editOrder.getGoods()}">
                    <%--<tShoppingCart:good entry="${entry}"/>--%>
                    <form class="good goods-font panel panel-default" action="<c:url value="/do/delete-goods"/>">
                        <div class="goods-del-button">
                            <input type="hidden" name="id" value="${entry.key.id}">
                            <a class="del">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a>
                        </div>

                        <div class="goods-icon text-center panel panel-default">
                            <img class="icon" src="<c:url value="/image/${entry.key.image.id}/${entry.key.image.filename}"/>"/>
                        </div>

                        <div class="goods-name form-group text-center">
                                ${entry.key.goodsName}
                        </div>

                        <div class="goods-price form-group text-center">
                                ${entry.key.price}  x
                        </div>

                        <div class="goods-count form-group text-center">
                            <input type="text" name="goods-count" class="form-control GoodsCount" maxlength="4" value="${entry.value}">
                        </div>

                        <div class="goods-price text-center" id="${entry.key.id}">
                                ${entry.key.price * entry.value}
                        </div>
                    </form>
                </c:forEach>
            </div>
                <div class="clear"></div>
            <div class="total-sum goods-font">
                <fmt:message key="message.total.cost"/>:<label id="total">${editOrder.getTotalSum()}</label>
            </div>
            <form method="GET" action="<c:url value="/do/saveEditedOrder"/>">
                <button class="button-continue-order" type="submit">
                    <fmt:message key="button.save"/>
                </button>
            </form>
        </div>

        <script src="<c:url value="/script/viewShoppingCart.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>