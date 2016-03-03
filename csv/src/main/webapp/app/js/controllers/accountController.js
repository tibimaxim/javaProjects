app
		.controller(
				'AccountCtrl',
				function($scope, $routeParams, $http) {
					
					$scope.id = $routeParams.id;
					
					$scope.user = null;
					$scope.newPassword = null;
					$scope.confirmPassword = null;

					$scope.errorMessage = null;
					$scope.successMessage = null;

					// on load call getData to pre-fill client
					$scope.$on('$viewContentLoaded', function($evt, data) {
						$scope.getUser($routeParams.id);
					});

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

				});