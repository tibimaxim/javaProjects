app.controller('AccountsCtrl', function($scope, $http) {
	
	$scope.results= null;
	$scope.username="";


	$scope.search = function() {
		$http({
			url : accountServicesPath + "searchAccounts",
			method : 'GET',
			params : {username : $scope.username},
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.results = serverData;
		});
	};

});