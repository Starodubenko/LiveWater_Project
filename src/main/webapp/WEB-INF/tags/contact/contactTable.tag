<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tContact" tagdir="/WEB-INF/tags/contact" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="payCardTable">
        <input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>
        <thead>
            <th><fmt:message key="clients.message.ID"/></th>
            <th><fmt:message key="clients.message.telephone"/></th>
            <th><fmt:message key="message.owner"/></th>
            <th><fmt:message key="message.part"/></th>
            <th><fmt:message key="message.deleted"/></th>
            <th></th>
            <th><fmt:message key="message.edit"/></th>
        </thead>
        <tContact:contactRow contact="${paginatedList}"/>
    </table>
</fmt:bundle>