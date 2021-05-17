<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>安全设置</title>
<%@ include file="../../static/top.jsp"%>


<link href="/web/res/css/safety.css" rel="stylesheet">
<link href="/web/res/css/safety-style.css" rel="stylesheet">
<link href="/web/res/css/style-responsive.css" rel="stylesheet">
</head>
<body class="sticky-header">

	<section> <!-- left side start--> <%@ include
		file="../../static/menu.jsp"%> <!-- left side end-->

	<!-- main content start-->
	<div class="main-content">

		<!-- header section start-->


		<%@ include file="../../static/header.jsp"%>

		<div class="wrapper">
			<div class="page-heading">
				<ul class="breadcrumb">
					<li><a href="#">个人中心</a></li>
					<li class="active">安全设置</li>
				</ul>
			</div>
			<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">安全设置</div>
							<div class="panel-body"></div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="panel">
							<div class="panel-body">
								<div class="Safety">
									<dl>
										<dt>
											<strong>登录密码：</strong> <span>保障账户安全，建议您定期更换密码</span> <b><span
												class="glyphicon glyphicon-ok"> </span>已经设置</b> <em>修改</em>
										</dt>
										<dd id="pwdDiv">
											<form action="#" method="get"  onSubmit="return false;">
												<table width="100%" class="safForm">
													<tr>
														<td width="35%" align="right">当前密码：</td>
														<td><input id="curPwd" type="password" class="safinput1" /> </td>
													</tr>
													<tr>
														<td width="35%" align="right">设置新密码：</td>
														<td><input id="newPwd" type="password" class="safinput1" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">确认新密码：</td>
														<td><input id="newRepPwd" type="password" class="safinput1" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">&nbsp;</td>
														<td><input id="chgPwdBtn" type="button" class="safSub"	value="修改登录密码" /></td>
													</tr>
												</table>
											</form>
										</dd>
										<dt>
											<strong>手机号码：</strong> <span>保障账户与信息安全，是您在智数重要的身份凭证</span>
											<b id="setMobile"><span class="glyphicon glyphicon-exclamation-sign"> </span>未设置 </b> <em>修改</em>
										</dt>
										<dd id="mobileDiv">
											<ul class="Step">
												<li class="stepCur"><span>1</span></li>
												<li><span>2</span></li>
												<li><span>3</span></li>
												<div class="clearfix"></div>
											</ul>
											<!--Step/-->
											<form action="#" method="get" class="sjyz-one"  onSubmit="return false;">
												<table width="100%" class="safForm">
													<tr>
														<td width="35%" align="right">手机号：</td>
														<td><input type="text" class="safinput1" id="mobile" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">验证码：</td>
														<td><input type="text" class="safinput2" id="mobileCode" />
															<button id="mobileCodeBtn">点击获取</button></td>
													</tr>
													<tr>
														<td width="35%" align="right">&nbsp;</td>
														<td><a id="upMobileOneBtn" class="safSub sjyz-one-next"  style="text-decoration:none;"
															href="javascript:;">下一步</a></td>
													</tr>
												</table>
											</form>
											<form action="#" method="get" class="sjyz-two"  onSubmit="return false;">
												<table width="100%" class="safForm">
													<tr>
														<td width="35%" align="right">新手机号：</td>
														<td><input id="newMobile" type="text" class="safinput1" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">验证码：</td>
														<td><input id="newMobileCode"  type="text" class="safinput2" />
															<button id="newMobileCodeBtn">点击获取</button></td>
													</tr>
													<tr>
														<td width="35%" align="right">&nbsp;</td>
														<td><a class="safSub sjyz-two-next"  id="upMobileTwoBtn"  style="text-decoration:none;"
															href="javascript:;">下一步</a></td>
													</tr>
												</table>
											</form>
											<div class="sjyz-ok">
												<span class="glyphicon glyphicon-ok"></span> 恭喜您,设置成功!
											</div>
										</dd>

										<dt>
											<strong>电子邮箱：</strong> <span>邮件接收账户通知，及时了解账户信息变动情况</span> <b id="setMail"><span
												class="glyphicon glyphicon-exclamation-sign"> </span>未设置 </b> <em>认证</em>
										</dt>
										<dd id="emailDiv">
											<ul class="Step">
												<li class="stepCur"><span>1</span></li>
												<li><span>2</span></li>
												<li><span>3</span></li>
												<div class="clearfix"></div>
											</ul>
											<!--Step/-->
											<form action="#" method="get" class="sjyz-one"  onSubmit="return false;">
												<table width="100%" class="safForm">
													<tr>
														<td width="35%" align="right">当前邮箱：</td>
														<td><input type="text" id="curEmail" class="safinput1" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">&nbsp;</td>
														<td><a id="upEmailOneBtn" class="safSub sjyz-one-next" style="text-decoration:none;"
															href="javascript:;">下一步</a></td>
													</tr>
												</table>
											</form>
											<form action="#" method="get" class="sjyz-two"  onSubmit="return false;">
												<table width="100%" class="safForm">
													<tr>
														<td width="35%" align="right">邮箱验证码：</td>
														<td><input id="newMailCode" type="text" class="safinput2" /></td>
													</tr>
													<tr>
														<td width="35%" align="right">&nbsp;</td>
														<td><a  id="upEmailTwoBtn" class="safSub sjyz-two-next"  style="text-decoration:none;"
															href="javascript:;">下一步</a></td>
													</tr>
												</table>
											</form>
											<div class="sjyz-ok">
												<span class="glyphicon glyphicon-ok"></span> 恭喜您,设置成功!
											</div>
										</dd>

									</dl>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
		<!--body wrapper end-->


		<!--footer section start-->
		<%@ include file="../../static/footer.jsp"%>
	
		<!--footer section end-->
		<script src="/web/my/settings/safety.js"></script>

	</div>
	<!-- main content end--> </section>
	
	<script type="text/javascript">
		
		
	$(document).ready(function(){
		
		
		var smobile = '${sessionScope._sessionkey.mobile}';
		var semail = '${sessionScope._sessionkey.email}';
		
		if(smobile!=''&&smobile!=null&&smobile!='null'&&smobile!=undefined){
			$("#setMobile").html("<span class='glyphicon glyphicon-ok'> </span>已经设置")
		}
		
		if(semail!=''&&semail!=null&&semail!='null'&&semail!=undefined){
			$("#setMail").html("<span class='glyphicon glyphicon-ok'> </span>已经设置")
		}
		
		
		  $("#chgPwdBtn").click(function(){
			var curPwd =  $("#curPwd").val();
			var newPwd =  $("#newPwd").val();
			var newRepPwd =  $("#newRepPwd").val();
		    if(curPwd==null||curPwd==''||curPwd==undefined){
		    	layer.msg("请输入当前密码")
		    	return;
		    }
		    if(newPwd==null||newPwd==''||newPwd==undefined){
		    	layer.msg("请输入新密码")
		    	return;
		    }
		    if(newRepPwd==null||newRepPwd==''||newRepPwd==undefined){
		    	layer.msg("请确认新密码")
		    	return;
		    }
		    
		    if(newPwd.length<4||newRepPwd<4){
		    	layer.msg("密码长度必须大于4位！");
		    	return ;
		    }
		    
		    if(newPwd!=newRepPwd){
		    	layer.msg("密码输入不匹配,请重新输入")
		    	return;
		    }
		    
		    curPwd = md5(curPwd);
		    newPwd = md5(newPwd);
			$.post("/action/my/s/upPwd", {"curPwd":curPwd,"newPwd":newPwd}, function(data) {
				if (data.code == 200) {
					layer.msg("操作成功")
					$("#curPwd").val("");
					$("#newPwd").val("");
					$("#newRepPwd").val("");
					$("#pwdDiv").hide();
				} else {
					layer.msg(data.msg)
				}
			});
			
			
		  });
		  
		  
		 
		  
		  
		  $("#mobileCodeBtn").click(function(){
			  var mobile = $("#mobile").val();
			  $.post("/action/user/u/sendCode", {
					"mobile" :mobile,
					"type" :"phone"
				}, function(result) {
					if (result.code == 200) {
						layer.msg("验证码发送成功");
					} else {
						layer.msg("验证码发送失败");
					}
					
				})
		  });
		  
		  
		  
		  $("#upMobileOneBtn").click(function(){
			  var mobile = $("#mobile").val();
			  var code = $("#mobileCode").val();
			  
			  if(mobile==null||mobile==''||mobile==undefined){
			    	layer.msg("请输入手机号码")
			    	return;
			  }
			  if(code==null||code==''||code==undefined){
			    	layer.msg("请输入验证码")
			    	return;
			  }
			  $.post("/action/user/u/checkCode", {"mobile":mobile,"code":code}, function(data) {
					if (data.code == 200) {
						 $(".sjyz-two").show();
						 $(".sjyz-one").hide();
						
						 $("#mobileDiv").find(".Step li:eq(1)").addClass("stepCur");
					} else {
						layer.msg(data.msg)
					}
			  });
			  
			  
			 
		  });
		  
		  
		  
		  $("#upEmailOneBtn").click(function(){
			  var curEmail = $("#curEmail").val();
			  if(checkMail(curEmail)){
				  
				  $.post("/action/user/u/sendCode", {
						"mobile" :curEmail,
						"type" :"email"
					}, function(result) {
						if (result.code == 200) {
							layer.msg("邮箱验证码发送成功");
							$(".sjyz-two").show();
							$(".sjyz-one").hide();
							$("#emailDiv").find(".Step li:eq(1)").addClass("stepCur");
						} else {
							layer.msg("验证码发送失败");
						}
						
					})
				  
				  
			  }
			  
		  });
		  
		  $("#upEmailTwoBtn").click(function(){
			  var curEmail = $("#curEmail").val();
			  var newMailCode = $("#newMailCode").val();
			  
			  
			  $.post("/action/my/s/upUserEmail", {
					"curEmail" :curEmail,
					"code" :newMailCode
				}, function(result) {
					if (result.code == 200) {
						layer.msg("邮箱验证码发送成功");
						$(".sjyz-ok").show();
						$(".sjyz-two").hide();
						$(".sjyz-one").hide();
						
						$("#emailDiv").find(".Step li:eq(2)").addClass("stepCur");
					} else {
						layer.msg("验证码发送失败");
					}
					
				})
			  
			  
			
		  });
		  
		  
		  
		  
		  $("#newMobileCodeBtn").click(function(){
			  var mobile = $("#newMobile").val();
			  $.post("/action/user/u/sendCode", {
					"mobile" :mobile,
					"type" :"phone"
				}, function(result) {
					if (result.code == 200) {
						layer.msg("验证码发送成功");
					} else {
						layer.msg("验证码发送失败");
					}
					
				})
		  });
		  
		  $("#upMobileTwoBtn").click(function(){
			  var newMobile = $("#newMobile").val();
			  var newMobileCode = $("#newMobileCode").val();
			  if(newMobile==null||newMobile==''||newMobile==undefined){
			    	layer.msg("请输入手机号码")
			    	return;
			  }
			  if(newMobileCode==null||newMobileCode==''||newMobileCode==undefined){
			    	layer.msg("请输入验证码")
			    	return;
			  }
			  $.post("/action/my/s/upNewMobile", {"newMobile":newMobile,"newMobileCode":newMobileCode}, function(data) {
					if (data.code == 200) {
						  $(".sjyz-ok").show();
						  $(".sjyz-two").hide();
						  $(".sjyz-one").hide();
						
						  $("#mobileDiv").find(".Step li:eq(2)").addClass("stepCur");
					} else {
						layer.msg(data.msg)
					}
			  });
			  
			
		  });
		 
		  
	});
		
	 function checkMail(mail){
	   var myreg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org|top|tv|com.cn)$/;
	   if(!myreg.test(mail)){
            layer.msg('提示\n\n请输入有效的邮箱！');
       	   return false;
       }else{
    	   return true;
       }
    }
	</script>

</body>
</html>
