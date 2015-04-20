<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tClientD" tagdir="/WEB-INF/tags/client/client/forDispatcher" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientTable">
        <input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>
        <thead>
        <th><fmt:message key="clients.message.last.name"/></th>
        <th><fmt:message key="clients.message.first.name"/></th>
        <th><fmt:message key="clients.message.middle.name"/></th>
        <th><fmt:message key="clients.message.address"/></th>
        <th><fmt:message key="clients.message.telephone"/></th>
        <th><fmt:message key="clients.message.mobilephone"/></th>
        <th></th>
        <th><fmt:message key="message.edit"/></th>
        </thead>
        <tClientD:clientRow clients="${paginatedList}"/>
    </table>
</fmt:bundle>