/**
 * 
 */
var routeApp = angular.module("routeApp",['ngRoute']);
routeApp.config(['$routeProvider','$locationProvider',function($routeProvider, $locationProvider){
	$locationProvider.hashPrefix('');
	$routeProvider
		.when('/home',{
			templateUrl: 'home.html',
			controller: 'HomeController'
		})
		.when('/list',{
			templateUrl: 'list.html',
			controller: 'ListParticipantController'
		})
		.when('/search',{
			templateUrl: 'search.html',
			controller: 'SearchParticipantController'
		})	
		.when('/register',{
			templateUrl: 'register.html',
			controller: 'RegisterParticipantController'
		});	
}]);
routeApp.controller("HomeController", function($scope){
	
});

routeApp.controller("ListParticipantController",['$scope','$log','$http', function($scope, $log, $http){
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

}]);

routeApp.controller("RegisterParticipantController", function($scope, $log, $http){
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

routeApp.controller("SearchParticipantController", function($scope, $log,$http){
	$scope.participant = {};
	$scope.searchParticipant = function(){
		
		$http({
			method: 'GET',
			url:'http://localhost:8080/EventProject/participants/' + $scope.participantId,
		}).then(
				function success(response){
					$scope.participant = response.data;
				},
				function error(response){
					$log.info("Error loading particilant");
				}
		    ); 
	}
});
