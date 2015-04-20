<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="contact" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${contact}">
        <form id="entity-line${entity.getId()}">
            <tr>
                <td class="id">
                    <label class="field">${entity.getId()}</label>
                </td>
                <td>
                    <label class="field" for="telephone${entity.getId()}">${entity.getTelephone()}</label>
                    <input id="telephone${entity.getId()}" name="telephone" type="text" class="form-control edit-field" style="display: none;" value="${entity.getTelephone()}"/>
                </td>
                <td>
                    <label class="field" for="owner${entity.getId()}">${entity.getOwner()}</label>
                    <input id="owner${entity.getId()}" name="owner" type="text" class="form-control edit-field" style="display: none;" value="${entity.getOwner()}"/>
                </td>
                <td>
                    <label class="field" for="part${entity.getId()}">${entity.getPart()}</label>
                    <input id="part${entity.getId()}" name="part" type="text" class="form-control edit-field" style="display: none;" value="${entity.getPart()}"/>
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
            <td><input name="telephone" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td><input name="owner" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td><input name="part" type="text" class="form-control edit-field" style="display: none;"/></td>
            <td></td>
            <td>
                <button type="button" class="btn btn-primary" id="add"><fmt:message key="button.add"/>
                </button>
                <button type="button" class="btn btn-primary" id="save" style="display: none;"
                        value="${entity.getId()}"><fmt:message key="button.save"/>
                </button>
            </td>
        </tr>
    </form>
</fmt:bundle>