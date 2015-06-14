<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <title><fmt:message key="navigation.aboutus"/></title>
        <link rel="stylesheet" href="<c:url value="/style/about.css"/>">
    </t:gHead>
    <t:gbody>
        <h1 class="about-header">"Live Water"</h1>

        <div class="about-content non-edit">
                ${about.text}
            <c:if test="${not empty user and user.role.positionName == 'Admin'}">
                <br>
                <br>

                <div style="float: right">
                    <button id="edit">Редактировать</button>
                </div>
            </c:if>
        </div>


        <c:if test="${not empty user and user.role.positionName == 'Admin'}">
            <div class="about-content edit" style="display: none">
                <form>
                    <input name="aboutId" type="hidden" value="${about.id}">

            <textarea name="text" rows="15" class="about-content">
                    ${about.text}
            </textarea>

                </form>
                <button id="save">Сохранить</button>
                <button id="cancel">Отмена</button>
            </div>
        </c:if>

        <div style="{float: right;}">
            <a id="firmsonmap_biglink"
               href="http://2gis.kz/karaganda/callout/firms-11822477302830162/center/73.08517456054689,49.86012002599576/zoom/11">Перейти
                к большой карте</a>
            <script charset="utf-8" src="http://widgets.2gis.com/js/DGWidgetLoader.js"></script>
            <script charset="utf-8">new DGWidgetLoader({
                "width": 640,
                "height": 600,
                "borderColor": "#a3a3a3",
                "pos": {"lat": 49.86012002599576, "lon": 73.08517456054689, "zoom": 11},
                "opt": {"city": "karaganda"},
                "org": [{"id": "11822477302830162"}]
            });</script>
            <noscript style="color:#c00;font-size:16px;font-weight:bold;">Виджет карты использует JavaScript. Включите
                его в настройках вашего браузера.
            </noscript>
        </div>

        <script src="<c:url value="/script/about.js"/>"></script>
    </t:gbody>
    </html>
</fmt:bundle>
