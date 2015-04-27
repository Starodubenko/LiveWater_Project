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
        <h1 class="about-header">"Доставка"</h1>

        <div class="about-content">
            Проект "Доставка" создан на примере фирм, которые занимаются продажей и доставкой бутилированной воды, а
            также устройств,
            способствующих её охлаждению, нагреву и распределению. Проект позволяет удобно и быстро создавать
            заказы, а также контролировать историю,
            созданных заказов в личном кабинете. Со стороны обслуживающего персонала проект предоставляет удобные
            инструменты
            для продвижения всех заказов дальше на склад...
        </div>

        <div style="{float: right;}">
            <a id="firmsonmap_biglink" href="http://2gis.kz/karaganda/callout/firms-11822477302830162/center/73.08517456054689,49.86012002599576/zoom/11">Перейти к большой карте</a><script charset="utf-8" src="http://widgets.2gis.com/js/DGWidgetLoader.js"></script><script charset="utf-8">new DGWidgetLoader({"width":640,"height":600,"borderColor":"#a3a3a3","pos":{"lat":49.86012002599576,"lon":73.08517456054689,"zoom":11},"opt":{"city":"karaganda"},"org":[{"id":"11822477302830162"}]});</script><noscript style="color:#c00;font-size:16px;font-weight:bold;">Виджет карты использует JavaScript. Включите его в настройках вашего браузера.</noscript>
        </div>
    </t:gbody>
    </html>
</fmt:bundle>