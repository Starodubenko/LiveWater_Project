<%@ tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<link rel='stylesheet' href='<c:url value="/style/gbody.css"/>'>
<body>

<t:navigation/>
<div class="main panel panel-default">
    <t:authentication/>
    <jsp:doBody/>
    <p id="back-top">
        <a href="#top"><span></span>Вверх</a>
    </p>
</div>
<t:footer/>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/script/welcome.js"/>"></script>
</body>