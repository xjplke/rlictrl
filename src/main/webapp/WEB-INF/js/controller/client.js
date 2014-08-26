function ClientListCtrl($scope,$location, Restangular) {
	$scope.searchby = 'type';
	$scope.key = '';
	
	var searchpath = function(s){
		return s!=''?"/"+s:"";
	}
	
	$scope.page = {number:1,size:10};
	$scope.pageChanged = function(){
		var searchpath = ($scope.searchby!=''&&$scope.key!='')?"/"+$scope.searchby+"/"+$scope.key:null;
		Restangular.all("client").customGETLIST(searchpath,{'page':$scope.page.number-1,'size':$scope.page.size}).then(function(clientlist){
			$scope.clientlist = clientlist;
			$scope.page = clientlist.page;
			$scope.page.number = $scope.page.number + 1;
		});
	}

	$scope.pageChanged();//first call
}

