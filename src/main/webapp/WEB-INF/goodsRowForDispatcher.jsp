<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <thead>
        <td><fmt:message key="orders.message.goods.name"/></td>
        <td><fmt:message key="orders.message.goods.cost"/></td>
    </thead>
    <c:forEach var="entity" items="${goods}">
            <tr>
                <td>
                    ${entity.getGoodsName()}
                </td>
                <td>
                    ${entity.getPrice()}
                </td>
            </tr>
    </c:forEach>
</fmt:bundle>