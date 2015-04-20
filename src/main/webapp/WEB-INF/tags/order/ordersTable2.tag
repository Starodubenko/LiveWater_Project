<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>
<%@ attribute name="ordersPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="ordersForm" onsubmit="return false;">
        <table class="table table-hover" ID="ordersTable" data-url="data1.json" data-height="299" data-sort-name="name" data-sort-order="desc">
            <input type="hidden" id="ordersPageNumber"
                   value="${ordersPaginatedList.getPageNumber()}"/>
            <thead>
            <tr>
                <th>
                    <div class="checkbox">
                        <label>
                            <input id="maincheck" type="checkbox">
                        </label>
                    </div>
                </th>
                <th data-sortable="true"><fmt:message key="orders.message.number"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.goods.name"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.goods.count"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.order.cost.without.discount"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.discount"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.order.cost"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.delivery.date"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.delivery.time"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.order.date"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.paid"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.additional.info"/></th>
                <th data-sortable="true"><fmt:message key="orders.message.status"/></th>
            </tr>
            </thead>
            <tOrder:orderRow2 orders="${ordersPaginatedList}"/>
        </table>
    </form>
</fmt:bundle>