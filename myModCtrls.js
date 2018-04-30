var app = angular.module('myModApp',[]);
app.controller('manageController', ['$scope', function($scope){
	$scope.project = {}
	$scope.project.employees=[
       {name:'Suguna Rao',job:'Head Clerk',salary:26500.0,deptId:120},
       {name:'Madhavan',job:'Analyst',salary:32500.0,deptId:130},
       {name:'Kadirvan',job:'Developer',salary:31000.0,deptId:110},
       {name:'Karthick',job:'Asst Manager',salary:39500.0,deptId:140}       
     ];
     $scope.project.totSal=function(emp){
    	return emp.salary*12;
     }
   }]);
app.controller('workController',['$scope', function($scope){
	$scope.project.workTasks = [{name:'codeWriting',duration:'106Hrs'},
	                        {name:'codeTesting',duration:'8Hrs'},
	                        {name:'perrReview',duration:'4Hrs'}];
	
     $scope.project.moto='Do In Time';
     
   }]);
