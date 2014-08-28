function LoginController($scope,$rootScope,$location,Restangular){
	$scope.user.username="";
	$scope.user.password="";
	
	$scope.login = function(){
		Restangular.all("login").post($scope.user).then(function (user){
			$rootScope.user = user;
			$rootScope.logined = true;
		},function(error){
			$rootScope.user = {};
			$rootScope.logined = false;
			$root.loginerr = error;
		});
	}
	
}