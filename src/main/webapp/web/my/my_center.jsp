<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>我的中心</title>
<%@ include file="../static/top.jsp"%>
<style type="text/css">
	.form-list {
	list-style-type: none;
	padding-top: 0px;
	padding-left: 20px;
	margin: 0px;
	float: left;
	width: 420px;
	height: 400px;
	}
</style>
</head>

<body class="sticky-header">

	<section> <!-- left side start--> <%@ include
		file="../static/menu.jsp"%> <!-- left side end-->

	<!-- main content start-->
	<div class="main-content">

		<!-- header section start-->


		<%@ include file="../static/header.jsp"%>
		<div class="wrapper">

		<!-- header section end-->

		<!-- page heading start-->
		<div class="page-heading">
			<ul class="breadcrumb">
                <li>
                    <a href="#">后台管理</a>
                </li>
                <li class="active"> 我的中心 </li>
            </ul>
		</div>
		
		<!--body wrapper end-->
		<div>
			<div class="row">
			<div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel">
                                <div class="panel-body">
                                    <div class="profile-pic text-center">
                                        <img alt="" src="${sessionScope._sessionkey.img}">
                                    </div>
                                    <ul class="p-info">
                                        <li>
                                            <div class="title">用户名</div>
                                            <div class="desk">${sessionScope._sessionkey.user_name}</div>
                                        </li>
                                        <li>
                                            <div class="title">手机</div>
                                            <div class="desk">${sessionScope._sessionkey.mobile}</div>
                                        </li>
                                        <li>
                                            <div class="title">邮箱</div>
                                            <div class="desk">${sessionScope._sessionkey.email}</div>
                                        </li>
                                         <li>
                                            <div class="title">采集券</div>
                                            <div class="desk">${user.account.totalAmount}</div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
            	
            	<div class="col-md-12 col-sm-12 col-xs-12">
            	
            	 <div class="panel purple">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                	 <div id="jobStatus" style="height:300px;"></div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
            	
            	</div>
            	
            	
            </div>
                </div>
                <div class="col-md-8">
                    <!--statistics start-->
                    <div class="row state-overview">
                    	<div class="col-md-4 col-xs-4 col-sm-3">
                            <div class="panel blue">
                                <div class="symbol">
                                    <i class="fa fa-money"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">${user.account.totalAmount}</div>
                                    <div class="title"> 采集券余额</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-md-4 col-xs-4 col-sm-3">
                            <div class="panel red">
                                <div class="symbol">
                                    <i class="fa fa-tags"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">${user.account.totalSale}</div>
                                    <div class="title">消费总额</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-4 col-sm-3">
                            <div class="panel purple">
                                <div class="symbol">
                                    <i class="fa fa-gavel"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">${user.account.lastSale}</div>
                                    <div class="title">昨日消费</div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
            	 <div class="panel purple">
            	 	<div class="panel-heading">
                            <h3 class="panel-title">最新消息 <small class="pull-right text-navy"><a href="/action/my/m/messageList">更多...</a></small></h3>
                           
                        </div>
                      <div class="panel-body">
                          <div class="row">
                              <div class="col-md-12 col-sm-12 col-xs-12">
                              	 <div id="tbody" class="feed-activity-list">

                                
                                  

                              	</div>
                              </div>
                              
                          </div>
                           
                      </div>
                    </div>
            	</div>
                    
                    </div >
                   
                    <!--statistics end-->
                </div>
                
            </div>
            
            
			
		</div>
		<!--footer section start-->
		<%@ include file="../static/footer.jsp"%>
		
		<script src="/web/res/js/echarts.min.js"></script>
		<!--footer section end-->
	</div>

	</div>
	<!-- main content end--> </section>
	<script>
	var pageSize = 5, pageinit = true;	
	$(document).ready(function () {
		queryList(1,true);
		var myChart = echarts.init(document.getElementById('jobStatus'));
		var option = {
			    title : {
			        text: '采集任务状态',
			     
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['未采集','采集中','采集完成','采集失败','解析中','解析完成','解析失败']
			    },
			    series : [
			        {
			            name: '采集状态',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '50%'],
			            data:${ts},
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};

		 myChart.setOption(option);
		
	});

	
	 function queryList(page, pageinit) {
			$("#tbody").empty();
			
			 $.get("/action/my/m/getMessagePage",{"pageNum" : page,"pageSize" : pageSize},function(data){
				 var contain;
				 var content = "";
					var _totalPage = data.maxPage;
					if (data.data.length) {
						for (var i = 0; i < data.data.length; i++) {
							contain = data.data[i];
							var read = "未读";
							if(contain.isread==1){
								read = "已读";
							}
							content += "<div class='feed-element'><div><small class='pull-right text-navy'>"+contain.create_time+"&nbsp;&nbsp;&nbsp;<font color='red'>"+read+"</font></small>" ;
							content += " <strong>"+contain.type+"</strong>" ;
							content += " <div>"+contain.msg+"</div>" ;
							content += " </div></div>" ;   
						}
						$("#tbody").append(content);
					}
				
			  })
			
		}
	</script>
	
</body>
</html>
