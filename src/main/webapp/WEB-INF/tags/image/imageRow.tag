<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="image" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${image}">
        <form id="entity-line${entity.getId()}">
            <tr>
                <td>
                    <label class="field">${entity.getId()}</label>
                </td>
                <td>
                    <div class="image-block">
                        <img class="image panel panel-default" src="/image/${entity.getId()}/${entity.getFilename()}">
                    </div>
                </td>
                <td>
                    <label for="filename${entity.getId()}">${entity.getFilename()}</label>
                    <%--<input id="filename${entity.getId()}" name="period" type="text" class="form-control field" style="display: none;" value="${entity.getFilename()}"/>--%>
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
            <td class="id"></td>
            <td></td>
            <%--<td><input name="filename" type="text" class="form-control field" style="display: none;"/></td>--%>
            <td><input id="filename" name="filename" type="file" class="edit-field" style="display: none;"></td>
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