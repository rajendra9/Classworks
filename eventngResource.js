/**
 * 
 */
var myApp = angular.module("eventApp", ['ngMessages','ngResource']);
myApp.factory('eventResourceFactory', ['$resource',function($resource){
	return $resource("/EventProject/participants/:participantId",
					 {participantId:'@participantId'}, //defaultParams
					 {}, //Actions 
					 {stripTrailingSlashes : false}); //options
}]);
var eventController = myApp.controller("eventController",['$scope','$log','eventResourceFactory', function($scope, $log, eventResourceFactory){
	
	var evtResFact = new eventResourceFactory();
	$scope.participantList = [];
		
	$scope.showAllParticipants = function(){
		$scope.participantList = eventResourceFactory.query(function(){
			$log.info("Successful load");
		},function(){
			$log.error('Error');
		});
	}
	
	$scope.registerParticipant = function(){
			$log.info($scope.participant);
			eventResourceFactory.save($scope.participant, function(){
				$scope.showAllParticipants();
			},function(){
				$log.error('Error');
			})
	 }

	$scope.removeParticipant = function(participantId){
		$log.info(participantId);
		    var participant = eventResourceFactory.get({participantId:participantId}, function(){
			participant.$delete(function(){
				$scope.showAllParticipants();
				$log.info("deleted");
			});
		});
	}

	$scope.showAllParticipants();

}]);

