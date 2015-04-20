<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>

<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.services"/></title>
        <link rel="stylesheet" href="<c:url value="/style/services.css"/>">
    </t:gHead>
    <t:gbody>
        <div class="panel panel-default goods">
            <tGoods:goodsShowcase goodsPaginatedList="${goodsPaginatedList}"/>
        </div>

        <div class="clear"></div>
    </t:gbody>
    <script src="<c:url value="/script/services.js"/>"></script>
    </html>
</fmt:bundle>