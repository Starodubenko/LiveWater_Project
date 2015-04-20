<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<%@ attribute name="ordersPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <div id="Orders-block">
        <div class="above-table-row">
            <ul id="change" class="pagination above-table-row-content">
                <li id="oBack"><a href="#page">&laquo;</a></li>

                <c:forEach var="i" begin="1" end="${ordersPaginatedList.getPageCount()}">
                    <li value="${i}" name="page${i}" class="oNumbered page"><a href="#page${i}">${i}</a>
                    </li>
                </c:forEach>

                <li id="oNext"><a href="#page">&raquo;</a></li>
            </ul>

            <div class="switcher above-table-row-content">
                <select class="form-control" id="switchStatusOrder">
                    <option value="waiting"><fmt:message key="message.waiting"/></option>
                    <option value="active"><fmt:message key="message.active"/></option>
                    <option value="canceled"><fmt:message key="message.canceled"/></option>
                    <option value="executed"><fmt:message key="message.executed"/></option>
                    <option value="all"><fmt:message key="message.allorders"/></option>
                </select>
            </div>

            <div class="above-table-row-content rows-count">
                <t:rowsCount target="orders" pageName="dispatcher"
                             targetRowsCount="${ordersPaginatedList.getRowsPerPage()}"/>
            </div>
            <div class="clear"></div>
        </div>

        <div class="orderListHeight tab-pane" style="overflow-y: scroll">
            <c:choose>
                <c:when test="${ordersPaginatedList.getTotalRowsCount() > 0}">
                    <tOrder:ordersTable2 ordersPaginatedList="${ordersPaginatedList}"/>
                </c:when>
                <c:otherwise>
                    <t:recordsNotFound/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script>
        $(function () {
            $("li.oNumbered[value=" + 1 + "]").addClass("active");
        });
    </script>
</fmt:bundle>