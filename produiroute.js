
var uiRouteApp = angular.module("uiRouteApp", ['ui.router']);

uiRouteApp.config(['$stateProvider', function($stateProvider){
	
	$stateProvider.state('participantParent',{
		url:'/participants',
		templateUrl:'templates/participantParent.html',
		controller: 'ParticipantParentController'
	})
	$stateProvider.state('home',{
		url: '/home',
		templateUrl: 'templates/home.html'
	})
	$stateProvider.state('participantParent.participants',{
		url: '/',
		templateUrl: 'templates/participants.html',
		controller:'ParticipantsController'
	})
	$stateProvider.state('participantParent.participantDetails',{
		url: '/:participantId',
		templateUrl: 'templates/participantDetails.html',
		controller:'ParticipantDetailsController'
	})

	$stateProvider.state('register',{
		url: '/register',
		templateUrl: 'templates/register.html',
		controller:'RegisterController'
	})

}]);

uiRouteApp.controller("ParticipantsController", function($scope, $log, $http){
	$scope.participantList = [];
	$http({
		method: 'GET',
		url:'http://localhost:8080/EventProject/participants/',
	}).then(
			function success(response){
				$log.info(response.data);
				$scope.participantList = response.data;
			},
			function error(response){
				$log.error("Error during registration.");
			}
	    ); 
});

uiRouteApp.controller("RegisterController", function($scope, $log, $http){
	$scope.registerParticipant = function(){
		$log.info($scope.participant);
		$http({
			method: 'POST',
			url:'http://localhost:8080/EventProject/participants/',
			data : $scope.participant
		}).then(
				function success(response){
					$log.info("Participants registration completed successfully.");
					//$scope.showAllParticipants();
				},
				function error(response){
					$log.error("Error during registration.");
				}
		    ); 
	}

});

uiRouteApp.controller("ParticipantDetailsController", function($scope, $log, $http, $stateParams){
		$scope.participant = {};
		$http({
			method: 'GET',
			url:'http://localhost:8080/EventProject/participants/' + $stateParams.participantId,
		}).then(
				function success(response){
					$log.info("Participants details loaded successfully.");
					$scope.participant = response.data;
				},
				function error(response){
					$log.error("Error during registration.");
				}
		    ); 
});

uiRouteApp.controller("ParticipantParentController", function($scope, $log, $http){
	$scope.participantCount = {};
	$http({
		method: 'GET',
		url:'http://localhost:8080/EventProject/participants/getCount',
	}).then(
			function success(response){
				$log.info("Participants count loaded successfully.");
				$scope.participantCount = response.data;
			},
			function error(response){
				$log.error("Error during registration.");
			}
	    ); 
	
});
