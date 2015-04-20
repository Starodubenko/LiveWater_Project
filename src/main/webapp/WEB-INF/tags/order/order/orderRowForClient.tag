<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="orders" type="java.util.List" %>
<%@ attribute name="todayDate" type="java.util.Date" %>
<%@ attribute name="today" type="java.lang.Boolean" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${orders}">
            <tr>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field">${entity.getNumber()}</label>
                </td>
                <td colspan="2"></td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label>${entity.getTotalSum()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field">${entity.getDiscount().getPercentage()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label>${entity.getTotalSum() * (1 - entity.getDiscount().getPercentage()/100)}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="delivery_date${entity.getId()}">${entity.getDeliveryDate()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="period${entity.getId()}">${entity.getPeriod().getPeriod()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field">${entity.getOrderDate()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="paid${entity.getId()}">
                        <c:choose>
                            <c:when test="${entity.isPaid() eq true}"><fmt:message key="message.yes"/></c:when>
                            <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                        </c:choose>
                    </label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="additional_info${entity.getId()}">${entity.getAdditionalInfo()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="status${entity.getId()}"><fmt:message key="message.${entity.getStatus().getStatusName()}1"/></label>
                </td>
                <c:if test="${entity.getStatus().getStatusName() eq 'waiting'}">
                    <td rowspan="${entity.getGoodsCount()+1}">
                        <button type="button" class="order-function-button cancelW" name="id" value="${entity.getId()}" title="<fmt:message key="button.cancel.order"/>">
                            <img src="/style/img/buttons/cancel.jpg"/>
                        </button>
                    </td>
                </c:if>
                <c:if test="${entity.getStatus().getStatusName() eq 'active'}">
                    <td rowspan="${entity.getGoodsCount()+1}">
                        <button type="button" class="order-function-button cancelA" name="id" value="${entity.getId()}" title="<fmt:message key="button.cancel.order"/>">
                            <img src="/style/img/buttons/cancel.jpg"/>
                        </button>
                    </td>
                </c:if>
                <c:if test="${entity.getStatus().getStatusName() eq 'canceled'}">
                    <td rowspan="${entity.getGoodsCount()+1}">
                        <button type="button" class="order-function-button restore" name="id" value="${entity.getId()}" title="<fmt:message key="button.restore.order"/>">
                            <img src="/style/img/buttons/restore.jpg"/>
                        </button>
                    </td>
                </c:if>
                <c:if test="${entity.getStatus().getStatusName() eq 'executed'}">
                    <td rowspan="${entity.getGoodsCount()+1}">
                        <button type="button" class="order-function-button repeat" name="id" value="${entity.getId()}" title="<fmt:message key="button.repeat.order"/>">
                            <img src="/style/img/buttons/repeat.jpg"/>
                        </button>
                    </td>
                </c:if>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <a <c:if test="${entity.getStatus().getStatusName() eq 'canceled'}">
                    href="<c:url value="/do/editOrder?id=${entity.getId()}"/>"
                    </c:if> ><fmt:message key="message.edit"/></a>
                </td>
            </tr>
            <c:forEach var="good" items="${entity.getGoods()}">
                <tr style="{margin-top: 0}">
                    <td>${good.getKey().getGoodsName()}</td>
                    <td>${good.getValue()}</td>
                </tr>
            </c:forEach>
    </c:forEach>
</fmt:bundle>