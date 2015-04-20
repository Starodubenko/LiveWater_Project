<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="filterForm">

        <div>
            <div class="center"><label for="filename"><fmt:message key="message.filename"/></label></div>
            <input id="filename" name="filename" type="text" class="form-control edit-field filter-entity-field"/>

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