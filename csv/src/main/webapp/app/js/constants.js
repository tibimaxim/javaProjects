var host = window.location.host;
var clientServicesPath = 'http://'+host+'/csv/client/';
var accountServicesPath = 'http://'+host+'/csv/account/';
var basePath ='http://'+host+'/csv/';
var appPath = 'http://'+host+'/csv/app/#/';

hasRole = function(user,role) {

	if (user=== null || user.roles === null
			|| user.roles.length < 1) {
		return false;
	};	
	
	for (var i=0;i<user.roles.length;i++) {
		if (user.roles[i] !== null && user.roles[i].role !== null && user.roles[i].role == role) {
			return true;
		}
	}
	return false;
};