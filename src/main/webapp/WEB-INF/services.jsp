<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>

<fmt:setLocale value="${locale}"/>
<c:set var="entityName" value="goodsServices"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.services"/></title>
        <link rel="stylesheet" href="<c:url value="/style/services.css"/>">
        <link rel="stylesheet" href="<c:url value="/style/admin.css"/>">
    </t:gHead>
    <t:gbody>
        <%--<div class="panel panel-default goods">--%>
            <%--<tGoods:goodsShowcase goodsPaginatedList="${goodsPaginatedList}"/>--%>
        <%--</div>--%>\


        <div class="panel panel-default dataBase">
            <div style="min-height: 10%; float: left">
                <div class="filter-entity panel panel-default">
                    <label style="{text-decoration-color: #ffffff}"><fmt:message key="message.filter"/></label>

                    <div class="filter">

                    </div>
                </div>
            </div>

            <div class="table-name form-group" style="{display: none;}">
                <select class="form-control" name="entityName" id="entityName" class="form-control">
                        <option value="goodsS" selected></option>
                </select>
            </div>

            <div class="order-by-block">
                <label style="float: left; margin-top: 5px">Сортировать |</label>
                <div class="order-by-select">
                    <div class="input-group-btn">
                        <select class="form-control" name="orderBy" id="orderBy">
                            <option value=""><fmt:message key="message.no"/></option>
                        </select>
                    </div>
                    <div class="input-group-btn">
                        <select class="form-control" name="orderByType" id="orderByType" class="form-control">
                            <option value="asc" <c:if test="${orderType eq 'asc'}">selected</c:if>>
                                <fmt:message key="asc"/>
                            </option>
                            <option value="desc" <c:if test="${orderType eq 'desc'}">selected</c:if>>
                                <fmt:message key="desc"/>
                            </option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="panel panel-default table-data-base">
                <c:if test="${not empty entityName}"><t:genericEntityBlock/></c:if>
            </div>
        </div>
        <div class="clear"></div>
    </t:gbody>
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/script/services.js"/>"></script>
    <script src="<c:url value="/script/admin.js"/>"></script>
    </html>
</fmt:bundle>