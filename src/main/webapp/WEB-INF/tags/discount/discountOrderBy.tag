<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="name"><fmt:message key="message.case.name"/></option>
    <option value="discount_percentage"><fmt:message key="discount.message.percentage"/></option>
</fmt:bundle>