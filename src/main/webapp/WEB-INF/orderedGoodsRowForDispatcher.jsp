<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <thead>
    <td><fmt:message key="orders.message.goods.name"/></td>
    <td><fmt:message key="orders.message.goods.count"/></td>
    <td><fmt:message key="orders.message.goods.cost"/></td>
    </thead>
    <c:forEach var="entity" items="${shoppingCartForClient.getGoodsList()}">
            <tr>
                <td>
                    <label style="width: 250px">${entity.getGoodsName()}</label>
                </td>
                <td>
                    <input type="text" class="form-control goodsCount" value="${shoppingCartForClient.getGoods().get(entity)}" maxlength="4" style="width: 70px">
                </td>
                <td id="goodsPrice${entity.getId()}">
                    ${entity.getPrice()*shoppingCartForClient.getGoods().get(entity)}
                </td>
            </tr>
    </c:forEach>
</fmt:bundle>