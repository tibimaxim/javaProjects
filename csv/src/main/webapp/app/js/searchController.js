app.controller('SearchCtrl', function($scope, $http) {
	$scope.clientSearch = {
		securityCode : "",
		name : "dadsadsa",
		firstName : ""
	}
	
	$scope.results;

	$scope.search = function() {
		$http({
			url : clientServicesPath + "searchClients",
			method : 'POST',
			data : $scope.clientSearch,
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.results = serverData;
			console.log($scope.results);
		});
	};

});