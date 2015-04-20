<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="filterForm">

        <div>
            <div class="center"><label for="serial_number"><fmt:message key="message.serial.number"/></label></div>
            <input id="serial_number" name="serial_number" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="secret_number"><fmt:message key="message.secret.number"/></label></div>
            <input id="secret_number" name="secret_number" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="balance"><fmt:message key="message.balance"/></label></div>
            <input id="balance" name="balance" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="card_status"><fmt:message key="status"/></label></div>
            <select id="card_status" name="card_status" class="form-control edit-field filter-entity-field">
                <c:forEach var="discount" items="${cardStatuses}">
                    <option value="">All</option>
                    <option  value="${discount}">
                            ${discount}
                    </option>
                </c:forEach>
            </select>

            <div class="center"><label for="deleted"><fmt:message key="message.deleted"/></label></div>
            <input id="deleted" name="deleted" type="checkbox" class="form-control edit-field filter-entity-field"/>
        </div>

        <button type="button" class="btn btn-primary" id="filter-button">
            <fmt:message key="button.apply"/>
        </button>
        <button type="button" class="btn btn-primary" id="filter-reset-button">
            <fmt:message key="button.reset"/>
        </button>
    </form>
</fmt:bundle>