<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="entry" type="java.util.Map"%>

<c:set var="good" value="${entry.key}"/>
<c:set var="count" value="${entry.value}"/>

<fmt:bundle basename="i18n.messages">
    <form class="good goods-font panel panel-default" action="<c:url value="/do/delete-goods"/>">
        <div class="goods-del-button">
            <input type="hidden" name="id" value="${good.id}">
            <a class="del">
                <span class="glyphicon glyphicon-remove"></span>
            </a>
        </div>

        <div class="goods-icon text-center panel panel-default">
            <img class="icon" src="<c:url value="/image/${good.image.id}/${good.image.filename}"/>"/>
        </div>

        <div class="goods-name form-group text-center">
                ${good.goodsName}
        </div>

        <div class="goods-price form-group text-center">
                ${good.price}  x
        </div>

        <div class="goods-count form-group text-center">
            <input type="text" name="goods-count" class="form-control GoodsCount" maxlength="4" value="${count}">
        </div>

        <div class="goods-price text-center" id="${good.id}">
                ${good.price * count}
        </div>
    </form>
</fmt:bundle>