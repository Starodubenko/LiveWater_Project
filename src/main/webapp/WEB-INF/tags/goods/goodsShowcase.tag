<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>
<%@ attribute name="goodsPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<fmt:setLocale value="${locale}"/>

<c:set var="whatCount" value="goods.count"/>

<fmt:bundle basename="i18n.messages">
    <div id="Goods-block">
        <div class="above-table-row">
            <ul id="changee" class="pagination above-table-row-content">
                <li id="back"><a href="#page">&laquo;</a></li>

                <c:forEach var="i" begin="1" end="${goodsPaginatedList.getPageCount()}">
                    <li value="${i}" name="page${i}" class="numbered page"><a href="#page${i}">${i}</a>
                    </li>
                </c:forEach>

                <li id="next"><a href="#page">&raquo;</a></li>
            </ul>

            <div class="above-table-row-content rows-count">
                <t:rowsCount target="goods" pageName="services" whatCount="${whatCount}"
                             targetRowsCount="${goodsPaginatedList.getRowsPerPage()}"/>
            </div>
            <div class="clear"></div>
        </div>

        <div id="Goods" style="overflow-y: scroll">
            <c:choose>
                <c:when test="${goodsPaginatedList.getTotalRowsCount() > 0}">
                    <tGoods:goods goods="${goodsPaginatedList}"/>
                </c:when>
                <c:otherwise>
                    <t:recordsNotFound/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</fmt:bundle>