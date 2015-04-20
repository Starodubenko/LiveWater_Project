<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:if test="${operation eq 'waiting'}">
        <button type="button" class="order-function-button cancelW" name="id" value="${id}"
                title="<fmt:message key="button.cancel.order"/>">
            <img src="/style/img/buttons/cancel.jpg"/>
        </button>
    </c:if>
    <c:if test="${operation eq 'active'}">
        <button type="button" class="order-function-button cancelA" name="id" value="${id}"
                title="<fmt:message key="button.cancel.order"/>">
            <img src="/style/img/buttons/cancel.jpg"/>
        </button>
    </c:if>
    <c:if test="${operation eq 'canceled'}">
        <button type="button" class="order-function-button restore" name="id" value="${id}"
                title="<fmt:message key="button.restore.order"/>">
            <img src="/style/img/buttons/restore.jpg"/>
        </button>
    </c:if>
    <c:if test="${operation eq 'executed'}">
        <button type="button" class="order-function-button repeat" name="id" value="${id}"
                title="<fmt:message key="button.repeat.order"/>">
            <img src="/style/img/buttons/repeat.jpg"/>
        </button>
    </c:if>
</fmt:bundle>