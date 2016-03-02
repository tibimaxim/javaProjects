app.controller('SearchCtrl', function($scope, $http) {
	$scope.clientSearch = {
		securityCode : null,
		name : null,
		firstName : null
	}
	
	$scope.results= null;

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
		});
	};

});