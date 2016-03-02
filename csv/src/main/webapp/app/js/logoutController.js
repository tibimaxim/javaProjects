app.controller('LogoutCtrl', function($scope, $routeParams, $http) {
	
	// on load call getData to pre-fill client
	$scope.$on('$viewContentLoaded', function($evt, data) {
		$scope.logout();
	});
	
	$scope.logout = $http({
		url : basePath + 'logout',
		method : 'POST',
		data : $scope.client,
		headers : {
			'Content-Type' : 'application/json'
		},
	}).success(function(serverData) {
		window.location = basePath + '?op=logout';
	}).error(function(serverData) {
		
	});

});