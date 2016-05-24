<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="title" value="Lazybets: Matches"/>
    <tiles:putAttribute name="body">

        <div class="body">

            <div>

                <table>
                    <thead>
                    <tr>
                        <td>Time</td>
                        <td>ID</td>
                        <td>Match</td>
                        <td>Result</td>
                    </tr>
                    </thead>
                    <tbody>
                        <%--@elvariable id="matches" type="java.util.List<eu.grigoriev.persistence.entity.MatchEntity>"--%>
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <td>${match.timestamp}</td>
                            <td>${match.id}</td>
                            <td>${match.hostTeamEntity.name} - ${match.guestTeamEntity.name}</td>
                            <td>${match.hostScores}:${match.guestScores}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>

        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>