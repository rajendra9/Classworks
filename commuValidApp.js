var commuValidApp = angular.module('commuValidApp',['ngMessages']);
commuValidApp.controller('commuValidContrl', ['$scope','$http', function($scope,$http){
	$scope.submitForm=function(isValid){
	   $http({method:'POST',
		      url:'commuSave',
		      params:{commu:$scope.community},
		      headers:{'Content-Type':'application/x-www-form-encoded'}	   
	   }).success(function(response){
		   $scope.message=response;
	   }).error(function(){
		  alert('Error'); 
	   });
	}	
}]);

    
  