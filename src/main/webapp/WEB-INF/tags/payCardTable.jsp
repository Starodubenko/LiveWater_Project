<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${payCardsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="message.ID"/></th>
            <th><fmt:message key="message.serial.number"/></th>
            <th><fmt:message key="message.secret.number"/></th>
            <th><fmt:message key="message.balance"/></th>
            <th><fmt:message key="status.name"/></th>
        </tr>
        <c:forEach var="entity" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${entity.getId()}</td>
                <td>${entity.getSerialNumber()}</td>
                <td>${entity.getSecretNumber()}</td>
                <td>${entity.getBalance()}</td>
                <td>${entity.getStatusPayCard().getStatusName()}</td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>
