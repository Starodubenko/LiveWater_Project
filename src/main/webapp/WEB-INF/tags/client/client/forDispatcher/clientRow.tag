<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="clients" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${clients}">
        <tr>
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
                <button type="button" class="btn btn-primary createOrder" data-toggle="modal" data-backdrop="static"
                        value="${entity.getId()}"
                        data-target="#myModel"><fmt:message
                        key="clients.message.create.order"/>
                </button>
            </td>
            <td>
                <input type="checkbox" class="checkbox edit" id="${entity.getId()}"/>
                <label class="checkbox-label" for="${entity.getId()}"></label>
            </td>
        </tr>
    </c:forEach>
</fmt:bundle>