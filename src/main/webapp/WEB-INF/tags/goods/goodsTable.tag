<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientTable">
        <input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>
        <thead>
        <th><fmt:message key="orders.message.id"/></th>
        <th><fmt:message key="goods.message.image"/></th>
        <th><fmt:message key="goods.message.goods.name"/></th>
        <th><fmt:message key="goods.message.price"/></th>
        <th><fmt:message key="message.deleted"/></th>
        <th></th>
        <th><fmt:message key="message.edit"/></th>
        </thead>
        <tGoods:goodsRow goods="${paginatedList}"/>
    </table>
</fmt:bundle>