<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.user.edit"/></title>
        <link rel='stylesheet'
              href='<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/css/bootstrap-datetimepicker.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
    </t:gHead>

    <t:gbody>
        <c:if test="${empty user}"><c:redirect url="/do/welcome"/></c:if>

        <div class="panel panel-default border client-avatar">

            <form id="image-form" method="post" enctype="multipart/form-data">
                <img class="image-block panel panel-default" src="/image/${userAvatar.getId()}/${userAvatar.getFilename()}">
                <input class="image" type="file" name="image" style="display: none;">

                <div class="image-block-buttons">
                    <button class="btn btn-default loginbtn" type="button" id="updateAvatar">
                        <fmt:message key="button.save.avatar"/>
                    </button>
                </div>
                <p class="text-center error-message" id="avatarInput"></p>
            </form>
        </div>

        <div class="panel panel-default border edit-fields">
            <label class="block-title"><fmt:message key="message.edit.password"/></label>

            <div class="panel panel-default border"></div>

            <form id="updatePasswordForm" onsubmit="return false;">
                <div class="edit-block edit-field-block" id="oldpasswordDiv">
                    <label class="field" for="oldpassword"><fmt:message key="clients.message.oldpassword"/></label>
                    <input id="oldpassword" name="oldpassword" type="password" class="form-control edit-field"/>

                    <p class="text-center error-message" id="oldpasswordInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="passwordDiv">
                    <label class="field" for="password"><fmt:message key="clients.message.newpassword"/></label>
                    <input id="password" name="password" type="password" class="form-control edit-field"/>

                    <p class="text-center error-message" id="passwordInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="confirmpasswordDiv">
                    <label class="field" for="confirmpassword"><fmt:message
                            key="clients.message.confirmpassword"/></label>
                    <input id="confirmpassword" name="confirmpassword" type="password" class="form-control edit-field"/>

                    <p class="text-center error-message" id="confirmpasswordInput"></p>
                </div>
                <div class="edit-block edit-field-block">
                    <button id="updatePassword" class="btn btn-default edit-field-block">
                        <fmt:message key="clients.message.save.password"/>
                    </button>
                    <p id="passwordmessage">

                    </p>
                </div>
            </form>

            <label class="block-title"><fmt:message key="message.edit.fullname"/></label>

            <div class="panel panel-default border"></div>

            <form id="updateFullNameForm" onsubmit="return false;">
                <div class="edit-block edit-field-block" id="firstnameDiv">
                    <label class="field" for="firstname"><fmt:message key="clients.message.first.name"/></label>
                    <input id="firstname" name="firstname" type="text" class="form-control edit-field"
                           value="${editUser.getFirstName()}"/>

                    <p class="text-center error-message" id="firstnameInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="lastnameDiv">
                    <label class="field" for="lastname"><fmt:message key="clients.message.last.name"/></label>
                    <input id="lastname" name="lastname" type="text" class="form-control edit-field"
                           value="${editUser.getLastName()}"/>

                    <p class="text-center error-message" id="lastnameInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="middlenameDiv">
                    <label class="field" for="middlename"><fmt:message key="clients.message.middle.name"/></label>
                    <input id="middlename" name="middlename" type="text" class="form-control edit-field"
                           value="${editUser.getMiddleName()}"/>

                    <p class="text-center error-message" id="middlenameInput"></p>
                </div>
                <div class="edit-block edit-field-block">
                    <button id="updateFullName" class="btn btn-default edit-field-block">
                        <fmt:message key="clients.message.save.fullnsme"/>
                    </button>
                    <p id="fullnamemessage">

                    </p>
                </div>
            </form>

            <label class="block-title"><fmt:message key="message.edit.contact.details"/></label>

            <div class="panel panel-default border"></div>

            <form id="updateContactDetailsForm" onsubmit="return false;">
                <div class="edit-block edit-field-block" id="addressDiv">
                    <label class="field" for="address"><fmt:message key="clients.message.address"/></label>
                    <input id="address" name="address" type="text" class="form-control edit-field"
                           value="${editUser.getAddress()}"/>

                    <p class="text-center error-message" id="addressInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="telephoneDiv">
                    <label class="field" for="telephone"><fmt:message key="clients.message.telephone"/></label>
                    <input id="telephone" name="telephone" type="text" class="form-control edit-field"
                           value="${editUser.getTelephone()}"/>

                    <p class="text-center error-message" id="telephoneInput"></p>
                </div>
                <div class="edit-block edit-field-block" id="mobilephoneDiv">
                    <label class="field" for="mobilephone"><fmt:message key="clients.message.mobilephone"/></label>
                    <input id="mobilephone" name="mobilephone" type="text" class="form-control edit-field"
                           value="${editUser.getMobilephone()}"/>

                    <p class="text-center error-message" id="mobilephoneInput"></p>
                </div>
                <div class="edit-block edit-field-block">
                    <button id="updateContactDetails" class="btn btn-default edit-field-block">
                        <fmt:message key="clients.message.save.contacts.details"/>
                    </button>
                    <p id="contactdetailsmessage">

                    </p>
                </div>
            </form>

            <c:if test="${user.getRole().getPositionName() eq 'Admin'}">
                <div class="panel panel-default border"></div>

                <form id="updatePositionForm" onsubmit="return false;">
                    <div class="edit-block edit-field-block">
                        <div class="field"><label for="position_name"><fmt:message
                                key="clients.message.position"/></label>
                        </div>
                        <select id="position_name" name="position_name"
                                class="form-control edit-field filter-entity-field">
                            <c:forEach var="discount" items="${positions}">
                                <option value="${discount.getPositionName()}"
                                        <c:if test="${editUser.getRole().getPositionName() eq discount.getPositionName()}">selected</c:if>>
                                        ${discount.getPositionName()}
                                </option>
                            </c:forEach>
                        </select>

                        <p id="positionmessage">

                        </p>
                    </div>
                </form>
                <form id="updateDiscountForm" onsubmit="return false;">
                    <div class="edit-block edit-field-block">
                        <div class="field"><label for="discount"><fmt:message key="clients.message.discount"/></label></div>
                        <select id="discount" name="discount"
                                class="form-control edit-field filter-entity-field">
                            <c:forEach var="discount" items="${discounts}">
                                <option value="${discount.getName()}"
                                        <c:if test="${editUser.getDiscount().getName() eq discount.getName()}">selected</c:if>>
                                        ${discount.getPercentage()}
                                </option>
                            </c:forEach>
                        </select>

                        <p id="discountmessage">

                        </p>
                    </div>
                </form>
            </c:if>

            <c:if test="${user.getRole().getPositionName() eq 'Admin'}">
                <div class="employee-details">
                    <label class="block-title"><fmt:message key="message.edit.employe.details"/></label>

                    <div class="panel panel-default border"></div>

                    <form id="updateEmployeeDetailsForm" onsubmit="return false;">
                        <div class="edit-block edit-field-block" id="identitycardDiv">
                            <label class="field" for="identitycard"><fmt:message
                                    key="clients.message.identitycard"/></label>
                            <input id="identitycard" name="identitycard" type="text" class="form-control edit-field"
                                   value="${editUser.getIdentityCard()}"/>

                            <p class="text-center error-message" id="identitycardInput"></p>
                        </div>
                        <div class="edit-block edit-field-block" id="workbookDiv">
                            <label class="field" for="workbook"><fmt:message key="clients.message.workbook"/></label>
                            <input id="workbook" name="workbook" type="text" class="form-control edit-field"
                                   value="${editUser.getWorkBook()}"/>

                            <p class="text-center error-message" id="workbookInput"></p>
                        </div>
                        <div class="edit-block edit-field-block" id="rnnDiv">
                            <label class="field" for="rnn"><fmt:message key="clients.message.rnn"/></label>
                            <input id="rnn" name="rnn" type="text" class="form-control edit-field"
                                   value="${editUser.getRNN()}"/>

                            <p class="text-center error-message" id="rnnInput"></p>
                        </div>
                        <div class="edit-block edit-field-block" id="sikDiv">
                            <label class="field" for="sik"><fmt:message key="clients.message.sik"/></label>
                            <input id="sik" name="sik" type="text" class="form-control edit-field"
                                   value="${editUser.getSIK()}"/>

                            <p class="text-center error-message" id="sikInput"></p>
                        </div>
                        <div class="edit-block edit-field-block">
                            <button class="btn btn-default edit-field-block" id="updateEmployeeDetails">
                                <fmt:message key="clients.message.save.employee.details"/>
                            </button>
                            <p id="empldetailsmessage">

                            </p>
                        </div>
                    </form>

                    <label class="block-title"><fmt:message key="message.edit.user.ban"/></label>

                    <div class="panel panel-default border"></div>
                    <div class="edit-block">
                        <input type="checkbox" name="deleted" class="checkbox edit" id="ban"
                               <c:if test="${editUser.isDeleted()}">checked</c:if>/>
                        <label class="checkbox-label" for="ban"></label>

                        <p id="banmessage">

                        </p>
                    </div>
                </div>
            </c:if>
        </div>

        <div class="clear"></div>
    </t:gbody>
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/js/bootstrap-datetimepicker.js"/>"></script>
    <script src="<c:url value="/script/editUser.js"/>"></script>
    </html>
</fmt:bundle>

