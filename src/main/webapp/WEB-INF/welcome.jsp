<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="farm.name"/></title>
        <link rel='stylesheet' href='<c:url value="/style/welcome.css"/>'>
    </t:gHead>
    <t:gbody>

        <div class="banner panel panel-default">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<c:url value="/style/img/water/water.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/style/img/water/water1.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/style/img/water/water2.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>

        <div class="image-link-blocks">
            <div class="panel panel-default image-link-block">
                <a href="about">
                    <img class="image-link" src="<c:url value="/style/img/about-us.jpg"/>" title="<fmt:message key="navigation.aboutus"/>">
                </a>
            </div>

            <div class="panel panel-default image-link-block">
                <a href="services">
                    <img class="image-link" src="<c:url value="/style/img/goods.jpg"/>" title="<fmt:message key="navigation.services"/>">
                </a>
            </div>

            <div class="panel panel-default image-link-block">
                <a>
                    <img class="image-link" src="<c:url value="/style/img/order.jpg"/>" title="<fmt:message key="navigation.create.order"/>">
                </a>
            </div>
        </div>

        <div class="clear"></div>

        <script src="<c:url value="/script/welcome.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>
