<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="orders" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${orders}">
        <form id="entity-line${entity.getId()}">
            <tr>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field">${entity.getNumber()}</label>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="user${entity.getId()}">${entity.getUser().getLastName()} ${entity.getUser().getFirstName()} ${entity.getUser().getMiddleName()}</label>
                    <input id="user${entity.getId()}" name="user" type="text" class="form-control edit-field" style="display: none;"
                           value="${entity.getUser().getLastName()} ${entity.getUser().getFirstName()} ${entity.getUser().getMiddleName()}"/>
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
                    <input id="delivery_date${entity.getId()}" name="delivery_date" type="text" class="form-control edit-field" style="display: none;"
                           value="${entity.getDeliveryDate()}"/>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="period${entity.getId()}">${entity.getPeriod().getPeriod()}</label>
                    <input id="period${entity.getId()}" name="period" type="text" class="form-control edit-field" style="display: none;"
                           value="${entity.getPeriod().getPeriod()}"/>
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
                    <input id="paid${entity.getId()}" name="paid" type="checkbox" class="edit-field" style="display: none;"
                           value="${entity.isPaid()}"/>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="additional_info${entity.getId()}">${entity.getAdditionalInfo()}</label>
                    <textarea id="additional_info${entity.getId()}" name="additional_info" class="form-control edit-field" style="display: none;">
                            ${entity.getAdditionalInfo()}
                    </textarea>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="status${entity.getId()}"><fmt:message key="message.${entity.getStatus().getStatusName()}"/></label>
                    <input id="status${entity.getId()}" type="text" class="form-control" style="display: none;" value="<fmt:message key="message.${entity.getStatus().getStatusName()}"/>"/>
                    <input name="status" type="hidden" class="edit-field" value="${entity.getStatus().getStatusName()}"/>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <label class="field" for="deleted${entity.getId()}">
                        <c:choose>
                            <c:when test="${entity.isDeleted() eq true}"><fmt:message key="message.yes"/></c:when>
                            <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                        </c:choose>
                    </label>
                    <input id="deleted${entity.getId()}" name="deleted" type="checkbox" class="edit-field" style="display: none;" value="${entity.isDeleted()}"/>
                </td>
                <td rowspan="${entity.getGoodsCount()+1}">
                    <button type="button" class="btn btn-primary delete" name="id" value="${entity.getId()}">
                        <fmt:message key="button.delete"/>
                    </button>
                </td>
            </tr>
            <c:forEach var="good" items="${entity.getGoods()}">
                <tr style="{margin-top: 0}">
                    <td>${good.getKey().getGoodsName()}</td>
                    <td>${good.getValue()}</td>
                </tr>
            </c:forEach>
        </form>
    </c:forEach>
</fmt:bundle>