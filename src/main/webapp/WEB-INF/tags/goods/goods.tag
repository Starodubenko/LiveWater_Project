<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="goods" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <input type="hidden" id="pageNumber" value="${goods.getPageNumber()}"/>
    <c:forEach var="good" items="${goods}">
        <form class="panel panel-default goods-block" onsubmit="return false;">
            <a href="<c:url value="/do/about-goods?id=${good.getId()}"/>"><img class="goods-image" src="<c:url value="/image/${good.getImage().getId()}/${good.getImage().getFilename()}"/>"/></a>

            <div class="goods-describe">
                    ${good.getGoodsName()}
            </div>
            <div class="goods-price">
                    ${good.getPrice()}
            </div>
            <input type="hidden" name="id" value="${good.getId()}">
                <%--<button class="adding-button" <c:if test="${good.inCart}">disabled</c:if>>--%>
            <button class="
            <c:choose>
                <c:when test="${good.inCart}">
                    go-to-cart
                </c:when>
                <c:otherwise>
                    adding-button
                </c:otherwise>
            </c:choose>">
                <c:choose>
                    <c:when test="${good.inCart}">
                        <fmt:message key="message.button.in.cart"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="message.button.add.goods"/>
                    </c:otherwise>
                </c:choose>
            </button>
        </form>
    </c:forEach>
</fmt:bundle>