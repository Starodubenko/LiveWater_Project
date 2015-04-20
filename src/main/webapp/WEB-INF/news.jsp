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

        <a class="add-article-ref" href="/LiveWater/do/article">Добавить</a>

        <c:forEach var="article" items="${articles}">
            <div class="news-block panel panel-default">
                <div class="news-block-header text-center">
                    ${article.title}
                </div>
                <hr class="hr-header">
                <div class="news-block-content panel panel-default">
                    ${article.getShortText()} <c:if test="${article.getText().length() > 300}">...</c:if>
                </div>
                <a class="news-article-link" href="/LiveWater/do/article?artId=${article.getId()}">Статья</a>

                <div class="clear"></div>
            </div>

            <script src="<c:url value="/script/news.js"/>"></script>
        </c:forEach>
    </t:gbody>
    </html>
</fmt:bundle>