<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfAuthenticationStrategy.SaveOnAccessCsrfToken"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bets App</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/auth/login.css"/>">
    <script src="<c:url value='/resources/js/jquery/jquery.min.js'/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>" type="text/javascript"></script>
</head>
<body>

<script type="text/javascript">
    $(function () {
        $("#alert-error").fadeTo(2000, 500).slideUp(500, function () {
            $("#alert-error").alert('close');
        });
        $("#alert-message").fadeTo(2000, 500).slideUp(500, function () {
            $("#alert-message").alert('close');
        });
    });
</script>

<div id="container">

    <form class="form-signin" id='loginForm' action='<c:url value='/j_spring_security_check'/>' method='post'>
        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:if test="${not empty error}">
            <div id="alert-error" class="alert alert-danger">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                    ${error}
            </div>
        </c:if>

        <%--@elvariable id="message" type="java.lang.String"--%>
        <c:if test="${not empty message}">
            <div id="alert-message" class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                    ${message}
            </div>
        </c:if>

        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" name="j_username" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="j_password" class="form-control" placeholder="Password" required>
        <!--div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div-->
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

</body>

</html>