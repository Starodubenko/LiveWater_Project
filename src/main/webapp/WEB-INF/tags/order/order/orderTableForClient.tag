<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrders" tagdir="/WEB-INF/tags/order/order" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<%@ attribute name="list" type="java.util.List" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover">
        <%--<input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>--%>
        <thead>
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
        <th></th>
        <th><fmt:message key="message.edit"/></th>
        </thead>
        <tOrders:orderRowForClient orders="${list}"/>
    </table>
</fmt:bundle>