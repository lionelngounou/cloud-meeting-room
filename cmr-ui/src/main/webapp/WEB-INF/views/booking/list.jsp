<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/layouts/commonURL.jsp" />
<html>
    <head>
        <title>bookings</title>
    </head>
    <body >
		<section ng-app="bookingApp">
			<div class="row"> 
				<div class="col-md-12"> 
					<h1>Bookings</h1>
				</div>
			</div>
			<div class="row" ng-controller="bookingCtrl"> 
				<div class="col-md-12"> 
					<button type="button" title="add/create boooking" ng-click="launchCreateForm()"						
							class="btn btn-primary btn-sm pull-right">
						<span class="glyphicon glyphicon-plus"></span>
						Add
					</button>
				</div>
				
				<div class="col-md-12"> 
					<table class="table table-striped table-hover" >
						<thead>
							<tr>
								<th>User</th>
								<th>Room</th>
								<th>Date</th>
								<th>Time Slot</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="bk in bookings" data-row-id="{{bk.id}}">
								<td>{{bk.user}}</td>
								<td>{{bk.room}}</td>
								<td>{{bk.date}}</td>
								<td>{{bk.timeSlot.description}}</td>
								<td>
									<span ng-if="bk.past && !bk.inProgress" title="meeting finished" class="text-primary glyphicon glyphicon-check"></span>
									<span ng-if="bk.inProgress" title="live meeting" class="text-success">LIVE</span>
								</td>
								<td><a ng-if="!bk.past && !bk.inProgress" href="javascript:;" ng-click="deleteRoomBooking(bk.id)">
										<span  title="cancel meeting" class="text-danger glyphicon glyphicon-remove-circle"></span>
									</a>	
								</td>
							</tr>						
						</tbody>
					</table>
				</div>
				
				<div class="col-md-12"> 
					<button type="button" title="add/create boooking" ng-click="launchCreateForm()"						
							class="btn btn-primary btn-sm pull-right">
						<span class="glyphicon glyphicon-plus"></span>
						Add
					</button>
				</div>
				
				<div id="modalContainer"> 
					<div id="createBookingModal" class="modal fade" role="dialog">						
						<div class="modal-dialog">
							<form role="form" name="createBookingForm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Create new booking</h4>
								</div>
								<div class="modal-body">
										<div class="row">
											<div class="col-sm-6 form-group">
												<label for="user">User : </label>
												<input type="text" id="user" class="form-control" ng-model="newRoomBooking.user" required="" placeholder="Enter user name"/>
											</div>
											<div class="col-sm-6 form-group">
												<label for="room">Room : </label>
												<select id="room" class="form-control" ng-model="newRoomBooking.room" ng-options="r for r in rooms" required="" ng-required="required" >
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6 form-group">
												<label for="date">Date : </label>
												<input type="date" id="date" class="form-control" ng-model="newRoomBooking.date" required="" ng-required="required" placeholder="Enter date"/>
											</div>
											<div class="col-sm-6 form-group">
												<label for="timeSlot">Time Slot : </label>
												<select id="timeSlot" class="form-control" ng-model="newRoomBooking.timeSlot" required=""
														ng-options="ts.id as ts.description for ts in timeSlots track by ts.id" ng-required="required">
												</select>
											</div>
										</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
									<input type="submit" class="btn btn-primary" value="Save"
											ng-click="createNewRoomBooking()" 
											ng-disabled="createBookingForm.$invalid" />
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>			
		</section>
    </body>
</html>

