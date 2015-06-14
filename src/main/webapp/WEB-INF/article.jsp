<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.news"/></title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
        <link rel="stylesheet" href="<c:url value="/style/news.css"/>">
    </t:gHead>
    <t:gbody>
        <h1 class="news-header">Новости</h1>

        <form class="news-block panel panel-default">
            <input type="hidden" name="artId" value="${article.id}">

            <div class="news-block-header text-center">
                <c:if test="${not empty user and user.role.positionName == 'Admin'}">
                    <div class="form-group input-block">
                        <input type="text" name="title" class="form-control color-tooltip input"
                               style="width: 85%; float: left;"
                               value="<c:if test="${not empty article}">${article.title}</c:if>">
                        <input readonly="readonly" type="text" name="newsDate"
                               class="form-control color-tooltip input datepicker"
                               style="margin-left: 5px; width: 14%; float: left;"
                               value="<c:if test="${not empty article}">${article.getFormatedDate()}</c:if>">
                    </div>
                </c:if>
                <div class="news-block-header text-center" style="float: left; width: 85%;">
                        ${article.title}
                </div>
                <div class="news-block-header text-center" style="float: left; margin-left: 5px; width: 14%;">
                        ${article.getFormatedDate()}
                </div>

            </div>
            <div class="clear"></div>
            <hr class="hr-header">
            <div class="news-block-content panel panel-default">
                <c:if test="${not empty user and user.role.positionName == 'Admin'}"></c:if>
                <textarea name="content" class="form-control" rows="23"><c:if
                        test="${not empty article}">${article.content}</c:if></textarea>

                <div><c:if test="${not empty article}">${article.content}</c:if></div>
            </div>

            <div class="clear"></div>
        </form>

        <input id="addIsOk" type="hidden" value="${addNews}">

        <c:if test="${not empty user and user.role.positionName == 'Admin'}">
            <div class="article-operations">
                <button id="edit">Исправить</button>
                <button id="save">Сохраниь</button>
                <button id="add">Добавить</button>
                <button id="cancel">Отменить</button>
                <button id="remove">Удалить</button>
            </div>
        </c:if>

        <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
        <script src="<c:url value="/script/news.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>