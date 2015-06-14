<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="filterForm">

        <div>
            <div class="center"><label for="goods_name"><fmt:message key="goods.message.goods.name"/></label></div>
            <input id="goods_name" name="goods_name" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="price"><fmt:message key="goods.message.price"/></label></div>
            <input id="price" name="price" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="width"><fmt:message key="goods.message.width"/></label></div>
            <input id="width" name="width" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="height"><fmt:message key="goods.message.height"/></label></div>
            <input id="height" name="height" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="cools"><fmt:message key="goods.message.cools"/></label></div>
            <input id="cools" name="cools" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="warms"><fmt:message key="goods.message.warms"/></label></div>
            <input id="warms" name="warms" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="watervalue"><fmt:message key="goods.message.watervalue"/></label></div>
            <input id="watervalue" name="watervalue" type="text" class="form-control edit-field filter-entity-field"/>

            <div class="center"><label for="sideboardvalue"><fmt:message key="goods.message.sideboardvalue"/></label></div>
            <input id="sideboardvalue" name="sideboardvalue" type="text" class="form-control edit-field filter-entity-field"/>
        </div>

        <button type="button" class="btn btn-primary" id="filter-button">
            <fmt:message key="button.apply"/>
        </button>
        <button type="button" class="btn btn-primary" id="filter-reset-button">
            <fmt:message key="button.reset"/>
        </button>
    </form>
</fmt:bundle>