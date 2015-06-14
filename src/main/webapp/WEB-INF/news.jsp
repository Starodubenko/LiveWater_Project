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

        <c:if test="${not empty user and user.role.positionName == 'Admin'}">
            <a class="add-article-ref" href="/LiveWater/do/article?addNews=ok">Добавить</a>
        </c:if>

        <c:forEach var="article" items="${articles}">
            <div class="news-block panel panel-default">
                <div class="news-block-header text-center" style="float: left; width: 85%;">
                    ${article.title}
                </div>
                <div class="news-block-header text-center" style="float: left; margin-left: 5px; width: 14%;">
                        ${article.getFormatedDate()}
                </div>
                <div class="clear"></div>
                <hr class="hr-header">
                <div class="news-block-content panel panel-default">
                    ${article.getShortText()} <c:if test="${article.getContent().length() > 300}">...</c:if>
                </div>
                <a class="news-article-link" href="/LiveWater/do/article?artId=${article.getId()}">Статья</a>

                <div class="clear"></div>
            </div>

            <script src="<c:url value="/script/news.js"/>"></script>
        </c:forEach>
    </t:gbody>
    </html>
</fmt:bundle>