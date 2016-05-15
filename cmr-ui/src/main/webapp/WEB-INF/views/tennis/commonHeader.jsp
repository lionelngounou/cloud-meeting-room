<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="matchesURL" value="/tennis/matches" />
<spring:url var="playersURL" value="/tennis/players" />
<spring:url var="tournamentsURL" value="/tennis/tournaments" />

<div class="row"> 
    <div class="col-md-12"> 
        <ul class="nav nav-tabs">
            <li class="active"><a href="${matchesURL}" xdata-toggle="tab">Matches</a></li>
            <li><a href="${playersURL}" xdata-toggle="tab">Players</a></li>
            <li><a href="${tournamentsURL}" xdata-toggle="tab">Tournaments</a></li>
        </ul>
    </div>
</div>