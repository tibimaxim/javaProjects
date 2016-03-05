app.controller('AddClientCtrl', function($scope, $http) {

	
	$scope.errorMessage = null;
	$scope.successMessage = null;
	//for new client
	$scope.fullName = "Nou";
	
	$scope.showUploadForm = false;
	$scope.showUploadButton=true;
	$scope.uploadFile = null;
	
	
	$scope.toggleShowUploadForm = function(){
		$scope.showUploadForm = !$scope.showUploadForm;
		$scope.uploadFile = null;
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
			url : clientServicesPath + 'saveClient',
			method : 'POST',
			data : $scope.client,
			headers : {
				'Content-Type' : 'application/json'
			},
		}).success(function(serverData) {
			window.location = appPath + "client/"+serverData.id;
		}).error(function(serverData) {
			$scope.errorMessage = serverData;
			$scope.successMessage = null;
		});
	};
	
	$scope.saveFromFile = function() {
		 var byteArray = $scope.uploadFile.data.replace(/data:image\/jpeg;base64,/g, '');

        $http({
            url: clientServicesPath + 'saveIdentityCardData',
			method : 'POST',
			data : {identityCardOcrAreaScan: byteArray},
			headers : {
				'Content-Type' : 'application/json'
			},

        })
		.success(function(serverData) {
			window.location = appPath + "client/"+serverData.id;
		}).error(function(serverData) {
			$scope.errorMessage = serverData;
			$scope.successMessage = null;
		});
	};

	
});