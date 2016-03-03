app
		.controller(
				'AddAccountCtrl',
				function($scope, $routeParams, $http) {

					$scope.user = {
						username : null,
						password : null,
						status : null,
						roles:null
					}
					
					$scope.currentRole = null;
					$scope.roles = [];
					
					

					$scope.errorMessage = null;
					$scope.successMessage = null;
					
					$scope.addRole = function() {
						var role = {role:$scope.currentRole};
						$scope.roles.push(role);
					}
					
					$scope.removeRole = function(role) {
						$scope.roles.pop(role);
					}
					
					$scope.save = function() {
						$scope.user.roles = $scope.roles;
						$http({
							url : accountServicesPath + 'saveAccount',
							method : 'POST',
							data : $scope.user,
							headers : {
								'Content-Type' : 'application/json'
							},
						}).success(function(serverData) {
							window.location = appPath + "account/"+serverData.id;
						}).error(function(serverData) {
							$scope.errorMessage = serverData.message;
							$scope.successMessage = null;
						});
					};

				});