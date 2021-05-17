<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>我的中心</title>
<%@ include file="../../static/top.jsp"%>

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
					<li><a href="#">我的采集</a></li>
					<li class="active">我的任务</li>
				</ul>
			</div>
			<div class="row">
			  <div class="col-md-12">
				<div class="panel panel-default">
	                <div class="panel-heading">我的采集任务</div>
	                <div class="panel-body">
	                   <div class="col-md-4">
			                  <div class="list-group" id="collist">
			                      <a href="#" class="list-group-item active">
			                          <h4 class="list-group-item-heading">任务列表</h4>
			                      </a>
			
			                  </div>
			            </div>
			            <div class="col-md-8">
			            	<table class="table table-bordered table-striped table-condensed">
		                       <thead>
		                       <tr>
		                           <th>任务ID</th>
		                           <th>采集地址</th>
		                           <th >任务状态</th>
		                           <th >创建时间</th>
		                           <th >操作</th>
		                          
		                       </tr>
		                       </thead>
		                       <tbody id="tbody">
		                      
		                       
		                       </tbody>
		                   </table>
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


	</div>
	<!-- main content end--> </section>
	
	<script type="text/javascript">
	
	var JOB_STATUS = {
			
			0 : {
				name : "待采集",
				value : '0'
			},
			1 : {
				name : "采集中",
				value : '1'
			},
			2 : {
				name : "采集完成",
				value : '2'
			},
			3 : {
				name : "采集失败",
				value : '3'
			},
			4 : {
				name : "解析中",
				value : '4'
			},
			5 : {
				name : "解析完成",
				value : '5'
			},
			6 : {
				name : "解析失败",
				value : '6'
			},
			
			"length" : 7
		};
	
	
		var tplList = ${tmpList};
		$(document).ready(function() {
			//  var it = " <a href='javascript:void(0)' class='list-group-item'><p class='list-group-item-text'>" + title + "</p> </a>";
			var li = "";
			for (var i = 0; i < tplList.length; i++) {
				li = li+ " <a href='javascript:void(0)' onclick='showTable("+tplList[i].id+")' class='list-group-item'><p class='list-group-item-text'>" + tplList[i].template_name + "</p> </a>";
			}
			
			$("#collist").append(li);
			if(tplList.length>0){
				queryList(tplList[0].pageList);
			}

		});
		
		function showTable(id){
			for (var i = 0; i < tplList.length; i++) {
			
				if(tplList[i].id==id){
					queryList(tplList[i].pageList);
				}
			}
		}
		
		
		function queryList(data){
			$("#tbody").empty();
		
			if(data==null||data==''||data==undefined){
				return;
			}
			var  content;
			var _totalPage = data.length;
			if(data.length){
				for(var i=0; i<data.length; i++){
					var contain = data[i];
					$("#tbody").append("<tr><td>"+(i+1)+"</td><td>"+contain.page_url+"</td><td>"+JOB_STATUS[contain.status].name+"</td><td>"+contain.create_time+"</td><td><a href='/action/my/m/toMyData?&requestid="+contain.request_id+"&page="+contain.page+"&mid=a2&aid=ch21'>查看数据</a>|<a onclick=\"repDown('"+contain.request_id+"',"+contain.page+")\" href='javascript:void(0)'>重新采集</a></td></tr>");
				}
				
			}
			
				$('#Pagination').empty();
				$.jqPaginator('#Pagination',{
					totalPages : _totalPage,
					visiblePages : 10,
					currentPage : 1,
					prev : '<li class="prev"><a href="javascript:scroll(0,0)">上页</a></li>',
					next : '<li class="next"><a href="javascript:scroll(0,0)">下页</a></li>',
					first : '<li class="first"><a href="javascript:scroll(0,0)">首页</a></li>',
					last : '<li class="last"><a href="javascript:scroll(0,0)">尾页</a></li>',
					page : '<li class="page"><a href="javascript:scroll(0,0)">{{page}}</a></li>',
					onPageChange : function(num, type) {
						
					}
				});
			
			
		}
		function repDown(requestid,page){
			$.blockUI({ message: '<h1>请稍等...</h1> ', css: { width: '200px', height: '100px' } });
			 $.post("/action/my/m/repMyDagePage", {"requestid": requestid, "page": page}, function (data) {
				 $.unblockUI();
		          if(data.code==200){
		        	  layer.msg("操作成功");
		          }else{
		        	  layer.msg(data.msg);
		          }
		        })
		}
	</script>

</body>
</html>
