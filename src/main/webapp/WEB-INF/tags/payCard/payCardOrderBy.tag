<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="serial_number"><fmt:message key="message.serial.number"/></option>
    <option value="secret_number"><fmt:message key="message.secret.number"/></option>
    <option value="balance"><fmt:message key="message.balance"/></option>d
    <option value="status_name"><fmt:message key="payCard.message.status"/></option>d
</fmt:bundle>