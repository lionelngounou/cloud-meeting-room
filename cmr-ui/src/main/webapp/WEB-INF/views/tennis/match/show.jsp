<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="/WEB-INF/layouts/commonURL.jsp" />
<html>
    <head>
        <title>tennis</title>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script type="text/javascript">
            var stompClient = null;
            
            function connect() {
                var socket = getSocket();
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/sockTMQ/result', function(data){
                        alert('socket data is ' + data);
                    });
                });
            }
            
            function disconnect(){
                if(stompClient!=null){
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log('Disconnected...');
            }
            
            function getSocket(){
                <spring:url var="_sockMatch" value="/sockTM" />
                return new SockJS('${_sockMatch}');
            }

            function setConnected(connected) {
                //document.getElementById('connect').disabled = connected;
                //document.getElementById('disconnect').disabled = !connected;
                //document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
                document.getElementById('responseDiv').innerHTML = '....connected....'+connected;
            }
            
            function updateScore(e){
                e.preventDefault();
                console.log("begin update score...");
                <spring:url var="updateMatchUrl" value="/tennis/matches/v1/${matchId}/update/point" />
                <spring:url var="matchScoreUrl" value="/tennis/matches/v1/${matchId}/score" />
//                $.post('${updateMatchUrl}', function(data, status, xhr){
//                    console.log("updated score for match...");
//                    console.log(status + ' : ' + xhr.status);
//                    console.log(data);
//                }, "json");
                
                $.ajax({
                    url: '${updateMatchUrl}',
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function(data, status, xhr){
                        console.log("success updated score for match...");
                        console.log(status + ' : ' + xhr.status);
                        console.log(data);
                    },
                    error: function(data, status, xhr){
                        console.log("error update score for match...");
                        console.log(status + ' : ' + xhr.status);
                        console.log(data);
                    },
                    statusCode: {
                        404: function(){
                            console.log("Match not found"); 
                        }
                    }
                });
            }
            
            var matchApp = angular.module("matchApp${matchId}", []);
            matchApp.controller('matchCtrl${matchId}', function($scope, $http){
				var m = {
                    id: '${matchId}',
					currentScore : ["30 ","15"],
					sets : [["6","3"],["2","0"]],
					over : false,
					serving: 1
				};
                $scope.match = m;
				$http.get("${matchScoreUrl}").then( function(response) {
					console.log("response from get score :::: ");
					console.log(response.data);
					$scope.match = response.data.score;
				});
            });
	
        </script>
    </head>
    <body>
        <jsp:include page="../commonHeader.jsp" />
        <div class="row"> 
            <div class="col-md-12">                
                <div id="responseDiv"></div>
            </div>
        </div>
        <div class="row" data-match-id="${matchId}" data-ng-app="matchApp${matchId}" data-ng-controller="matchCtrl${matchId}"> 
            <div class="col-md-6">                
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" data-ng-class="{'point-serve' : match.serving==0}"
                        ><h2>${match.playerOne.firstAndLastNames}</h2></li>
                    <li class="list-group-item" data-ng-class="{'point-serve' : match.serving==1}"
                        ><h2>${match.playerTwo.firstAndLastNames}</h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator" data-ng-repeat="set in match.sets">               
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" ><h2>{{set[0]}}</h2></li>
                    <li class="list-group-item"><h2><b>{{set[1]}}</b></h2></li>
                </ul>
            </div>
				<div class="col-md-1 score-separator" data-ng-hide="match.over">               
                <ul class="list-group">
                    <li class="list-group-item active bottom-match-divider" ><h2>{{match.currentScore[0]}}</h2></li>
                    <li class="list-group-item active "><h2>{{match.currentScore[1]}}</h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator">               
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" >
                        <h2><a class="xbtn btn-md" title="add point" href="javascript:;" onclick="updateScore(event);">
                            <span class="glyphicon glyphicon-plus"></span></a></h2>
                    </li>
                    <li class="list-group-item" >
                        <h2><a class="xbtn btn-md" title="add point" href="javascript:;" onclick="updateScore(event);">
                            <span class="glyphicon glyphicon-plus"></span></a></h2>
                    </li>
                </ul>
            </div>                
        </div>
        <div class="row"> 
            <div class="col-md-6">                
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" ><h2>${match.playerOne.firstAndLastNames}</h2></li>
                    <li class="list-group-item point-serve"><h2>${match.playerTwo.firstAndLastNames}</h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator">               
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" ><h2>9</h2></li>
                    <li class="list-group-item"><h2><b>12</b></h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator">               
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" ><h2>6</h2></li>
                    <li class="list-group-item"><h2>4</h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator">               
                <ul class="list-group">
                    <li class="list-group-item active bottom-match-divider" ><h2>A</h2></li>
                    <li class="list-group-item active "><h2>40</h2></li>
                </ul>
            </div>
            <div class="col-md-1 score-separator">               
                <ul class="list-group">
                    <li class="list-group-item bottom-match-divider" >
                        <h2><a class="xbtn btn-md" title="add point" href="javascript:;" onclick="updateScore(event);">
                            <span class="glyphicon glyphicon-plus"></span></a></h2>
                    </li>
                    <li class="list-group-item" >
                        <h2><a class="xbtn btn-md" title="add point" href="javascript:;" onclick="updateScore(event);">
                            <span class="glyphicon glyphicon-plus"></span></a></h2>
                    </li>
                </ul>
            </div>                
        </div>
        <div class="row"> 
            <div class="col-md-6"> 
                <a class="btn btn-primary" href="javascript:;" onclick="updateScore(event);">
                    <span class="glyphicon glyphicon-minus"></span> remove previous point</a>
            </div>                
        </div>
    </body>
</html>

