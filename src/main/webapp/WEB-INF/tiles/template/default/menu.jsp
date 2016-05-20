<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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