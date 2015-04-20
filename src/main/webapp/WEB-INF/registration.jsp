<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="button.registration"/></title>
        <link rel="stylesheet" href="<c:url value="/style/registration.css"/> ">
    </t:gHead>
    <t:gbody>
        <c:if test="${not empty user}"><c:redirect url="/do/welcome"/></c:if>
        <div class="border">
            <form id="regForm" class="registration" method="post" onsubmit="return false;">
                <div class="form-group input-block" id="loginDiv">
                    <label for="Login" class="field-description"><fmt:message key="clients.message.login"/></label>
                    <input type="text" name="login" placeholder="Ivanov99" class="form-control color-tooltip input" id="Login" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.login.describe"/>"><label class="necessary-mark"> *</label>

                    <p class="text-center error-message" id="loginInput"></p>
                </div>
                <div class="form-group input-block" id="passwordDiv">
                    <label for="Password" class="field-description"><fmt:message key="clients.message.password"/></label>
                    <input type="password" name="password" class="form-control color-tooltip input" id="Password" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.password.describe"/>"><label class="necessary-mark"> *</label>

                    <p class="text-center error-message" id="passwordInput"></p>
                </div>
                <div class="form-group input-block" id="confirmpasswordDiv">
                    <label for="ConfirmPassword" class="field-description"><fmt:message key="clients.message.confirmpassword"/></label>
                    <input type="password" name="confirmpassword" class="form-control color-tooltip input" id="ConfirmPassword" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.password.describe"/>"><label class="necessary-mark"> *</label>

                    <p class="text-center error-message" id="confirmpasswordInput"></p>
                </div>
                <div class="form-group input-block" id="lastnameDiv">
                    <label for="Lastname" class="field-description"><fmt:message key="clients.message.last.name"/></label>
                    <input type="text" name="lastname" placeholder="Ivanov" class="form-control color-tooltip input" id="Lastname" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.lastname.describe"/>">

                    <p class="text-center error-message" id="lastnameInput"></p>
                </div>
                <div class="form-group input-block" id="firstnameDiv">
                    <label for="Firstname" class="field-description"><fmt:message key="clients.message.first.name"/></label>
                    <input type="text" name="firstname" placeholder="Ivan" class="form-control color-tooltip input" id="Firstname" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.firstname.describe"/>">

                    <p class="text-center error-message" id="firstnameInput"></p>
                </div>
                <div class="form-group input-block" id="middlenameDiv">
                    <label for="Middlename" class="field-description"><fmt:message key="clients.message.middle.name"/></label>
                    <input type="text" name="middlename" placeholder="Ivanovich" class="form-control color-tooltip input" id="Middlename" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.middlename.describe"/>">

                    <p class="text-center error-message" id="middlenameInput"></p>
                </div>
                <div class="form-group input-block" id="addressDiv">
                    <label for="Address" class="field-description"><fmt:message key="clients.message.address"/></label>
                    <input type="text" name="address" placeholder="Ivanova-32" class="form-control color-tooltip input" id="Address" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.address.describe"/>"><label class="necessary-mark"> *</label>

                    <p class="text-center error-message" id="addressInput"></p>
                </div>
                <div class="form-group input-block" id="telephoneDiv">
                    <label for="Telephone" class="field-description"><fmt:message key="clients.message.telephone"/></label>
                    <input type="text" name="telephone" placeholder="87212965896" class="form-control color-tooltip input" id="Telephone" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.telephone.describe"/>">

                    <p class="text-center error-message" id="telephoneInput"></p>
                </div>
                <div class="form-group input-block" id="mobilephoneDiv">
                    <label for="Mobilephone" class="field-description"><fmt:message key="clients.message.mobilephone"/></label>
                    <input type="text" name="mobilephone" placeholder="87007778958" class="form-control color-tooltip input"
                           id="Mobilephone" data-toggle="tooltip" data-placement="right" title="<fmt:message key="message.mobilephone.describe"/>"><label class="necessary-mark"> *</label>

                    <p class="text-center error-message" id="mobilephoneInput"></p>
                </div>
            </form>
            <div class="input-block reg-button">
                <button type="button" class="btn btn-primary" id="goRegistration"><fmt:message
                        key="message.sing.up"/>
                </button>
                <div id="message">

                </div>
            </div>
        </div>

        <div class="modal fade" id="Reg-successful" tabindex="-1" role="dialog" aria-labelledby="RegLabel"
             aria-hidden="true"
                data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title text-center" id="RegLabel"><fmt:message
                                    key="message.registration.successful"/></h4>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" id="Reg-Ok" style="float: left"><fmt:message key="button.go.back.to.welcome"/></button>
                            <button class="btn btn-primary" id="Reg-Go-To-PC"><fmt:message key="button.go.to.pc"/></button>
                        </div>
                    </div>
                </div>
        </div>

        <script src="<c:url value="/script/registration.js"/>"></script>
        <script src="<c:url value="/script/pswStrength.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>