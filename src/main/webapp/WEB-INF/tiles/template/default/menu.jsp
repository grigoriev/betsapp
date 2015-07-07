<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">

    <c:url value='/wc2014' var="wc2014"/>
    <c:url value='/euro2016' var="euro2016"/>

    <ul id="left-vertical-menu" tabindex="-1">
        <li url="${wc2014}">
            World Cup 2014 in Brasil
        </li>
        <li url="${euro2016}">
            Euro 2016 in France
        </li>
    </ul>
</div>