app.controller('ClientCtrl', function($scope, $routeParams, $http) {

	$scope.id = $routeParams.id;
	$scope.errorMessage = null;

	$scope.client = {
		id : null,
		securityCode : null,
		serialCode : null,
		serialNumber : null,
		lastName : null,
		firstName1 : null,
		firstName2 : null,
		dateOfBirth : null,
		sex : null,
		expireDate : null,
		emailAddress : null,
		phoneNumber : null,
		address : null
	};

	// on load call getData to pre-fill client
	$scope.$on('$viewContentLoaded', function($evt, data) {
		$scope.getClient();
	});

	$scope.getClient = function() {
		$http({
			url : clientServicesPath + 'getClient?id=' + $scope.id,
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.client = serverData;
			console.log($scope.client);
		});
	};

	$scope.update = function() {
		$http({
			url : clientServicesPath + 'updateClient',
			method : 'POST',
			data : $scope.client,
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.client = serverData;
			$scope.errorMessage = null;
			console.log($scope.client);
		}).error(function(serverData) {
			$scope.errorMessage = serverData;
			console.log($scope.client);
		});
	};

	
});