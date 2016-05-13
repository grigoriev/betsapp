<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.CsrfAuthenticationStrategy.SaveOnAccessCsrfToken"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="<c:url value='/resources/js/jquery/jquery.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/common.js'/>" type="text/javascript"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AuthLogin</title>
</head>
<body>

<script>
    $(function () {
        $("#username").focus();
    });
</script>

<div id="login-box">

    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <%--@elvariable id="logout" type="java.lang.String"--%>
    <c:if test="${not empty logout}">
        <div class="logout">${logout}</div>
    </c:if>

    <form id='loginForm' action='<c:url value='/j_spring_security_check'/>' method='post'>

        <div id="div-login">
            <table id="table-login">
                <colgroup>
                    <col width="35%">
                    <col width="65%">
                </colgroup>

                <tr>
                    <td>
                        <label for="username">Username</label>
                    </td>
                    <td>
                        <input id="username" type="text" name="j_username">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password</label>
                    </td>
                    <td>
                        <input id="password" type="password" name="j_password"/>
                    </td>
                </tr>
            </table>

            <table id="table-buttons">
                <tr>
                    <td align="right">
                        <button id="submit-button" type="submit">Login</button>
                    </td>
                </tr>
            </table>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    </form>
</div>

</body>

</html>