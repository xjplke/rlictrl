
rlictrlApp.filter('shortcut',function(){
	return function(s,len){
		if(typeof(s)=='undefine'){
			return '';
		}
		if(typeof(s)!='string'){
			return s;
		}
		var length = len | 20;
		if(s.length > length){
			return s.substr(0,length-4)+'...';
		}
		return s;
	};
});

rlictrlApp.filter('booltoicontag',function(){
	return function(b){
		if(b==true){
			return "ok";
		}
		return "remove";
	}
});
