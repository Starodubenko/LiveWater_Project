<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<%@ taglib prefix="tOrder2" tagdir="/WEB-INF/tags/order/order" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <ul class="nav nav-tabs  nav-justified" role="tablist">
        <li class="active"><a href="#Today" role="tab" data-toggle="tab"><fmt:message
                key="client.information.today"/></a></li>
        <li><a href="#HistoryOrders" role="tab" data-toggle="tab"><fmt:message key="client.information.history"/></a>
        </li>
        <li><a href="#AllOrders" role="tab" data-toggle="tab"><fmt:message key="client.information.all"/></a></li>
    </ul>
    <div class="tab-content">
        <div class="orderListHeight tab-pane active" id="Today" style="overflow: scroll">
            <tOrder2:orderTableForClient list="${todayOrders}"/>
        </div>
        <div class="orderListHeight tab-pane" id="HistoryOrders" style="overflow: scroll">
            <tOrder2:orderTableForClient list="${pastOrders}"/>
        </div>
        <div class="orderListHeight tab-pane" id="AllOrders" style="overflow: scroll">
            <tOrder2:orderTableForClient list="${allOrders}"/>
        </div>
    </div>
</fmt:bundle>