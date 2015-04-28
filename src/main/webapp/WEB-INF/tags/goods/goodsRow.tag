<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="goods" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="entity" items="${goods}">
        <form id="entity-line${entity.getId()}">
            <tr>
                <td>
                    <label class="field">${entity.getId()}</label>
                </td>
                <td>
                    <div class="image-block">
                        <%--<img class="image panel panel-default" src="/image/${entity.getImage().getId()}/${entity.getImage().getFilename()}">--%>
                        <img class="image panel panel-default" src="<c:url value="/image/${entity.getImage().getId()}/${entity.getImage().getFilename()}"/>">
                    </div>
                    <label class="field" for="image${entity.getId()}">${entity.getImage().getFilename()}</label>
                    <input id="image${entity.getId()}" name="image" type="text" class="form-control edit-field" style="display: none;" value="${entity.getImage().getFilename()}"/>
                </td>
                <td>
                    <label class="field" for="goods_name${entity.getId()}">${entity.getGoodsName()}</label>
                    <input id="goods_name${entity.getId()}" name="goods_name" type="text" class="form-control edit-field" style="display: none;" value="${entity.getGoodsName()}"/>
                </td>
                <td>
                    <label class="field" for="price${entity.getId()}">${entity.getPrice()}</label>
                    <input id="price${entity.getId()}" name="price" type="text" class="form-control edit-field" style="display: none;" value="${entity.getPrice()}"/>
                </td>
                <td>
                    <label class="field" for="deleted${entity.getId()}">
                        <c:choose>
                            <c:when test="${entity.isDeleted() eq true}"><fmt:message key="message.yes"/></c:when>
                            <c:otherwise><fmt:message key="message.no"/></c:otherwise>
                        </c:choose>
                    </label>
                    <input id="deleted${entity.getId()}" name="deleted" type="checkbox" class="field" style="display: none;" value="${entity.isDeleted()}"/>
                </td>
                <td>
                    <button type="button" class="btn btn-primary delete" name="id" value="${entity.getId()}">
                        <fmt:message key="button.delete"/>
                    </button>
                </td>
                <td>
                    <a href="<c:url value="/do/editGoods?goodsId=${entity.getId()}&purpose=update"/>"><fmt:message key="client.edit.goods"/></a>
                </td>
            </tr>
        </form>
    </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <button type="button" class="btn btn-primary" id="addGoods">
                    <fmt:message key="button.add"/>
                </button>
            </td>
        </tr>
</fmt:bundle>