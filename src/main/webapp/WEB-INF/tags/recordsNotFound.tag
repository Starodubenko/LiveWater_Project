<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <link rel='stylesheet' href='<c:url value="/style/recordsNotFound.css"/>'>
    <div class="text-center records-not-found-block">
        <div class="message">
            <fmt:message key="message.records-not-found"/>
        </div>
    </div>
</fmt:bundle>
