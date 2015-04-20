<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>

<link rel='stylesheet' href='<c:url value="/style/authentication.css"/>'>

<fmt:bundle basename="i18n.messages">
    <div class="log-in-out-forms">
        <form class="location" method="post" action="<c:url value="/do/changeLocale"/>">
            <select class="form-control language" id="switchLanguage" onchange="submit()" name="locale">
                <option class="language-icon"
                        <c:if test="${locale == 'ru'}">selected</c:if> value="ru"><fmt:message
                        key="navigation.rus"/></option>
                <option class="language-icon"
                        <c:if test="${locale == 'en'}">selected</c:if> value="en"><fmt:message
                        key="navigation.eng"/>
                </option>
            </select>
        </form>

        <c:if test="${not empty user}">
            <div id="autorized" class="panel panel-default">
                <label class="welcome-label">${user.getRole().getPositionName()}: </label>
                <label class="name-label">${user.getFirstName()}</label>
                <label class="name-label">${user.getLastName()}</label>
                <a href="<c:url value="/do/logout"/>">[<fmt:message key="button.logout"/>]</a>
                <div class="clear"></div>
            </div>
        </c:if>
        <c:if test="${empty user}">
            <div id="unAutorized" class="panel panel-default">
                <form id="loginform" method="post">
                    <a href="<c:url value="/do/registration"/>"><fmt:message key="button.registration"/></a>

                    <div class="field-block">
                        <label class="login-label" for="inputLogin" style="margin-left: 5px"><fmt:message
                                key="authentication.login"/></label>
                        <input type="text" name="authenticationLogin" class="login-fields form-control" id="inputLogin"
                               value="">
                    </div>
                    <div class="field-block">
                        <label class="login-label" for="inputPassword"><fmt:message
                                key="authentication.password"/></label>
                        <input type="password" name="authenticationPassword" class="login-fields form-control"
                               id="inputPassword" value="">
                    </div>

                    <input type="button" class="loginbtn btn-cust" value="<fmt:message key="button.login"/>"
                           id="goLogin">
                    <br/>

                    <p class="log-in-error" id="errorLogin"></p>
                </form>
                <div class="clear"></div>
            </div>
        </c:if>
        <div class="clear"></div>
    </div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/script/authentication.js"/>"></script>
</fmt:bundle>