<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    function authLogout() {
        location.href = '<c:url value="/auth/logout?logout=true"/>';
    }
</script>

<div class="header">

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--a class="navbar-brand" href="#">Lazybets</a-->
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <%--@elvariable id="cups" type="java.util.List<eu.grigoriev.persistence.entity.CupEntity>"--%>
                    <c:forEach var="cup" items="${cups}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><c:out value="${cup.displayName}"/><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="cupStage" items="${cup.cupStageEntities}">
                                    <li><a href="<c:out value="${cupStage.url}"/>"><c:out value="${cupStage.stage}"/></a></li>
                                </c:forEach>
                                <c:forEach var="cupMenuItem" items="${cup.cupMenuItemEntities}">
                                    <c:choose>
                                        <c:when test="${cupMenuItem.menuItem == '---separator---'}">
                                            <li role="separator" class="divider"></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="<c:out value="${cupMenuItem.url}"/>"><c:out value="${cupMenuItem.menuItem}"/></a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <%--@elvariable id="admin" type="java.lang.Boolean"--%>
                    <c:if test="${admin}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Add User</a></li>
                                <li><a href="#">Edit User</a></li>
                                <li role="separator" class="divider"></li>
                            </ul>
                        </li>
                    </c:if>
                    <li><a href="#" onclick="authLogout()">Logout</a></li>
                </ul>

            </div>
        </div>
    </nav>

</div>