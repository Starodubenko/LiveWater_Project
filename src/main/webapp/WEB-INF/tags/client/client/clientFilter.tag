<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="filterForm">

        <div>
            <div class="center"><label for="login"><fmt:message key="clients.message.login"/></label></div>
            <input id="login" name="login" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="firstname"><fmt:message key="clients.message.first.name"/></label></div>
            <input id="firstname" name="firstname" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="lastname"><fmt:message key="clients.message.last.name"/></label></div>
            <input id="lastname" name="lastname" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="middlename"><fmt:message key="clients.message.middle.name"/></label></div>
            <input id="middlename" name="middlename" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="address"><fmt:message key="clients.message.address"/></label></div>
            <input id="address" name="address" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="telephone"><fmt:message key="clients.message.telephone"/></label></div>
            <input id="telephone" name="telephone" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="mobilephone"><fmt:message key="clients.message.mobilephone"/></label></div>
            <input id="mobilephone" name="mobilephone" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="position_name"><fmt:message key="clients.message.position"/></label></div>
            <select id="position_name" name="position_name" class="form-control edit-field filter-entity-field">
                <option value="">
                    All
                </option>
                <c:forEach var="discount" items="${positions}">
                    <option value="${discount}">
                            ${discount}
                    </option>
                </c:forEach>
            </select>

            <div class="center"><label for="virtual_balance"><fmt:message key="clients.message.balance"/></label></div>
            <input id="virtual_balance" name="virtual_balance" type="text"
                   class="form-control edit-field filter-entity-field"/>

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