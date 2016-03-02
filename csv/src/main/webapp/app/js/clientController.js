app.controller('ClientCtrl', function($scope, $routeParams, $http) {

	$scope.id = $routeParams.id;
	$scope.errorMessage = null;
	$scope.successMessage = null;

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

	$scope.save = function() {
		//get date from datepicker, cast it as date and replace the on in $scope
		var dateOfBirthSelected = $("#dateOfBirthDatePicker").val();
		var expireDateSelected = $("#expireDateDatePicker").val();
		var pattern = /(\d{2})\-(\d{2})\-(\d{4})/;
		var dateOfBirth = new Date(dateOfBirthSelected.replace(pattern,'$3-$2-$1'));
		var expireDate = new Date(expireDateSelected.replace(pattern,'$3-$2-$1'));
		$scope.client.dateOfBirth = dateOfBirth;
		$scope.client.expireDate = expireDate;

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
			$scope.successMessage = "Datele au fost modificate cu success!";
		}).error(function(serverData) {
			$scope.errorMessage = serverData;
			$scope.successMessage = null;
		});
	};

	
});