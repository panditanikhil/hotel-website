var app = angular.module("hotel",[]);
		app.controller("registerController", function($scope,$http){
				alert('hi');
			$scope.register = function($window)
			{ 	alert('hello0');
				 $http({
		    		 url:"/CherryBrownHotelProject/CustomerRegistration", 
		    		 method:"post", 
		    		 params:{
		    			 
		    			 "customerName":$scope.name,
		    			 "customerAge":$scope.age,
		    			 "customerGender":$scope.gender,
		    			 "customerContactNumber":$scope.contactNumber,
		    			 "customerLocation":$scope.location,
		    			 "customerEmail":$scope.email
		    			 
		    		 }
		    	 }).then(function(result){
		    		 alert(result.data);
		    		 $scope.id=result.data;
		    		 $window.location.href = 'http://localhost:8080/CherryBrownHotelProject/success.html';
		    		 alert(result.data);
		    		 
		    	 },function(result){
		    		alert("fail");
		    		 
		    	 });
			};
		});