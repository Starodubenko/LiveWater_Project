<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="login"><fmt:message key="clients.message.login"/></option>
    <option value="firstname"><fmt:message key="clients.message.first.name"/></option>
    <option value="lastname"><fmt:message key="clients.message.last.name"/></option>
    <option value="middlename"><fmt:message key="clients.message.middle.name"/></option>
    <option value="address"><fmt:message key="clients.message.address"/></option>
    <option value="telephone"><fmt:message key="clients.message.telephone"/></option>
    <option value="mobilephone"><fmt:message key="clients.message.mobilephone"/></option>
    <option value="identitycard"><fmt:message key="clients.message.identitycard"/></option>
    <option value="workbook"><fmt:message key="clients.message.workbook"/></option>
    <option value="rnn"><fmt:message key="clients.message.rnn"/></option>
    <option value="sik"><fmt:message key="clients.message.sik"/></option>
    <option value="position_name"><fmt:message key="clients.message.position"/></option>
</fmt:bundle>