
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="/WEB-INF/layouts/commonURL.jsp" />

<html>
    <head>
        <title>tennis matches</title>
    </head>
    <body>
        <jsp:include page="../commonHeader.jsp" />
        <div class="row"> 
            <div class="col-md-12"> 
                <h2>Matches (${liveMatchesCount} Live)</h2>
            </div>
        </div>
        <div class="row"> 
            <c:forEach var="match" items="${matches}">
                <div class="col-md-3"> 
                    <spring:url var="matchURL" value="/tennis/matches/${match.id}" />
                    <a href="${matchURL}" class="btn-primary">
                        <ul class="list-group ${match.over? '':'live-match'}">
                            <li class="list-group-item bottom-match-divider" >@${match.playerOne.lastname}</li>
                            <li class="list-group-item">@${match.playerTwo.lastname}</li>
                        </ul>
                    </a>
                </div>
            </c:forEach>
        </div>
    </body>
</html>

