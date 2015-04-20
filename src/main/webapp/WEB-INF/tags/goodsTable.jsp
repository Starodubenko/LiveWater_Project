<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${goodsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="message.ID"/></th>
            <th><fmt:message key="message.goods.name"/></th>
            <th><fmt:message key="message.price"/></th>
        </tr>
        <c:forEach var="entity" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${entity.getId()}</td>
                <td>${entity.getGoodsName()}</td>
                <td>${entity.getPrice()}</td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>