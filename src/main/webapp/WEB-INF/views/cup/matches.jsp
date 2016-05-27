<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="title" value="Lazybets: Matches"/>
    <tiles:putAttribute name="body">
        <%--@elvariable id="currentCup" type="eu.grigoriev.persistence.entity.CupEntity"--%>
        <%--@elvariable id="currentUser" type="eu.grigoriev.persistence.entity.UserEntity"--%>
        <%--@elvariable id="matches" type="java.util.List<eu.grigoriev.persistence.entity.MatchEntity>"--%>
        <%--@elvariable id="bet" type="eu.grigoriev.persistence.entity.BetEntity"--%>

        <div class="body">

            <div class="table-responsive">

                <table class="matches">
                    <col width="100"/>
                    <col width="100"/>
                    <col width="25"/>
                    <col width="300"/>
                    <col width="50"/>
                    <c:forEach var="user" items="${currentCup.userEntities}">
                        <col width="50"/>
                    </c:forEach>
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>ID</th>
                        <th>Match</th>
                        <th>Result</th>
                        <c:forEach var="user" items="${currentCup.userEntities}">
                            <th title="${user.display}">${user.abbreviation}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <td>${match.date}</td>
                            <td>${match.time}</td>
                            <td>${match.serialNumber}</td>
                            <td>
                                <table class="team">
                                    <tr>
                                        <td>${match.hostTeamEntity.name}</td>
                                        <td><img class="flag ${match.hostTeamEntity.name}" alt="${match.hostTeamEntity.name}" src="${match.hostTeamEntity.logo}"/></td>
                                        <td>vs</td>
                                        <td><img class="flag ${match.guestTeamEntity.name}" alt="${match.guestTeamEntity.name}" src="${match.guestTeamEntity.logo}"/></td>
                                        <td>${match.guestTeamEntity.name}</td>
                                    </tr>
                                </table>
                            </td>
                            <td class="result"
                                title="
                                <c:choose>
                                    <c:when test="${match.finished == true}">
                                        <c:choose>
                                            <c:when test="${match.matchTypeEntity.type == 'AET'}">
                                                AET ${match.hostAetScores}:${match.guestAetScores} (${match.hostScores}:${match.guestScores})
                                            </c:when>
                                            <c:when test="${match.matchTypeEntity.type == 'PENALTY'}">
                                                Penalty ${match.hostPenaltySeriesScores}:${match.guestPenaltySeriesScores} - AET ${match.hostAetScores}:${match.guestAetScores} (${match.hostScores}:${match.guestScores})
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        Not finished
                                    </c:otherwise>
                                </c:choose>
                                "
                            >
                                <c:choose>
                                    <c:when test="${match.finished == true}">
                                        <c:choose>
                                            <c:when test="${match.matchTypeEntity.type == 'STANDARD'}">
                                                ${match.hostScores}:${match.guestScores}
                                            </c:when>
                                            <c:when test="${match.matchTypeEntity.type == 'AET'}">
                                                ${match.hostAetScores}:${match.guestAetScores}
                                            </c:when>
                                            <c:when test="${match.matchTypeEntity.type == 'PENALTY'}">
                                                ${match.hostAetScores}:${match.guestAetScores}
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        -:-
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <c:forEach var="user" items="${currentCup.userEntities}">

                                <c:set var="bet" value="${null}"/>
                                <c:forEach var="tmp" items="${match.betEntities}">
                                    <c:if test="${tmp.userEntity.id == user.id}">
                                        <c:set var="bet" value="${tmp}"/>
                                    </c:if>
                                </c:forEach>

                                <c:choose>
                                    <c:when test="${bet == null && user.id == currentUser.id}">
                                        <td>
                                            <input type="text" class="userBet" value="-:-"/>
                                        </td>
                                    </c:when>
                                    <c:when test="${bet != null && user.id == currentUser.id}">
                                        <td>
                                            <input type="text" class="userBet" value="${bet.hostScores}:${bet.guestScores}"/>
                                        </td>
                                    </c:when>
                                    <c:when test="${bet != null && user.id != currentUser.id}">
                                        <td>
                                                ${bet.hostScores}:${bet.guestScores}
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            -:-
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>