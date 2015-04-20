<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <option value=""><fmt:message key="message.no"/></option>
    <option value="number"><fmt:message key="orders.message.number"/></option>
    <option value="delivery_date"><fmt:message key="orders.message.delivery.date"/></option>
    <option value="delivery_time"><fmt:message key="orders.message.delivery.time"/></option>
    <option value="order_date"><fmt:message key="orders.message.order.date"/></option>
</fmt:bundle>