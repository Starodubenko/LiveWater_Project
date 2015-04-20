<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<%@ taglib prefix="tOrders" tagdir="/WEB-INF/tags/order/order" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="farm.dispather"/></title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/dispatcher.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/admin.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
    </t:gHead>
    <t:gbody>
        <c:if test="${empty user}"><c:redirect url="/do/welcome"/></c:if>
        <div class="panel panel-default dataBase">
            <div style="min-height: 10%; float: left">
                <div class="filter-entity panel panel-default">
                    <label style="{text-decoration-color: #ffffff}">Фильтр</label>

                    <div class="filter">

                    </div>
                </div>
            </div>

            <div class="table-name form-group">
                <select class="form-control" name="entityName" id="entityName" class="form-control">
                    <option value="clientD"><fmt:message key="client"/></option>
                    <option value="order2D"><fmt:message key="order2"/></option>
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

        <div class="modal fade bs-example-modal-lg" id="myModel" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel"><fmt:message
                                key="order.checkout.message.header"/></h4>
                    </div>
                    <div id="orderForm">

                    </div>
                    <p class="errormessage"></p>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="create"><fmt:message
                                key="button.create.order"/></button>
                    </div>
                </div>
            </div>
        </div>
    </t:gbody>

    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/script/dispatcher.js"/>"></script>
    <script src="<c:url value="/script/admin.js"/>"></script>
    <script src="<c:url value="/script/session-plugin.js"/>"></script>
    </html>
</fmt:bundle>


