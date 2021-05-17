<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>安全设置</title>
<%@ include file="../../static/top.jsp"%>
<link href="/web/res/css/amazeui.min.css" rel="stylesheet">
<link href="/web/res/css/amazeui.cropper.css" rel="stylesheet">
<link href="/web/res/css/custom_up_img.css" rel="stylesheet">
<link href="/web/res/fonts/css/font-awesome.css" type="text/css" rel="stylesheet" >
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
					<li class="active">个人账户</li>
				</ul>
			</div>
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default" >
								<div class="panel-heading">个人账户</div>
								<div class="panel-body" >
								
							<div class="col-md-12">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="col-md-12">
								<div class="panel" style="border:1px solid #ddd">
									<div class="panel-body">
											<div class="profile-pic text-center" id="up-img-touch">
												<img id = "imgPath" class="am-circle" alt="点击图片上传" src=""
													data-am-popover="{content: '点击上传', trigger: 'hover focus'}">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="panel" style="border:1px solid #ddd">
									<div class="panel-body" >
										<ul class="p-info">
											<li>
												<div class="title">
													<font><font >用户名：</font></font>
												</div>
												<div class="desk">
													<font><font class="update" id="text_username" >${sessionScope._sessionkey.user_name}</font></font>
												</div>
											</li>
											<li>
												<div class="title">
													<font><font >公司名称：</font></font>
												</div>
												<div class="desk">
													<font><input id ="company" type="text"  value="${user.company}"/></font>
												</div>
											</li>
											<li>
												<div class="title">
													<font>省：</font>
												</div>
												<div class="desk">
													<select id="prv"><option value="">请选择...</option></select>
												</div>
												
											</li>
											<li>
												<div class="title">
													<font>市：</font>
												</div>
												
												<div class="desk">
													<select id="city"><option value="">请选择...</option></select>
												</div>
											</li>
											<li>
												<div class="title">
													<font>区：</font>
												</div>
												
												<div class="desk">
												<select id="area"><option value="">请选择...</option></select>
												</div>
											</li>
											<li>
												<div class="title">
													<font>所属行业：</font>
												</div>
												<div class="desk">
													<select id="business">
													  <option value="">选择行业</option>
													  <option value="高新科技">高新科技</option>
													  <option value="互联网">&nbsp;&nbsp;&nbsp;互联网</option>
													  <option value="电子商务">&nbsp;&nbsp;&nbsp;电子商务</option>
													  <option value="电子游戏">&nbsp;&nbsp;&nbsp;电子游戏</option>
													  <option value="计算机软件">&nbsp;&nbsp;&nbsp;计算机软件</option>
													  <option value="计算机硬件">&nbsp;&nbsp;&nbsp;计算机硬件</option>
													  <option value="信息传媒">信息传媒</option>
													  <option value="出版业">&nbsp;&nbsp;&nbsp;出版业</option>
													  <option value="电影录音">&nbsp;&nbsp;&nbsp;电影录音</option>
													  <option value="广播电视">&nbsp;&nbsp;&nbsp;广播电视</option>
													  <option value="通信">&nbsp;&nbsp;&nbsp;通信</option>
													  <option value="金融">金融</option>
													  <option value="银行">&nbsp;&nbsp;&nbsp;银行</option>
													  <option value="资本投资">&nbsp;&nbsp;&nbsp;资本投资</option>
													  <option value="证券投资">&nbsp;&nbsp;&nbsp;证券投资</option>
													  <option value="保险">&nbsp;&nbsp;&nbsp;保险</option>
													  <option value="信贷">&nbsp;&nbsp;&nbsp;信贷</option>
													  <option value="财务">&nbsp;&nbsp;&nbsp;财务</option>
													  <option value="审计">&nbsp;&nbsp;&nbsp;审计</option>
													  <option value="服务业">服务业</option>
													  <option value="法律">&nbsp;&nbsp;&nbsp;法律</option>
													  <option value="餐饮">&nbsp;&nbsp;&nbsp;餐饮</option>
													  <option value="酒店">&nbsp;&nbsp;&nbsp;酒店</option>
													  <option value="旅游">&nbsp;&nbsp;&nbsp;旅游</option>
													  <option value="广告">&nbsp;&nbsp;&nbsp;广告</option>
													  <option value="公关">&nbsp;&nbsp;&nbsp;公关</option>
													  <option value="景观">&nbsp;&nbsp;&nbsp;景观</option>
													  <option value="咨询分析">&nbsp;&nbsp;&nbsp;咨询分析</option>
													  <option value="市场推广">&nbsp;&nbsp;&nbsp;市场推广</option>
													  <option value="人力资源">&nbsp;&nbsp;&nbsp;人力资源</option>
													  <option value="社工服务">&nbsp;&nbsp;&nbsp;社工服务</option>
													  <option value="养老服务">&nbsp;&nbsp;&nbsp;养老服务</option>
													  <option value="教育">教育</option>
													  <option value="高等教育">&nbsp;&nbsp;&nbsp;高等教育</option>
													  <option value="基础教育">&nbsp;&nbsp;&nbsp;基础教育</option>
													  <option value="职业教育">&nbsp;&nbsp;&nbsp;职业教育</option>
													  <option value="幼儿教育">&nbsp;&nbsp;&nbsp;幼儿教育</option>
													  <option value="特殊教育">&nbsp;&nbsp;&nbsp;特殊教育</option>
													  <option value="培训">&nbsp;&nbsp;&nbsp;培训</option>
													  <option value="医疗服务">医疗服务</option>
													  <option value="临床医疗">&nbsp;&nbsp;&nbsp;临床医疗</option>
													  <option value="制药">&nbsp;&nbsp;&nbsp;制药</option>
													  <option value="保健">&nbsp;&nbsp;&nbsp;保健</option>
													  <option value="美容">&nbsp;&nbsp;&nbsp;美容</option>
													  <option value="医疗器材">&nbsp;&nbsp;&nbsp;医疗器材</option>
													  <option value="生物工程">&nbsp;&nbsp;&nbsp;生物工程</option>
													  <option value="疗养服务">&nbsp;&nbsp;&nbsp;疗养服务</option>
													  <option value="护理服务">&nbsp;&nbsp;&nbsp;护理服务</option>
													  <option value="艺术娱乐">艺术娱乐</option>
													  <option value="创意艺术">&nbsp;&nbsp;&nbsp;创意艺术</option>
													  <option value="体育健身">&nbsp;&nbsp;&nbsp;体育健身</option>
													  <option value="娱乐休闲">&nbsp;&nbsp;&nbsp;娱乐休闲</option>
													  <option value="图书馆">&nbsp;&nbsp;&nbsp;图书馆</option>
													  <option value="博物馆">&nbsp;&nbsp;&nbsp;博物馆</option>
													  <option value="策展">&nbsp;&nbsp;&nbsp;策展</option>
													  <option value="博彩">&nbsp;&nbsp;&nbsp;博彩</option>
													  <option value="制造加工">制造加工</option>
													  <option value="食品饮料业">&nbsp;&nbsp;&nbsp;食品饮料业</option>
													  <option value="纺织皮革业">&nbsp;&nbsp;&nbsp;纺织皮革业</option>
													  <option value="服装业">&nbsp;&nbsp;&nbsp;服装业</option>
													  <option value="烟草业">&nbsp;&nbsp;&nbsp;烟草业</option>
													  <option value="造纸业">&nbsp;&nbsp;&nbsp;造纸业</option>
													  <option value="印刷业">&nbsp;&nbsp;&nbsp;印刷业</option>
													  <option value="化工业">&nbsp;&nbsp;&nbsp;化工业</option>
													  <option value="汽车">&nbsp;&nbsp;&nbsp;汽车</option>
													  <option value="家具">&nbsp;&nbsp;&nbsp;家具</option>
													  <option value="电子电器">&nbsp;&nbsp;&nbsp;电子电器</option>
													  <option value="机械设备">&nbsp;&nbsp;&nbsp;机械设备</option>
													  <option value="塑料工业">&nbsp;&nbsp;&nbsp;塑料工业</option>
													  <option value="金属加工">&nbsp;&nbsp;&nbsp;金属加工</option>
													  <option value="军火">&nbsp;&nbsp;&nbsp;军火</option>
													  <option value="地产建筑">地产建筑</option>
													  <option value="房地产">&nbsp;&nbsp;&nbsp;房地产</option>
													  <option value="装饰装潢">&nbsp;&nbsp;&nbsp;装饰装潢</option>
													  <option value="物业服务">&nbsp;&nbsp;&nbsp;物业服务</option>
													  <option value="特殊建造">&nbsp;&nbsp;&nbsp;特殊建造</option>
													  <option value="建筑设备">&nbsp;&nbsp;&nbsp;建筑设备</option>
													  <option value="贸易零售">贸易零售</option>
													  <option value="零售">&nbsp;&nbsp;&nbsp;零售</option>
													  <option value="大宗交易">&nbsp;&nbsp;&nbsp;大宗交易</option>
													  <option value="进出口贸易">&nbsp;&nbsp;&nbsp;进出口贸易</option>
													  <option value="公共服务">公共服务</option>
													  <option value="政府">&nbsp;&nbsp;&nbsp;政府</option>
													  <option value="国防军事">&nbsp;&nbsp;&nbsp;国防军事</option>
													  <option value="航天">&nbsp;&nbsp;&nbsp;航天</option>
													  <option value="科研">&nbsp;&nbsp;&nbsp;科研</option>
													  <option value="给排水">&nbsp;&nbsp;&nbsp;给排水</option>
													  <option value="水利能源">&nbsp;&nbsp;&nbsp;水利能源</option>
													  <option value="电力电网">&nbsp;&nbsp;&nbsp;电力电网</option>
													  <option value="公共管理">&nbsp;&nbsp;&nbsp;公共管理</option>
													  <option value="环境保护">&nbsp;&nbsp;&nbsp;环境保护</option>
													  <option value="非盈利组织">&nbsp;&nbsp;&nbsp;非盈利组织</option>
													  <option value="开采冶金">开采冶金</option>
													  <option value="煤炭工业">&nbsp;&nbsp;&nbsp;煤炭工业</option>
													  <option value="石油工业">&nbsp;&nbsp;&nbsp;石油工业</option>
													  <option value="黑色金属">&nbsp;&nbsp;&nbsp;黑色金属</option>
													  <option value="有色金属">&nbsp;&nbsp;&nbsp;有色金属</option>
													  <option value="土砂石开采">&nbsp;&nbsp;&nbsp;土砂石开采</option>
													  <option value="地热开采">&nbsp;&nbsp;&nbsp;地热开采</option>
													  <option value="交通仓储">交通仓储</option>
													  <option value="邮政">&nbsp;&nbsp;&nbsp;邮政</option>
													  <option value="物流递送">&nbsp;&nbsp;&nbsp;物流递送</option>
													  <option value="地面运输">&nbsp;&nbsp;&nbsp;地面运输</option>
													  <option value="铁路运输">&nbsp;&nbsp;&nbsp;铁路运输</option>
													  <option value="管线运输">&nbsp;&nbsp;&nbsp;管线运输</option>
													  <option value="航运业">&nbsp;&nbsp;&nbsp;航运业</option>
													  <option value="民用航空业">&nbsp;&nbsp;&nbsp;民用航空业</option>
													  <option value="农林牧渔">农林牧渔</option>
													  <option value="种植业">&nbsp;&nbsp;&nbsp;种植业</option>
													  <option value="畜牧养殖业">&nbsp;&nbsp;&nbsp;畜牧养殖业</option>
													  <option value="林业">&nbsp;&nbsp;&nbsp;林业</option>
													  <option value="渔业">&nbsp;&nbsp;&nbsp;渔业</option>
													</select>

												</div>
											</li>
											<li>
												<div class="panel-body">
													<div style="text-align: right" id="update" ><a  class='btn btn-success'>保存</a></div>
													<!-- Modal -->
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
								
								</div>
							</div>
						</div>
					</div>
					

			<div class="am-modal am-modal-no-btn up-modal-frame" tabindex="-1" id="up-modal-frame">
					<div class="am-modal-dialog up-frame-parent up-frame-radius">
						<div class="am-modal-hd up-frame-header">
							<label>修改头像</label> <a href="javascript: void(0)"
								class="am-close am-close-spin" data-am-modal-close>&times;</a>
						</div>
						<div class="am-modal-bd  up-frame-body">
						<form enctype="multipart/form-data">
							<div class="am-g am-fl">

								<div class="am-form-group am-form-file">
									<div class="am-fl">
										<button type="button" class="am-btn am-btn-default am-btn-sm">
											<i class="am-icon-cloud-upload"></i> 选择要上传的文件
										</button>
									</div>
									<input type="file" id="fileId" class="up-img-file" name="file"> 
								</div>
							</div>
							<div class="am-g am-fl">
								<div class="up-pre-before up-frame-radius">
									<img alt="" src="" class="up-img-show" id="up-img-show">
								</div>
								<div class="up-pre-after up-frame-radius"></div>
							</div>
							<div class="am-g am-fl">
								<div class="up-control-btns">
									<span class="am-icon-rotate-left" id="up-btn-left"></span> 
									<span class="am-icon-rotate-right" id="up-btn-right"></span> 
									<span class="am-icon-check up-btn-ok" url="/admin/user/upload.action" parameter="{width:'100',height:'100'}"> </span>
								</div>
							</div>
							</form>
						</div>
					</div>
					
				</div>

				<!--加载框-->
				<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
					id="up-modal-loading">
					<div class="am-modal-dialog">
						<div class="am-modal-hd">正在上传...</div>
						<div class="am-modal-bd">
							<span class="am-icon-spinner am-icon-spin"></span>
						</div>
					</div>
				</div>

				<!--警告框-->
				<div class="am-modal am-modal-alert" tabindex="-1"
					id="up-modal-alert">
					<div class="am-modal-dialog">
						<div class="am-modal-hd">信息</div>
						<div class="am-modal-bd" id="alert_content">成功了</div>
						<div class="am-modal-footer">
							<span class="am-modal-btn">确定</span>
						</div>
					</div>
				</div>
		</div>
		<!--body wrapper end-->


		<!--footer section start-->
		<%@ include file="../../static/footer.jsp"%>
	
		<!--footer section end-->
		<!-- <script src="/web/res/js/ajaxfileupload.js"></script> -->
	
		<script src="/web/res/js/amazeui.min.js" charset="utf-8"></script>
		<script src="/web/res/js/cropper.min.js" charset="utf-8"></script>
	    
		<script src="/web/my/settings/up_img.js" charset="utf-8"></script>
		
	</div>
	<!-- main content end--> </section>
	<script>
	 var prvList = ${prvlist};
	 
	 for(var i=0;i<prvList.length;i++){
		 var pdata = prvList[i];
		 $("#prv").append("<option value='"+pdata.id+"' >"+pdata.name+"</option>");
	 }
	 
	 
	 
	  $(document).ready(function() {
		  
		  var img = '${sessionScope._sessionkey.img}';
		  
		  if(img==''||img=='null'||img==undefined){
			  $("#imgPath").attr("src","/web/res/images/photos/user-avatar.png");
		  }else{
			  $("#imgPath").attr("src",img);
		  }
		  
		  var prv_code =  '${user.prv_code}';
		  var prv_name =  '${user.prv_name}';
		
		  var city_code =  '${user.city_code}';
		  var city_name =  '${user.city_name}';
		  
		  var area_code =  '${user.area_code}';
		  var area_name =  '${user.area_name}';
		  
		  var business =  '${user.business}';
		  
		  if(business!=''||business!='null'||business!=undefined){
			  $("#business").val(business);
		  }

		  if(prv_code!=''||prv_code!='null'||prv_code!=undefined){
			  $("#prv").val(prv_code);
		  }
		  
		  if(city_code!=''||city_code!='null'||city_code!=undefined){
			  $("#city").append("<option value='"+city_code+"' selected>"+city_name+"</option>");
		  }
		  if(area_code!=''||area_code!='null'||area_code!=undefined){
			  $("#area").append("<option value='"+area_code+"' selected>"+area_name+"</option>");
		  }
		
		 
		  
		
		  $("#update").click(function() {
			  
			  var company = $("#company").val();
			  
			  var imgPath = $("#imgPath").attr("src");
			  
			  var prv= $("#prv").val();
			  
			  var city= $("#prv").val();
			  
			  var area= $("#area").val();
			  var business= $("#business").val();
			  
			  $.get("/action/my/s/upUserinfo",{"company":company,"imgPath":imgPath,"prv":prv,"city":city,"area":area,"business":business},function(data){
				  if(data.code==200){
					  layer.msg("操作成功")
			      }else{
					layer.msg(data.msg)
				  }
			  })
			  
		  })
		  
		  $('#prv').change(function(){ 
				 var pid =$(this).children('option:selected').val();
				 
				 if(pid!='0'&&pid!=''&&pid!=null){
					 $('#city').empty();
					 $.get("/action/my/s/getRegionByPid",{"parentCode":pid},function(data){
						  if(data.code==200){
							  var cityList = data.data;
							  
							  for(var i=0;i<cityList.length;i++){
							 	 var cdata = cityList[i];
							 	 $("#city").append("<option value='"+cdata.id+"' >"+cdata.name+"</option>");
							  } 
							  var c = $("#city").val();
							  $.get("/action/my/s/getRegionByPid",{"parentCode":c},function(data){
								  $('#area').empty();
								  if(data.code==200){
									  var aityList = data.data;
									  
									  for(var i=0;i<aityList.length;i++){
									 	 var adata = aityList[i];
									 	 $("#area").append("<option value='"+adata.id+"' >"+adata.name+"</option>");
									  }
							      }else{
									layer.msg(data.msg)
								  }
							  })
					      }else{
							layer.msg(data.msg)
						  }
					  })
				 }
			  });
		  
		  
		  $('#city').change(function(){ 
				 var pid =$(this).children('option:selected').val();
				 
				 if(pid!='0'&&pid!=''&&pid!=null){
					 $('#area').empty();
					 $.get("/action/my/s/getRegionByPid",{"parentCode":pid},function(data){
						  if(data.code==200){
							  var aityList = data.data;
							  
							  for(var i=0;i<aityList.length;i++){
							 	 var adata = aityList[i];
							 	 $("#area").append("<option value='"+adata.id+"' >"+adata.name+"</option>");
							  }
					      }else{
							layer.msg(data.msg)
						  }
					  })
				 }
			  });
		  
	  })

		  
	
			  
		 
	</script>

</body>
</html>
