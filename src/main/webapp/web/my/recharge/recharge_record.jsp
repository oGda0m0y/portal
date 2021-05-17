<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>充值记录</title>
<%@ include file="/web/static/top.jsp"%>
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

	<section> <!-- left side start--> 
		<%@ include file="/web/static/menu.jsp"%> <!-- left side end-->

	<!-- main content start-->
	<div class="main-content">

		<!-- header section start-->
		<%@ include file="/web/static/header.jsp"%>
		<div class="wrapper">

			<!-- header section end-->

			<!-- page heading start-->
			<div class="page-heading">
				<ul class="breadcrumb">
					<li><a href="#">个人中心</a></li>
					<li class="active">充值记录</li>
				</ul>
			</div>
			
			<div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">我的充值记录</div>
                        <div class="panel-body">

                            <div class="col-md-12">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                                    <label id="start_text" for="label"
                                                           class="col-sm-2 control-label">开始时间</label>

                                                    <div class="col-sm-4 ">
                                                        <input type="text" class="form-control " id="begin_date"
                                                               onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'twoer'})"
                                                               value="">
                                                    </div>

                                                    <label for="label" id="end_date"
                                                           class="col-sm-2 control-label">结束时间</label>

                                                    <div class="col-sm-4 ">
                                                        <input type="text" class="form-control" id="end_date"
                                                               onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'twoer'})"
                                                               value="">
                                                    </div>
                                                </div>
                                        <div class="col-sm-1 ">
                                            <button id="searchBtn" type="button" class="btn btn-success">查询</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">充值记录列表</div>
                                    <div class="panel-body">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">
												<th>类型</th>
												<th>智数豆</th>
												<th>订单号</th>
												<th>备注</th>
												<th>时间</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tbody">


                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                                <div class="paging">
                                    <ul class="pagination" id="Pagination">
                                    </ul>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>

            </div>

			<!--body wrapper end-->
			<div></div>
			<!--footer section start-->
			<%@ include file="/web/static/footer.jsp"%>

			<!--footer section end-->
		</div>

	</div>
	<!-- main content end--> </section>
	<script>
		var pageinit=true;
		var _totalPage=1;
		$(document).ready(function() {
			$("#searchBtn").click(function(){
				var _totalPage=1;
				queryList(1,true);
			});
			queryList(1,true);
		});
		
		
		function queryList(page, pageinit){
			var begin = $("#begin_date").val();
			var end = $("#end_date").val();
			$.ajax({
			    url:'/action/my/s/queryBalanceBill',
			    type:'POST', // GET
			    async:true,    // 或false,是否异步
			    data:{
			    	'begin':begin,
			    	'end':end,
			    	'type':0,
			    	'p':page
			    },
			    timeout:50000,    // 超时时间
			    dataType:'json',    // 返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data){
			        if(data.code == 200){
			        	var d = data.data;
			        	
			        	$("#tbody").empty();
			        	var html='';
			        	_totalPage = d.maxPage;
			        	for(var i=0; i<d.length; i++){
							var contain = d[i];
							html=html+'<tr>'+
								'<td>'+BALANCE_TYPE[contain.type].name+"</td>"+
								'<td>'+(contain.type == 0 ? "+" : "-")+contain.amount+"</td>"+
								'<td>'+contain.order_no+"</td>"+
								'<td>'+contain.remark+"</td>"+
								'<td>'+formatTime(contain.create_time)+"</td>"+
								'</tr>';
						}
			        	$("#tbody").html(html);
			        	
			        	
			        	
			        }else{
			        	/* 关闭遮罩 */
			        	alert(data.msg);
			        }
			    },
			    error:function(xhr,textStatus){
			    	/* 关闭遮罩 */
			    	alert("网络出现问题，请稍后再试!!!");
			    }
			});
			if (pageinit) {
				$('#Pagination').empty();
				$.jqPaginator('#Pagination',{
					totalPages : _totalPage,
					visiblePages : 5,
					currentPage : page,
					prev : '<li class="prev"><a href="javascript:scroll(0,0)">上页</a></li>',
					next : '<li class="next"><a href="javascript:scroll(0,0)">下页</a></li>',
					first : '<li class="first"><a href="javascript:scroll(0,0)">首页</a></li>',
					last : '<li class="last"><a href="javascript:scroll(0,0)">尾页</a></li>',
					page : '<li class="page"><a href="javascript:scroll(0,0)">{{page}}</a></li>',
					onPageChange : function(page, type) {
						if ((this.currentPage != 1)|| (1 != page)) {
							queryList(page,false);
						}
					}
				});
			}
			
		}
		
	</script>

</body>
</html>
