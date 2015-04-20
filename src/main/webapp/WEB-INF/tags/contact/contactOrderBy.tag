<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="telephone"><fmt:message key="clients.message.telephone"/></option>
    <option value="owner"><fmt:message key="message.owner"/></option>
    <option value="part"><fmt:message key="message.part"/></option>
</fmt:bundle>