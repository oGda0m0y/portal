
(function() {
	var _zsuuid = Cookies.get('_zsuuid');

	if(!_zsuuid){
		var uuid = Math.uuid();
		Cookies.set('_zsuuid', uuid);
	}
	
	
})();