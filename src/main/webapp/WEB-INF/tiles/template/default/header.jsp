<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap-theme.min.css"/>">
<script src="<c:url value="/resources/js/jquery/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/holder.min.js"/>"></script>

<script src="<c:url value="/resources/js/notify.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/spin.min.js"/>" type="text/javascript"></script>

<script src="<c:url value="/resources/js/common.js"/>" type="text/javascript"></script>

<script type="text/javascript">
    function authLogout() {
        location.href = '<c:url value="/auth/logout?logout=true"/>';
    }
</script>

<div class="header">

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Euro2016<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Group Stage</a></li>
                            <li><a href="#">1/8</a></li>
                            <li><a href="#">1/4</a></li>
                            <li><a href="#">1/2</a></li>
                            <li><a href="#">Final</a></li>
                            <li><a href="#">Others</a></li>
                            <li><a href="#">Scores</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Help</a></li>
                            <li><a href="#">Participants</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" onclick="authLogout()">Logout</a></li>
                </ul>

                <%--@elvariable id="admin" type="java.lang.Boolean"--%>
                <c:if test="${admin}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Add User</a></li>
                                <li><a href="#">Edit User</a></li>
                                <li role="separator" class="divider"></li>
                            </ul>
                        </li>
                    </ul>
                </c:if>
            </div>
        </div>
    </nav>

</div>