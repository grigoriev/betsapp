<%@ page import="java.util.Date" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="title" value="Lazybets: Matches"/>
    <tiles:putAttribute name="body">

        <div class="body">

            <div>

                <table class="matches">
                    <col width="100"/>
                    <col width="120"/>
                    <col width="20"/>
                    <col width="370"/>
                    <col width="59"/>
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>ID</th>
                        <th>Match</th>
                        <th>Result</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%--@elvariable id="matches" type="java.util.List<eu.grigoriev.persistence.entity.MatchEntity>"--%>
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
                            <td>
                                <c:choose>
                                    <c:when test="${match.finished}">
                                        <c:choose>
                                            <c:when test="${match.matchTypeEntity.type == 'STANDARD'}">
                                                ${match.hostScores}:${match.guestScores}
                                            </c:when>
                                            <c:when test="${match.matchTypeEntity.type == 'AET'}">
                                                AET ${match.hostAetScores}:${match.guestAetScores} (${match.hostScores}:${match.guestScores})
                                            </c:when>
                                            <c:when test="${match.matchTypeEntity.type == 'PENALTY'}">
                                                Penalty ${match.hostPenaltySeriesScores}:${match.guestPenaltySeriesScores} <br/>
                                                AET ${match.hostAetScores}:${match.guestAetScores} (${match.hostScores}:${match.guestScores})
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        -:-
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>