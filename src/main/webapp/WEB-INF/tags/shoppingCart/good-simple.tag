<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="entry" type="java.util.Map.Entry" %>
<%@ attribute name="discount" type="java.lang.Integer" %>

<c:set var="good" value="${entry.key}"/>
<c:set var="count" value="${entry.value}"/>

<fmt:bundle basename="i18n.messages">
        <tr>
            <td>${good.goodsName}</td>
            <td>${count}</td>
            <td>${good.price * count}</td>
            <td>${good.price * count * (1 - discount / 100)}</td>
        </tr>
</fmt:bundle>