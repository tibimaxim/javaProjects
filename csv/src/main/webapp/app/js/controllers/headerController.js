app.controller('HeaderCtrl', function($scope, $http) {

	$scope.user = null;
	$scope.showAdminPages = false;

	$scope.getUserFromServices = function() {
		$http({
			url : accountServicesPath + 'getCurrentAccount',
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.user = serverData;
			$scope.showAdminPages = hasRole(serverData,'ADMIN');
		})

	};

	

});