<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="filterForm">
        <div>
            <div class="center"><label for="number"><fmt:message key="orders.message.number"/></label></div>
            <input id="number" name="number" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="delivery_date"><fmt:message key="orders.message.delivery.date"/></label></div>
            <input id="delivery_date" name="delivery_date" type="text" class="form-control edit-field filter-entity-field datepickerNotBlocked"/>

            <div class="center"><label for="delivery_time"><fmt:message key="orders.message.delivery.time"/></label></div>
            <select id="delivery_time" name="delivery_time" class="form-control edit-field filter-entity-field">
                <option value="">All</option>
                <c:forEach var="status" items="${periods}">
                    <option  value="${status.id}">
                            ${status.period}
                    </option>
                </c:forEach>
            </select>

            <div class="center"><label for="order_date"><fmt:message key="orders.message.order.date"/></label></div>
            <input id="order_date" name="order_date" type="text" class="form-control edit-field filter-entity-field datepickerNotBlocked"/>

            <div class="center"><label for="paid"><fmt:message key="orders.message.paid"/></label></div>
            <input id="paid" name="paid" type="checkbox" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="order_status"><fmt:message key="orders.message.status"/></label></div>
            <select id="order_status" name="order_status" class="form-control edit-field filter-entity-field">
                <option value="">All</option>
                <c:forEach var="discount" items="${statuses}">
                    <option  value="${discount.id}">
                            ${discount.statusName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="button" class="btn btn-primary" id="filter-button">
            <fmt:message key="button.apply"/>
        </button>
        <button type="button" class="btn btn-primary" id="filter-reset-button">
            <fmt:message key="button.reset"/>
        </button>
    </form>
</fmt:bundle>