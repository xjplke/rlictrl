'use strict';

var rlictrlApp = angular.module('rlictrlApp',[
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
		
		//config RestangularProvider
    	RestangularProvider.addResponseInterceptor(function(data, operation, what, url, response, deferred) {
			var extractedData;
			if (operation === "getList" && data.content !== "undefine") {//getList need response must be list, change  warpper data formate
				//Restangular 的getList要求必须返回list对象，而实际接口返回的是page对象，所以这里处理了一下，把page里面的list作为最后的返回值，而将page对象挂在list对象下面了。
				extractedData = data.content;
				extractedData.page = data;
			}else{
				extractedData = data;
			}
			return extractedData;
		});
	}
]);
