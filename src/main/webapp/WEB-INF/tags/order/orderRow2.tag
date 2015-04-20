<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="orders" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="order" items="${orders}">
        <tr>
            <td rowspan="${order.getGoodsCount()+1}">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="IdOrder" value="${row.getId()}">
                    </label>
                </div>
            </td>
            <td rowspan="${order.getGoodsCount()+1}">
                <div>${order.getNumber()}</div>
            </td>
            <td colspan="2"></td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getTotalSum()}</td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getDiscount().getPercentage()}</td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getTotalSum() * (1 - order.getDiscount().getPercentage()/100)}</td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getDeliveryDate()}</td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getPeriod().getPeriod()}</td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getOrderDate()}</td>
            <td rowspan="${order.getGoodsCount()+1}">
                <c:choose>
                    <c:when test="${order.isPaid() eq true}"><fmt:message key="message.yes"/></c:when>
                    <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                </c:choose>
            </td>
            <td rowspan="${order.getGoodsCount()+1}">${order.getAdditionalInfo()}</td>
            <td rowspan="${order.getGoodsCount()+1}"><fmt:message key="message.${order.getStatus().getStatusName()}"/></td>
        </tr>
        <c:forEach var="good" items="${order.getGoods()}">
            <tr style="{margin-top: 0}">
                <td>${good.getKey().getGoodsName()}</td>
                <td>${good.getValue()}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</fmt:bundle>