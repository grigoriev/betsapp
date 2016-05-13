<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap-theme.min.css"/>">
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>

<script src="<c:url value="/resources/js/jquery/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/notify.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/spin.min.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/common.js"/>" type="text/javascript"></script>

<script type="text/javascript">
    function authLogout() {
        location.href = '<c:url value="/auth/logout?logout=true"/>';
    }
</script>

<div class="header">
    <div id="div-logout">
        <button id="button-logout" tabindex="-1" onclick="authLogout();">Logout</button>
    </div>
</div>