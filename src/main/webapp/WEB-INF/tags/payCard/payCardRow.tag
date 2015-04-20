<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="payCard" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>


<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${payCard}">
        <form id="entity-line${entity.getId()}">
            <tr>
                <td class="id">
                    <label class="field">${entity.getId()}</label>
                </td>
                <td>
                    <label class="field" for="serial_number${entity.getId()}">${entity.getSerialNumber()}</label>
                    <input id="serial_number${entity.getId()}" name="serial_number" type="text" class="form-control edit-field" style="display: none;" value="${entity.getSerialNumber()}"/>
                </td>
                <td>
                    <label class="field" for="secret_number${entity.getId()}">${entity.getSecretNumber()}</label>
                    <input id="secret_number${entity.getId()}" name="secret_number" type="text" class="form-control edit-field" style="display: none;" value="${entity.getSecretNumber()}"/>
                </td>
                <td><label class="field" for="balance${entity.getId()}">${entity.getBalance()}</label>
                    <input id="balance${entity.getId()}" name="balance" type="text" class="form-control edit-field" style="display: none;" value="${entity.getBalance()}"/>
                </td>
                <td>
                    <label class="field" for="status_name${entity.getId()}">
                        <c:if test="${entity.getStatusPayCard().getStatusName() eq 'activated'}"><fmt:message key="message.activated"/></c:if>
                        <c:if test="${entity.getStatusPayCard().getStatusName() eq 'not activated'}"><fmt:message key="message.notactivated"/></c:if>
                    </label>
                    <select id="status_name${entity.getId()}" name="status_name" style="display: none;" class="form-control edit-field">
                        <c:forEach var="status" items="${cardStatuses}">
                            <option <c:if test="${entity.getStatusPayCard().getStatusName() eq status}">selected</c:if> value="${status}">
                                <c:if test="${status eq 'activated'}"><fmt:message key="message.activated"/></c:if>
                                <c:if test="${status eq 'not activated'}"><fmt:message key="message.notactivated"/></c:if>
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <label class="field" for="deleted${entity.getId()}">
                        <c:choose>
                            <c:when test="${entity.isDeleted() eq true}"><fmt:message key="message.yes"/></c:when>
                            <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                        </c:choose>
                    </label>
                    <input id="deleted${entity.getId()}" name="deleted" type="checkbox" class="edit-field" style="display: none;" value="${entity.isDeleted()}"/>
                </td>
                <td>
                    <button type="button" class="btn btn-primary delete" name="id" value="${entity.getId()}">
                        <fmt:message key="button.delete"/>
                    </button>
                </td>
                <td>
                    <input type="checkbox" class="checkbox edit" id="${entity.getId()}"/>
                    <label class="checkbox-label" for="${entity.getId()}"></label>
                </td>
            </tr>
        </form>
    </c:forEach>
    <form id="saveForm">
        <tr>
            <td></td>
            <td><input name="serial_number" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td><input name="secret_number" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td><input name="balance" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td></td>
            <td></td>
            <td>
                <button type="button" class="btn btn-primary" id="add"><fmt:message key="button.add"/>
                </button>
                <button type="button" class="btn btn-primary" id="save" style="display: none;"
                        value="${entity.getId()}"><fmt:message key="button.save"/>
                </button>
            </td>
            <td>
                <div id="result">

                </div>
            </td>
        </tr>
    </form>
</fmt:bundle>