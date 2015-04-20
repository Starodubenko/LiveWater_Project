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

<%@ attribute name="entityName" type="java.lang.String" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:choose>
        <c:when test="${entityName eq 'clientD'}">
            <tClientD:clientFilter/>
        </c:when>
        <c:when test="${entityName eq 'order2D'}">
            <tOrderD:orderFilter/>
        </c:when>

        <c:when test="${entityName eq 'clientA'}">
            <tClient:clientFilter/>
        </c:when>
        <c:when test="${entityName eq 'employeeA'}">
            <tEmployee:employeeFilter/>
        </c:when>
        <c:when test="${entityName eq 'order2A'}">
            <tOrder:orderFilter/>
        </c:when>
        <c:when test="${entityName eq 'goodsA'}">
            <tGoods:goodsFilter/>
        </c:when>
        <c:when test="${entityName eq 'paycardA'}">
            <tPayCard:payCardFilter/>
        </c:when>
        <c:when test="${entityName eq 'periodA'}">
            <tPeriod:periodFilter/>
        </c:when>
        <c:when test="${entityName eq 'positionA'}">
            <tPosition:positionFilter/>
        </c:when>
        <c:when test="${entityName eq 'discountA'}">
            <tDiscount:discountFilter/>
        </c:when>
        <c:when test="${entityName eq 'imageA'}">
            <tImage:imageFilter/>
        </c:when>
        <c:when test="${entityName eq 'contactA'}">
            <tContact:contactFilter/>
        </c:when>
        <c:when test="${entityName eq 'statusA'}">
            <tOrderStatus:orderStatusFilter/>
        </c:when>
        <c:when test="${entityName eq 'statuspaycardA'}">
            <tPayCardStatus:payCardStatusFilter/>
        </c:when>
    </c:choose>
</fmt:bundle>