<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.goods.edit"/></title>
        <link rel='stylesheet'
              href='<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/css/bootstrap-datetimepicker.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
    </t:gHead>

    <t:gbody>
        <c:if test="${empty user}"><c:redirect url="/do/welcome"/></c:if>

        <label class="block-title"><fmt:message key="message.addOrEditGoods"/></label>

        <form id="goods-form" method="post" enctype="multipart/form-data" onsubmit="return false;">
            <input type="hidden" id="id" value="${editGoods.getId()}">
            <div class="panel panel-default border client-avatar">
                    <c:choose>
                        <c:when test="${empty goodsImage}">
                            <img class="image-block panel panel-default" src="/style/img/no_photo.png">
                        </c:when>
                        <c:otherwise>
                            <img class="image-block panel panel-default" src="/image/${goodsImage.getId()}/${goodsImage.getFilename()}">
                        </c:otherwise>
                    </c:choose>
                    <input class="image" type="file" name="goodsImageFileName" style="display: none;">

                    <p class="text-center error-message" id="avatarInput"></p>
            </div>
            <div class="panel panel-default border edit-fields">
                    <div class="edit-block edit-field-block" id="goodsnameDiv">
                        <label class="field" for="goodsname"><fmt:message key="goods.message.goods.name"/></label>
                        <input id="goodsname" name="goodsname" type="text" class="form-control edit-field" value="<c:if test="${not empty editGoods}">${editGoods.getGoodsName()}</c:if>"/>

                        <p class="text-center error-message" id="goodsnameInput"></p>
                    </div>
                    <div class="edit-block edit-field-block" id="priceDiv">
                        <label class="field" for="price"><fmt:message key="goods.message.price"/></label>
                        <input id="price" name="price" type="text" class="form-control edit-field" value="<c:if test="${not empty editGoods}">${editGoods.getPrice()}</c:if>"/>

                        <p class="text-center error-message" id="passwordInput"></p>
                    </div>
            </div>
            <div class="edit-field-block-button">
                <c:if test="${purpose eq 'update'}">
                    <button id="updateGoods" class="btn btn-default edit-field-block">
                        <fmt:message key="clients.message.save.goods"/>
                    </button>
                </c:if>
                <c:if test="${purpose eq 'add'}">
                    <button id="addGoods" class="btn btn-default edit-field-block">
                        <fmt:message key="clients.message.add.goods"/>
                    </button>
                </c:if>
                <p id="goodsmessage">

                </p>
            </div>
        </form>


        <div class="clear"></div>
    </t:gbody>
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/js/bootstrap-datetimepicker.js"/>"></script>
    <script src="<c:url value="/script/editUser.js"/>"></script>
    </html>
</fmt:bundle>

