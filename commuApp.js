var commuApp = angular.module('commuApp', []);
commuApp.service('communityService',['$http', function($http){
   return {
	validOrFind:function(action, memberId, callback){
		$http({ method:'GET',
			    url:'useCommunity',
			    params:{act:action,memId:memberId}
		}).success(callback);  
	},
	updateServTime:function(action, memberId, noDays, callback){
		$http({ method:'POST',
		    url:'useCommunity',
		    params:{act:action,memId:memberId,nod:noDays},
		    headers:{'Content-Type':'application/x-www-form-encoded'}
	}).success(callback);    
   },
   registerMem:function(action, memberId, memberName, paidFees, communityId, callback){
		$http({ method:'POST',
		    url:'useCommunity',
		    params:{act:action, memId:memberId, memName:memberName, fees:paidFees, community:communityId},
		    headers:{'Content-Type':'application/x-www-form-encoded'}
	}).success(callback);    
  }
	
 }	
}]);






