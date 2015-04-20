<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.news"/></title>
        <link rel="stylesheet" href="<c:url value="/style/news.css"/>">
    </t:gHead>
    <t:gbody>
        <h1 class="news-header">Новости</h1>

        <div class="news-block panel panel-default">
            <div class="news-block-header text-center">
                <div class="form-group input-block">
                    <input type="text" name="title" class="form-control color-tooltip input" value="<c:if test="${not empty article}">${article.title}</c:if>">
                </div>
                <div><c:if test="${not empty article}">${article.title}</c:if></div>
            </div>
            <hr class="hr-header">
            <div class="news-block-content panel panel-default">
                <textarea class="form-control" rows="23"><c:if test="${not empty article}">${article.text}</c:if></textarea>
                <div><c:if test="${not empty article}">${article.text}</c:if></div>
            </div>

            <div class="clear"></div>
        </div>

        <div class="article-operations">
            <button id="edit">Исправить</button>
            <button id="save">Сохраниь</button>
            <button id="cancel">Отменить</button>
            <button id="remove">Удалить</button>
        </div>

        <script src="<c:url value="/script/news.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>