<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value="/resources/js/jquery/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/notify.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/spin.min.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/common.js"/>" type="text/javascript"></script>

<script type="text/javascript">
    function appURL(path) {
        return <c:url value="/"/> +path;
    }

    function navigateToIndex() {
        location.href = '<c:url value="/"/>';
    }

    function authLogout() {
        $.get(
                '<c:url value="/auth/logout"/>',
                function (data) {
                    navigateToIndex();
                }
        );
    }
</script>

<div class="header">
    <div id="div-logout">
        <button id="button-logout" tabindex="-1" onclick="authLogout()">Logout</button>
    </div>
</div>