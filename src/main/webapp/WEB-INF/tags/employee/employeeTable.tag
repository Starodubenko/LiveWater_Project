<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tEmployee" tagdir="/WEB-INF/tags/employee" %>
<%@ attribute name="paginatedList" type="com.epam.star.dao.util.PaginatedList" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientTable">
        <input type="hidden" id="pageNumber" value="${paginatedList.getPageNumber()}"/>
        <thead>
        <th><fmt:message key="clients.message.ID"/></th>
        <th><fmt:message key="clients.message.login"/></th>
        <th><fmt:message key="clients.message.password"/></th>
        <th><fmt:message key="clients.message.last.name"/></th>
        <th><fmt:message key="clients.message.first.name"/></th>
        <th><fmt:message key="clients.message.middle.name"/></th>
        <th><fmt:message key="clients.message.address"/></th>
        <th><fmt:message key="clients.message.telephone"/></th>
        <th><fmt:message key="clients.message.mobilephone"/></th>
        <th><fmt:message key="clients.message.identitycard"/></th>
        <th><fmt:message key="clients.message.workbook"/></th>
        <th><fmt:message key="clients.message.rnn"/></th>
        <th><fmt:message key="clients.message.sik"/></th>
        <th><fmt:message key="clients.message.position"/></th>
        <th><fmt:message key="clients.message.balance"/></th>
        <th><fmt:message key="clients.message.avatar"/></th>
        <th><fmt:message key="clients.message.discount"/></th>
        <th><fmt:message key="message.deleted"/></th>
        <th></th>
        <th><fmt:message key="message.edit"/></th>
        </thead>
        <tEmployee:employeeRow employees="${paginatedList}"/>
    </table>
</fmt:bundle>