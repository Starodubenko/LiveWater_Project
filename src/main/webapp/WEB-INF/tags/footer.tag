<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<link rel='stylesheet' href='<c:url value="/style/footer.css"/>'>

<div class="footer panel panel-default">
    <div class="center footer-block panel panel-default">
        <div class="contacts panel panel-default">
            <c:forEach var="contact" items="${contacts}">
                <div class="contact">
                    <label class="part left">${contact.part}:</label>
                    <label class="number left">${contact.telephone}</label>
                    <label class="left"> - ${contact.owner}</label>
                </div>
                <br/>
            </c:forEach>
        </div>

        <div class="clear"></div>
    </div>
</div>