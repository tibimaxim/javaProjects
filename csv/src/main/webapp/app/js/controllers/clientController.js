app.controller('ClientCtrl', function($scope, $routeParams, $http) {

	$scope.id = $routeParams.id;
	$scope.errorMessage = null;
	$scope.successMessage = null;
	$scope.fullName = null;
	
	$scope.overlay = false;
	
	$scope.toggleDeletePopUp = function() {
		$scope.overlay = !$scope.overlay;
	}

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
	
	$scope.buildFullName = function (){
		//compose client full name
		if ($scope.client.lastName !== null) {
			$scope.fullName = $scope.client.lastName;
		};
		if ($scope.client.firstName1 !== null) {
			$scope.fullName += " "+ $scope.client.firstName1;
		};
		if ($scope.client.firstName2 !== null) {
			$scope.fullName += " "+ $scope.client.firstName2;
		};
	};

	$scope.getClient = function() {
		$http({
			url : clientServicesPath + 'getClient?id=' + $scope.id,
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.client = serverData;
			$scope.buildFullName();
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

	$scope.deleteClient = function() {
		$scope.overlay = false;
		$http({
			url : clientServicesPath + 'deleteClient',
			method : 'POST',
			params : {id : $scope.id},
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			$scope.errorMessage = null;
			$scope.successMessage = "Clientul a fost sters";
		}).error(function(serverData) {
			$scope.errorMessage = serverData;
			$scope.successMessage = null;
		});
		
	};
	
});