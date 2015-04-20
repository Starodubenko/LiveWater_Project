<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrders" tagdir="/WEB-INF/tags/order/order" %>

<fmt:bundle basename="i18n.messages">
    <c:if test="${empty userForOrder}">
        <div class="orderText form-group">
            <div class="center"><label for="lastname"><fmt:message key="clients.message.last.name"/></label></div>
            <input type="text" id="lastname" name="lastname" class="form-control">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="firstname"><fmt:message key="clients.message.first.name"/></label></div>
            <input type="text" id="firstname" name="firstname" class="form-control">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="middlename"><fmt:message key="clients.message.middle.name"/></label>
            </div>
            <input type="text" id="middlename" name="middlename" class="form-control">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="address"><fmt:message key="clients.message.address"/></label></div>
            <input type="text" id="address" name="address" class="form-control">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="telephone"><fmt:message key="clients.message.telephone"/></label></div>
            <input type="text" id="telephone" name="telephone" class="form-control">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="mobilephone"><fmt:message key="clients.message.mobilephone"/></label>
            </div>
            <input type="text" id="mobilephone" name="mobilephone" class="form-control">
        </div>
    </c:if>

    <c:if test="${not empty userForOrder}">
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.last.name"/> :</label>
            <label>${userForOrder.lastName}</label>
        </div>
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.first.name"/> :</label>
            <label>${userForOrder.firstName}</label>
        </div>
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.middle.name"/> :</label>
            <label>${userForOrder.middleName}</label>
        </div>
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.address"/> :</label>
            <label>${userForOrder.address}</label>
        </div>
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.telephone"/> :</label>
            <label>${userForOrder.telephone}</label>
        </div>
        <div class="orderText form-group">
            <label><fmt:message key="clients.message.mobilephone"/> :</label>
            <label>${userForOrder.mobilephone}</label>
        </div>
    </c:if>
    <form id="orderInfoForm" onsubmit="return false;">
        <div class="orderText form-group">
            <div class="center"><label for="date"><fmt:message key="orders.message.order.date"/></label></div>
            <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="date">
        </div>
        <div class="orderText form-group">
            <div class="center"><label for="periodTime"><fmt:message key="orders.message.delivery.time"/></label></div>
            <select class="form-control" name="deliverytime" value="Time" class="form-control" id="periodTime">
                <c:forEach var="status" items="${periods}">
                    <option value="${status.id}">${status.period}</option>
                </c:forEach>
            </select>
        </div>
        <div class="orderInfo form-group">
            <div class="center"><label for="additionalInformation"><fmt:message
                    key="orders.message.additional.info"/></label></div>
            <textarea name="additionalinformation" value="Count" class="form-control" id="additionalInformation">
            </textarea>
        </div>
    </form>

    <div class="add-rem-goods-block">
        <div class="goods-table panel panel-default">
            <div class="form-group">
                <label for="searchGoods"><fmt:message key="message.search.by.goods.name"/></label>
                <input type="text" id="searchGoods" name="searchGoods" class="form-control">
            </div>
            <table class="table table-hover goodsForDispatcher">

            </table>
        </div>

        <div class="ra-button-block">
            <button class="ra-button add"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </button>
            <button class="ra-button remove"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </button>
        </div>
        <div class="ordered-goods panel panel-default">
            <table class="table table-hover orderedGoodsForDispatcher">

            </table>
        </div>
        <div class="panel panel-default">
            <fmt:message key="orders.message.order.cost.without.discount"/> :<label id="totalPrice">0</label>
            <fmt:message key="discount"/> :<label id="discount">0</label>
            <fmt:message key="message.total.cost"/> :<label id="totalPriceWithDiscount">0</label>
        </div>
    </div>
</fmt:bundle>