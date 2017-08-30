var userController = require('./user-controller');
(function(){
	$(document).ready(function(){
		userController.init(configConstants);
	});
}());
