app
		.controller(
				'AccountCtrl',
				function($scope, $routeParams, $http) {
					
					$scope.id = $routeParams.id;
					
					$scope.user = null;
					$scope.newPassword = null;
					$scope.confirmPassword = null;
					
					$scope.isAdmin=false;
					
					$scope.currentRole = null;
					
					$scope.errorMessage = null;
					$scope.successMessage = null;

					// on load call getData to pre-fill client
					$scope.$on('$viewContentLoaded', function($evt, data) {
						$scope.getUser($routeParams.id);
						$scope.getUserFromServices();
					});
					
					$scope.removeRole = function(role) {
						$scope.user.roles.pop(role);
					}
					
					$scope.addRole = function() {
						var role = {role:$scope.currentRole};
						console.log($scope.currentRole);
						$scope.user.roles.push(role);
					}

					$scope.getUser = function(id) {
						$http({
							url : accountServicesPath + 'getAccount?id='+id,
							method : 'GET',
							headers : {
								'Content-Type' : 'application/json'
							},
						}).success(function(serverData) {
							$scope.user = serverData;
							console.log($scope.user);
						});
					};

					$scope.changePassword = function() {
						console.log($scope.passwordChange);
						// validate password
						if ($scope.passwordChange === null
								|| $scope.newPassword === null
								|| $scope.confirmPassword === null
								|| $scope.newPassword !== $scope.confirmPassword) {
							$scope.errorMessage = "Parola noua si parola confirmata nu sunt identice si/sau completate.";
							return;
						}
						;
						var parameters = {
							password : null,
							id : null
						};
						parameters.password = $scope.newPassword;
						parameters.id = $scope.id;
						$http(
								{
									url : accountServicesPath
											+ 'changePasswordForUser',
									method : 'POST',
									params : parameters,
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
								})
								.success(
										function(serverData) {
											$scope.errorMessage = null;
											$scope.successMessage = "Parola a fost schimbata cu succes.";
										}).error(function(serverData) {
									$scope.errorMessage = serverData.message;
									$scope.successMessage = null;
								});
					};
					
					$scope.getUserFromServices = function() {
						$http({
							url : accountServicesPath + 'getCurrentAccount',
							method : 'GET',
							headers : {
								'Content-Type' : 'application/json'
							},
						}).success(function(serverData) {
							$scope.isAdmin = hasRole(serverData,'ADMIN');
						})

					};
					
					$scope.saveRoles = function() {
						$http({
							url : accountServicesPath + 'updateRoles',
							method : 'POST',
							data : $scope.user.roles,
							params : {id:$scope.id},
							headers : {
								'Content-Type' : 'application/json'
							},
						}).success(function(serverData) {
							$scope.successMessage = 'Rolurile au fost modificate';
						}).error(function(serverData) {
							$scope.errorMessage = serverData.message;
							$scope.successMessage = null;
						});
					};
					
					$scope.blockUnblockAccount = function() {
						$http({
							url : accountServicesPath + 'blockUnblockAccount',
							method : 'POST',
							params : {id:$scope.id},
							headers : {
								'Content-Type' : 'application/json'
							},
						}).success(function(serverData) {
							$scope.successMessage = 'Statusul utilizatorului a fost modificat';
							$scope.user = serverData;
						}).error(function(serverData) {
							$scope.errorMessage = serverData.message;
							$scope.successMessage = null;
						});
					};

				});