<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tPayCard" tagdir="/WEB-INF/tags/payCard" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="payCardTable">
        <input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>
        <thead>
            <th><fmt:message key="clients.message.ID"/></th>
            <th><fmt:message key="payCard.message.serial.number"/></th>
            <th><fmt:message key="payCard.message.secret.number"/></th>
            <th><fmt:message key="payCard.message.balance"/></th>
            <th><fmt:message key="payCard.message.status"/></th>
            <th><fmt:message key="message.deleted"/></th>
            <th></th>
            <th><fmt:message key="message.edit"/></th>
        </thead>
        <tPayCard:payCardRow payCard="${paginatedList}"/>
    </table>
</fmt:bundle>