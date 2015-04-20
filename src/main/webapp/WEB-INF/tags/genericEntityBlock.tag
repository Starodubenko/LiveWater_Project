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

<c:set var="whatCount" value="client.count"/>

<fmt:bundle basename="i18n.messages">
    <div id="entityBlock">

        <div class="above-table-row">
            <ul id="changee" class="pagination above-table-row-content">
                <li id="back"><a href="#page">&laquo;</a></li>

                <c:forEach var="i" begin="1" end="${paginatedList.getPageCount()}">
                    <li value="${i}" name="page${i}" class="numbered page <c:if test="${page == i}">active</c:if>">
                        <a href="#page${i}">${i}</a>
                    </li>
                </c:forEach>

                <li id="next"><a href="#page">&raquo;</a></li>
            </ul>

            <div class="above-table-row-content rows-count">
                <t:rowsCount pageName="admin" targetRowsCount="${paginatedList.getRowsPerPage()}" whatCount="${entityName.toLowerCase().substring(0,entityName.length()-1)}.count"/>
            </div>
            <div class="clear"></div>
        </div>

        <div class="entitiesList tab-pane" style="overflow-y: scroll">
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
        </div>

        <div class="add-entity">

        </div>

        <script>
            $(function () {
//                $("li.numbered[value=" + 1 + "]").addClass("active");
                if ($('.numbered').size() < 2) {
                    $('#next').addClass("disabledd");
                    $('#back').addClass("disabledd");
                }
            });
        </script>
    </div>
</fmt:bundle>