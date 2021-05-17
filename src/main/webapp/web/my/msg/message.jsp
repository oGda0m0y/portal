<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>我的消息</title>
<%@ include file="../../static/top.jsp"%>
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
		file="../../static/menu.jsp"%> <!-- left side end-->

	<!-- main content start-->
	<div class="main-content">

		<!-- header section start-->


		<%@ include file="../../static/header.jsp"%>
		<div class="wrapper">

		<!-- header section end-->

		<!-- page heading start-->
		<div class="page-heading">
			<ul class="breadcrumb">
                <li>
                    <a href="#">后台管理</a>
                </li>
                <li > 我的中心 </li>
                <li class="active"> 我的消息 </li>
            </ul>
		</div>
		
		<!--body wrapper end-->
		<div>
	

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
            	 <div class="panel purple">
            	 	<div class="panel-heading">
                            <h3 class="panel-title">最新消息 </h3>
                           
                        </div>
                      <div class="panel-body">
                          <div class="row">
                              <div class="col-md-12 col-sm-12 col-xs-12">
                              	 <div id="tbody" class="feed-activity-list">

                                
                                  

                              	</div>
                              </div>
                              
                          </div>
                           <div class="paging">
									<ul class="pagination" id="Pagination">

									</ul>
								</div>
                      </div>
                    </div>
            	</div>
			</div >

            
            
			
		</div>
		<!--footer section start-->
		<%@ include file="../../static/footer.jsp"%>
	
		<!--footer section end-->
	</div>

	</div>
	<!-- main content end--> </section>
	<script>
	var pageSize =20, pageinit = true;	
	$(document).ready(function () {
		queryList(1,true);
	
		
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
					if (pageinit) {
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
								if ((this.currentPage != 1)|| (1 != num)) {
									queryList(num,false);
								}
							}
						});
					}
			  })
			
		}
	</script>
	
</body>
</html>
