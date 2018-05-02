var commuApp = angular.module('commuApp');
commuApp.controller('communityController',['$scope', 'communityService', function($scope,communityService){
     $scope.validateMember = function(){
    	communityService.validOrFind('validate',$scope.memId,function(data){
    	   $scope.message = data;	
    	}); 
     }
     $scope.findService = function(){
     	communityService.validOrFind('findService',$scope.memId,function(data){
     	   $scope.message = data;	
     	}); 
     }
     $scope.updateService = function(){
      	communityService.updateServTime('update',$scope.memId,$scope.nodService,function(data){
      	   $scope.message = data;	
      	}); 
     }  
     $scope.registerMember = function(){
       	communityService.registerMem('register',$scope.memId,$scope.memName,$scope.fees,$scope.commuId,function(data){
       	   $scope.message = data;	
       	}); 
      }    
 }]);