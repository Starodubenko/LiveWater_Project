<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>

<fmt:setLocale value="${locale}"/>
<c:set var="entityName" value="goodsServices"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title>${goods.goodsName}</title>
        <link rel='stylesheet' href='<c:url value="/style/aboutGoods.css"/>'>
    </t:gHead>
    <t:gbody>
        <div class="goods-image">
            <img src="<c:url value="/image/${goods.getImage().getId()}/${goods.getImage().getFilename()}"/>"/>
        </div>
        <div class="info panel panel-default">
            <c:forEach begin="1" end="10" >
                <div class="goods-characteristic">
                    <label>Характеристика</label>
                    <div class="panel panel-default">Описание</div>
                </div>
            </c:forEach>
        </div>
    </t:gbody>

    <%--<script src="<c:url value="/script/aboutGoods.js"/>"></script>--%>
    </html>
</fmt:bundle>