
(function() {
	var _zsuuid = Cookies.get('_zsuuid');

	if(!_zsuuid){
		var uuid = Math.uuid();
		Cookies.set('_zsuuid', uuid);
	}
	$.post("/action/user/u/islogin", function(data) {
		if (data.code == 200) {
			$("#loginLi").html("<a  href='javascript:void(0);' class='dropdown-toggle' data-toggle='dropdown'  style='font-size:16px;'>"+data.data.user_name+"</a><ul class='dropdown-menu' style='margin-top:-1px;'><li><a href='/action/my/m/toMyCenter'><i class='fa fa-user'></i>  个人中心</a></li> <li><a href='/action/my/m/logout'><i class='fa fa-sign-out'></i>  退出</a></li>  </ul>");
		}
	});
	
})();