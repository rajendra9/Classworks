/**
 * 
 */
var myApp = angular.module("myApp",[]);
var eventController = myApp.controller("eventController", function($scope){
	
	$scope.submitEventForm = function(){
		alert("Event form submitted successfully.");
	}
});