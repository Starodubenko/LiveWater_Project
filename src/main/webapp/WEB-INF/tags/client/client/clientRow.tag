<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="clients" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${clients}">
        <tr>
            <td>
                <label class="field">${entity.getId()}</label>
            </td>
            <td>
                <label class="field">${entity.getLogin()}</label>
            </td>
            <td>
                <label class="field" for="lastname${entity.getId()}">${entity.getLastName()}</label>
                <input id="lastname${entity.getId()}" name="lastname" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getLastName()}"/>
            </td>
            <td><label class="field" for="firstname${entity.getId()}">${entity.getFirstName()}</label>
                <input id="firstname${entity.getId()}" name="firstname" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getFirstName()}"/>
            </td>
            <td>
                <label class="field" for="middlename${entity.getId()}">${entity.getMiddleName()}</label>
                <input id="middlename${entity.getId()}" name="middlename" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getMiddleName()}"/>
            </td>
            <td>
                <label class="field" for="address${entity.getId()}">${entity.getAddress()}</label>
                <input id="address${entity.getId()}" name="address" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getAddress()}"/>
            </td>
            <td>
                <label class="field" for="telephone${entity.getId()}">${entity.getTelephone()}</label>
                <input id="telephone${entity.getId()}" name="telephone" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getTelephone()}"/>
            </td>
            <td>
                <label class="field" for="mobilephone${entity.getId()}">${entity.getMobilephone()}</label>
                <input id="mobilephone${entity.getId()}" name="mobilephone" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getMobilephone()}"/>
            </td>
            <td>
                <label class="field" for="position_name${entity.getId()}">${entity.getRole().getPositionName()}</label>

                <select id="position_name${entity.getId()}" name="position_name" style="display: none;"
                        class="form-control edit-field">
                    <c:forEach var="discount" items="${positions}">
                        <option
                                <c:if test="${entity.getRole().getPositionName() eq discount}">selected</c:if>
                                value="${discount}">
                                ${discount}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <label class="field" for="virtual_balance${entity.getId()}">${entity.getVirtualBalance()}</label>
                <input id="virtual_balance${entity.getId()}" name="virtual_balance" type="text"
                       class="form-control edit-field" style="display: none;" value="${entity.getVirtualBalance()}"/>
            </td>
            <td>
                <label class="field" for="avatar${entity.getId()}">${entity.getAvatar()}</label>
                <input id="avatar${entity.getId()}" name="avatar" type="text" class="form-control edit-field"
                       style="display: none;" value="${entity.getAvatar()}"/>
            </td>
            <td>
                <label class="field">${entity.getDiscount().getPercentage()}</label>
            </td>
            <td>
                <label class="field" for="deleted${entity.getId()}">
                    <c:choose>
                        <c:when test="${entity.isDeleted() eq true}"><fmt:message key="message.yes"/></c:when>
                        <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                    </c:choose>
                </label>
                <input id="deleted${entity.getId()}" name="deleted" type="checkbox" class="edit-field"
                       style="display: none;" value="${entity.isDeleted()}"/>
            </td>
            <td>
                <button type="button" class="btn btn-primary delete" name="id" value="${entity.getId()}">
                    <fmt:message key="button.delete"/>
                </button>
            </td>
            <td>
                <a href="<c:url value="/do/editProfile?userId=${entity.getId()}"/>"><fmt:message key="client.edit.profile"/></a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td><input name="login" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="firstname" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="lastname" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="middlename" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="address" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="telephone" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="mobilephone" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="position_name" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="virtual_balance" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td><input name="avatar" type="text" class="form-control edit-field" style="display: none;"/></td>
        <td></td>
        <td></td>
        <td>
            <button type="button" class="btn btn-primary" id="add"><fmt:message key="button.add"/>
            </button>
            <button type="button" class="btn btn-primary" id="save" style="display: none;">
                <fmt:message key="button.save"/>
            </button>
        </td>
    </tr>
</fmt:bundle>