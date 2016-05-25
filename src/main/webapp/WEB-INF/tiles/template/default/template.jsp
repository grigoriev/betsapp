<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <%@include file="../include/html_head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>">
</head>

<body>
<div class="page">
    <tiles:insertAttribute name="header"/>
    <div class="content">
        <tiles:insertAttribute name="body"/>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>