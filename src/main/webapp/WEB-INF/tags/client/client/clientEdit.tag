<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="clients" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">

    <div class="client-avatar">
        <div class="image-block">
            <img class="avatar panel panel-default" src="/image/${clientAvatar.getFilename()}">
        </div>
    </div>



    <div class="edit-field-block">
        <label class="field" for="password"><fmt:message key="clients.message.password"/></label>
        <input id="password" name="lastname" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="confirmpassword"><fmt:message key="clients.message.confirmpassword"/></label>
        <input id="confirmpassword" name="confirmpassword" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="firstname"><fmt:message key="clients.message.first.name"/></label>
        <input id="firstname" name="firstname" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">

        <label class="field" for="lastname"><fmt:message key="clients.message.last.name"/></label>
        <input id="lastname" name="lastname" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="middlename"><fmt:message key="clients.message.middle.name"/></label>
        <input id="middlename" name="middlename" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="address"><fmt:message key="clients.message.address"/></label>
        <input id="address" name="address" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="telephone"><fmt:message key="clients.message.telephone"/></label>
        <input id="telephone" name="telephone" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="mobilephone"><fmt:message key="clients.message.mobilephone"/></label>
        <input id="mobilephone" name="mobilephone" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="identitycard"><fmt:message key="clients.message.identitycard"/></label>
        <input id="identitycard" name="identitycard" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="workbook"><fmt:message key="clients.message.workbook"/></label>
        <input id="workbook" name="workbook" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="rnn"><fmt:message key="clients.message.rnn"/></label>
        <input id="rnn" name="rnn" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="sik"><fmt:message key="clients.message.sik"/></label>
        <input id="sik" name="sik" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="position"><fmt:message key="clients.message.position"/></label>
        <input id="position" name="position" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
    <div class="edit-field-block">
        <label class="field" for="deleted"><fmt:message key="clients.message.first.name"/></label>
        <input id="deleted" name="deleted" type="text" class="form-control edit-field" value="${entity.getPassword()}"/>
    </div>
</fmt:bundle>