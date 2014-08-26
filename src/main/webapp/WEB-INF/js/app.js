'use strict';

var rlictrlApp = angulr.module('rlictrlApp',[
    'ngRoute',
    'restangular',
    'ui.bootstrap'
]);


rlictrlApp.config(['$routeProvider','RestangularProvider',
	function($routeProvider,RestangularProvider){
		$routeProvider.
			when('/',{
				templateUrl: 'partials/client/list.html',
				controller: 'ClientListCtrl'
			}).
			otherwise({
    			redirectTo: '/'
    		});
	}
]);
