<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="balance" type="java.math.BigDecimal" %>
<link rel='stylesheet' href='<c:url value="/style/payment.css"/>'>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:if test="${not empty user}">
        <div class="client-info">
            <div class="eWalet panel panel-default">
                <label class="balance"> <fmt:message key="payment.message.your.balance"/>: </label>
                <label class="balance-value"> ${balance} </label>
                <br>
                <button class="topup btn btn-primary" data-toggle="modal" data-target="#Payment">
                    <fmt:message key="button.topup"/>
                </button>
            </div>
            <p class="text-center error-message" id="paymentInput"></p>
        </div>
    </c:if>

    <div class="modal fade" id="Payment" tabindex="-1" role="dialog" aria-labelledby="PaymentLabel" aria-hidden="true">
        <form id="payment-form" onsubmit="return false;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center" id="PaymentLabel"><fmt:message
                                key="payment.message.header"/></h4>
                    </div>
                    <div class="paymentform">
                        <div class="form-group">
                            <div class="center">
                                <p for="SecretCode"><fmt:message key="payment.message.explanation"/></p>
                            </div>
                            <div class="center">
                                <input type="text" name="SecretCode"
                                       placeholder="<fmt:message key="payment.message.input"/>"
                                       class=" center input-field form-control" id="SecretCode">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="payment-message text-center"></div>
                        <button type="submit" class="btn btn-primary" id="Pay"><fmt:message key="button.pay"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script src="<c:url value="/script/payment.js"/>"></script>
</fmt:bundle>
