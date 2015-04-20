<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<link rel='stylesheet' href='<c:url value="/style/navigation.css"/>'>

<fmt:bundle basename="i18n.messages">
    <link href='http://fonts.googleapis.com/css?family=Lobster&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <div class="background-navigation">
        <div class="farm-label">
            <a href="welcome">
                <img class="farm-label-image" src="<c:url value="/style/img/Label.jpg"/>" title="<fmt:message key="navigation.home"/>">
            </a>
        </div>

        <nav class="center">
            <ul class="fancyNav">
                <li id="news"><a href="news"><fmt:message key="navigation.news"/></a></li>
                <li id="about"><a href="about"><fmt:message key="navigation.aboutus"/></a></li>
                <li id="services"><a href="services"><fmt:message key="navigation.services"/></a></li>
                <c:if test="${not empty user}">
                    <li id="personalCabinet"><a href="personal-cabinet"><fmt:message
                            key="navigation.personal.cabinet"/></a></li>
                </c:if>
                <c:if test="${not empty user && user.getRole().getPositionName() ne 'Client'}">
                    <li id="office"><a href="office"><fmt:message key="navigation.work.office"/></a></li>
                </c:if>
            </ul>

            <div class="shopping-cart">
                <div>
                    <a href="<c:url value="/do/shoppingCart"/>"><fmt:message key="orders.message.goods.in.cart"/>:</a>
                    <label id="goodscountincart">${shoppingCart.getGoodsCount()}</label>
                </div>
                <div style="margin-top: -15px">
                    <fmt:message key="message.to.the.sum"/> <label class="total">${shoppingCart.getTotalSum()}</label>
                </div>
            </div>
        </nav>
    </div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/script/welcome.js"/>"></script>
</fmt:bundle>
