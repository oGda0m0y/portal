var countdown=null;
var code=null;
//账号状态
var user_flag=false;


$('#r_loginBtn').on('click', function() {
	$("#myRegister").modal('hide');
	
	$("#myLogin").modal({
	    remote: "/portal/static/login.html"
	});
	
});


//注册成功后3秒自动登录
$('#registerBtn').on('click', function(){
	var username = $("#reg_user_name").val();
	var password = $("#reg_pwd").val();
	var pwd2 = $("#reg_pwd2").val();
	var code = $("#rcode").val();
	
	if(password==''||password==null||password==undefined){
		layer.msg("请输入密码")
		return false;
	}
	if(pwd2==''||pwd2==null||pwd2==undefined){
		layer.msg("请再次确认密码")
		return false;
	}
	if(password!=pwd2){
		layer.msg("密码不一致")
		return false;
	}

	
	var user = {};
	user.user_name = username;
	user.password = password;
	user.code = code;
	$.post("/action/user/u/regUser", user, function(data) {
		if (data.code == 200) {
			
			window.location.href="/";
		} else {
			layer.msg(data.msg)
		}
	});
	
	
})

//获取验证码
	$('#g_code').on('click', function() {
		countdown=60;
		
		var regNumber = /\d+/;//验证0-9的任意数字最少出现1次。
		var regString = /[a-zA-Z]+/; //验证大小写26个字母任意字母最少出现1次。
		var username=$("#reg_user_name").val();
		var type="";
		if(username.indexOf("@")>=0||username.indexOf(".com")>=0||username.indexOf(".cn")>=0||username.indexOf(".COM")>=0||username.indexOf(".CN")>=0){
			type="email";
		}else if(regNumber.test(username)&&(!regString.test(username))&&(escape(username).indexOf("%u")==-1)){
			type="phone";
		}else if(username.match(/\D/)!=null){
			layer.msg('不能含有字母'); 
//			return;
		}else if(escape(username).indexOf("%u")!=-1){
			layer.msg("不能含有汉字"); 
//			return;
		}
		$.post("/action/user/u/sendCode", {
			"mobile" :username,
			"type" :type
		}, function(result) {
			if (result.code == 200) {
				layer.msg("短信已发送");
				setInterval(settime, 1000);
			} else {
				layer.msg("验证码发送失败，请60秒后再试");
			}
			
		})
		
		
	})
	
	
	//60秒倒计时读秒发送验证码


	function settime(){
		var val=$("#g_code");
		var username=$("#reg_user_name").val();
		if (countdown == 0) { 
			val.removeAttr("disabled");    
			val.val("重新获取"); 
			val.onclick=null;
		} else { 
			val.attr("disabled", true); 
			val.val("发送验证码(" + countdown + ")"); 
			countdown--; 
		} 
		
	}
//判断输入账号是否合法
//$("#reg_user_name").blur(function(){
//	var user_name=$("#reg_user_name").val();
//	if(user_name==""||user_name==null){
//		user_name.attr("placeholder","用户名不能为空");
//	}else{
////		layer.msg("发请求到后台判断账号是否存在");
//		if(true){
//			document.getElementById("username_true").style.display="";
//			document.getElementById("username_false").style.display="none";
//		}else{
//			document.getElementById("username_false").style.display="";
//			document.getElementById("username_true").style.display="none";
//		}
//	}
//})
////
//
////判断俩次密码是否相同
//$("#reg_pwd2").blur(function(){
//	var pwd=$("#reg_pwd").val();
//	var pwd1=$("#reg_pwd2").val();
//	if(pwd==pwd1){
//		document.getElementById("pwd1_true").style.display="";
//		document.getElementById("pwd1_false").style.display="none";
//	}else{
//		document.getElementById("pwd1_false").style.display="";
//		document.getElementById("pwd1_true").style.display="none";
//	}
//})
