<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client/client" %>
<%@ taglib prefix="tClientD" tagdir="/WEB-INF/tags/client/client/forDispatcher" %>
<%@ taglib prefix="tEmployee" tagdir="/WEB-INF/tags/employee" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order/order" %>
<%@ taglib prefix="tOrderD" tagdir="/WEB-INF/tags/order/order/forDispatcher" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>
<%@ taglib prefix="tPayCard" tagdir="/WEB-INF/tags/payCard" %>
<%@ taglib prefix="tPeriod" tagdir="/WEB-INF/tags/period" %>
<%@ taglib prefix="tPosition" tagdir="/WEB-INF/tags/position" %>
<%@ taglib prefix="tDiscount" tagdir="/WEB-INF/tags/discount" %>
<%@ taglib prefix="tImage" tagdir="/WEB-INF/tags/image" %>
<%@ taglib prefix="tContact" tagdir="/WEB-INF/tags/contact" %>
<%@ taglib prefix="tOrderStatus" tagdir="/WEB-INF/tags/orderStatus" %>
<%@ taglib prefix="tPayCardStatus" tagdir="/WEB-INF/tags/payCardStatus" %>

<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<%@ attribute name="entityName" type="java.lang.String" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:choose>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'clientD'}">
            <tClientD:clientTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'order2D'}">
            <tOrderD:orderTable paginatedList="${paginatedList}"/>
        </c:when>


        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'goodsG'}">
            <tGoods:goods goods="${paginatedList}"/>
        </c:when>


        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'clientA'}">
            <tClient:clientTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'employeeA'}">
            <tEmployee:employeeTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'order2A'}">
            <tOrder:orderTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'goodsA'}">
            <tGoods:goodsTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'paycardA'}">
            <tPayCard:payCardsTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'periodA'}">
            <tPeriod:periodTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'positionA'}">
            <tPosition:positionTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'discountA'}">
            <tDiscount:discountTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'imageA'}">
            <tImage:imageTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'contactA'}">
            <tContact:contactTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'statusA'}">
            <tOrderStatus:orederStatusTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:when test="${paginatedList.getTotalRowsCount() > 0 and entityName eq 'statuspaycardA'}">
            <tPayCardStatus:payCardStatusTable paginatedList="${paginatedList}"/>
        </c:when>
        <c:otherwise>
            <t:recordsNotFound/>
        </c:otherwise>
    </c:choose>
</fmt:bundle>