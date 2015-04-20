<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.personal.cabinet"/></title>
        <link rel='stylesheet'
              href='<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/css/bootstrap-datetimepicker.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
    </t:gHead>
    <t:gbody>
        <c:if test="${empty user}"><c:redirect url="/do/welcome"/></c:if>
        <t:payment balance="${clientBalance}"/>

        <div class="panel panel-default border client-avatar">
            <div class="image-block">
                <img class="avatar panel panel-default"
                     src="/LiveWater/image/${clientAvatar.getId()}/${clientAvatar.getFilename()}">
            </div>

            <a href="<c:url value="/do/editProfile"/>"><fmt:message key="client.edit.profile"/></a>
        </div>

        <div class="order-successful-message panel panel-default"
             <c:if test="${orderSuccessful ne 'ok'}">style="display: none"</c:if>>
            <div>Ваш заказ №${orderNumber} создан, ожидайте подтвеждения от диспетчера.</div>
            <button>Закрыть</button>
        </div>

        <div class="clear"></div>

        <div class="orders">
            <label><fmt:message key="client.information.orders.count"/>: ${ordersCount}</label>
        </div>

        <div class="clear"></div>

        <div class="panel-group orders" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading edit-panel-heading">
                    <h4 class="panel-title">
                        <a id="browse-orders" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            <img src="<c:url value="/style/img/plus.png"/>"/>
                            <fmt:message key="client.information.orders.browse"/>
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body" style="padding: 0">
                        <div class="orderList panel panel-default">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="createOrderLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="createOrderLabel">Create order</h4>
                    </div>
                    <form action="${pageContext.request.contextPath}/do/fastCreateOrder">
                        <div class="registration">

                            <div class="orderText form-group">
                                <label for="Date">Delivery date</label>
                                <input type="text" name="deliverydate" placeholder="Date"
                                       class="form-control datetimepicker"
                                       id="Date">
                            </div>
                            <div class="orderText form-group">
                                <label for="PeriodTime">Delivery time</label>
                                <select class="form-control" name="deliverytime" value="Time" class="form-control"
                                        id="PeriodTime">
                                    <c:forEach var="status" items="${periods}">
                                        <option>${status.period}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="orderText form-group">
                                <label for="GoodsName">Goods type</label>
                                <select class="form-control" name="goodsname" value="Goods name" class="form-control"
                                        id="GoodsName">
                                    <c:forEach var="goodss" items="${goods}">
                                        <option>${goodss.getGoodsName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="orderText form-group">
                                <label for="Count">Goods count</label>
                                <input type="text" name="goodscount" placeholder="Count" class="form-control"
                                       id="Count">
                            </div>
                            <div class="form-group">
                                <label for="Additional Information">Additional Information</label>
                            <textarea name="additionalinformation" value="Count" class="form-control"
                                      id="Additional Information">
                            </textarea>
                            </div>
                        </div>
                        <div class="paymentType form-group">
                            <label class="paymentTypeContent">Online</label><input type="radio" name="paymentType"
                                                                                   value="online">
                            <label class="paymentTypeContent">Cache</label><input type="radio" name="paymentType"
                                                                                  value="cache">
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close order form
                            </button>
                            <button type="submit" class="btn btn-primary">Create order</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="clear"></div>
    </t:gbody>
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/js/bootstrap-datetimepicker.js"/>"></script>
    <script src="<c:url value="/script/client.js"/>"></script>
    </html>
</fmt:bundle>

