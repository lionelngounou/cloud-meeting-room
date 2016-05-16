
/*
 * Lionel Ngounou
 * hide impl of booking js
 */
	
(function(){
	'use strict';
	
	var URLS = getUrls();
	
	var bookingCtrl = function($scope, $http){		
	
		function loadRoomBookings(){
			$http.get(URLS.bookingListAPI).then( 
				function(response){
					$scope.bookings = response.data.roomBookings;
				}
			);
		}
		
		function initFormData(){
			if(!$scope.rooms || $scope.rooms===null){
				$http.get(URLS.roomListAPI).then( 
					function(response){
						$scope.rooms = response.data.rooms;
					}
				);
			}

			if(!$scope.timeSlots || $scope.timeSlots===null){
				$http.get(URLS.timeSlotListAPI).then( 
					function(response){
						$scope.timeSlots = response.data.timeSlots;
					}
				);
			}
			$scope.newRoomBooking = {};
		}
		
		$scope.launchCreateForm = function(){
			console.log("@===@ launch create room booking form");
			initFormData();
			$("#createBookingModal").modal();
		};	
		
		$scope.createNewRoomBooking = function(){
			console.log("@===@ create new room booking : " + JSON.stringify($scope.newRoomBooking));
			//add to back end
			$http.post(URLS.bookingCreateAPI, $scope.newRoomBooking).then( 
				function(response){
					console.log("@===@ success from create booking : " + JSON.stringify(response.data));
					loadRoomBookings();//refresh list
					$("#createBookingModal").modal('hide');
				}, 
				function(response){
					console.log("@===@ error from create booking : " + JSON.stringify(response.data));
				}				
			);
		};
		
		loadRoomBookings();
		
		$("#createBookingModal").on('hidden.bs.modal', function(){ 
			delete $scope.newRoomBooking;
		});
		
	};
	
	var bookingApp = angular.module("bookingApp", []);	
	bookingApp.controller('bookingCtrl', bookingCtrl);
}());
