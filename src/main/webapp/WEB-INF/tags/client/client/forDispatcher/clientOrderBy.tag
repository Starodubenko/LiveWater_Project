<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="firstname"><fmt:message key="clients.message.first.name"/></option>
    <option value="lastname"><fmt:message key="clients.message.last.name"/></option>
    <option value="middlename"><fmt:message key="clients.message.middle.name"/></option>
    <option value="address"><fmt:message key="clients.message.address"/></option>
    <option value="telephone"><fmt:message key="clients.message.telephone"/></option>
    <option value="mobilephone"><fmt:message key="clients.message.mobilephone"/></option>
</fmt:bundle>