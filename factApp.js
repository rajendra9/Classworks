var factCommuApp = angular.module('factCommuApp', []);
factCommuApp.factory('commuFactory', function($http){
   var commuFactory = {}
   commuFactory.getCommunities = function(callback){
	 return $http.get('commuServ').success(callback);
   }
   commuFactory.getMembers = function(commuId, callback){
	   return $http({method:'POST',
		      url:'commuServ',
		      params:{comId:commuId},
		      headers:{'Content-Type':'application/x-www-form-encoded'}	   
	   }).success(callback);
   }	
   return commuFactory;
});

factCommuApp.controller('commuContrl',['$scope', 'commuFactory', function($scope, commuFactory){
	commuFactory.getCommunities(function(data){
		$scope.communities = data;
	});
	$scope.display=function(indx){
	   $scope.cid = $scope.communities[indx].regNum;
	   //alert('$$$'+$scope.cid);
	   commuFactory.getMembers($scope.cid, function(data){
		   $scope.members = data;
	   });
	}
}]);






