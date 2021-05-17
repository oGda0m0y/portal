<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>我的中心</title>
    <%@ include file="../../static/top.jsp" %>

    <style>
        
        th{
        	min-width: 80px;
        }
    </style>
</head>
<body class="sticky-header">

<section> <!-- left side start-->
    <%@ include
            file="../../static/menu.jsp" %> <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">

        <!-- header section start-->


        <%@ include file="../../static/header.jsp" %>

        <div class="wrapper">
			<div class="page-heading">
				<ul class="breadcrumb">
					<li><a href="#">VIP服务</a></li>
					<li class="active">我的工单</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">我的工单</div>
                        <div class="panel-body">

                            

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">我的工单列表</div>
                                    <div class="panel-body" style="overflow-x:scroll">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">
												<td >项目名称</td>
                                                <td >工单号</td>
                                                <td>工单标题</td>
                                                <td>需求类型</td>
                                                <td>相关工单号</td>
                                                <td>需求内容</td>
                                                <td>附件</td>
                                                <td>工单状态</td>
                                                <td>创建时间</td>
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

        </div>
        <!--body wrapper end-->


        <!--footer section start-->
        <%@ include file="../../static/footer.jsp" %>

        <!--footer section end-->

        
        
    </div>

    <!-- main content end--> </section>

<script type="text/javascript">

var pageSize = 20, pageinit = true;	
var WORK_STATUS = {
		0 : {
			name : "待审核",
			value : '0'
		},
		1 : {
			name : "处理中",
			value : '1'
		},
		2 : {
			name : "处理完成",
			value : '2'
		},
		3 : {
			name : "关闭",
			value : '3'
		},
		
		
		"length" : 4
	};
$(document).ready(function () {
	queryList(1, true);
	
	 
});
    
function queryList(page, pageinit) {
	$("#tbody").empty();
	
	 $.get("/action/my/c/getCompWorkOrder",{"pageNum" : page,"pageSize" : pageSize},function(data){
		 var contain, content;
			var _totalPage = data.maxPage;
			if (data.data.length) {
				for (var i = 0; i < data.data.length; i++) {
					contain = data.data[i];
					
					
					content += "<tr><td>"+ contain.project_name + "</td>" ;
					content += "<td>"+ contain.work_no + "</td>" ;
					
					content += "<td>"+ contain.title + "</td>" ;
					content += "<td>"+ contain.type + "</td>" ;
					content += "<td>"+ contain.bind_no + "</td>" ;
					content += "<td>"+ contain.content + "</td>" ;
					content += "<td>"+ contain.attch_name + "</td>" ;
					content += "<td>"+ WORK_STATUS[contain.status].name + "</td>" ;
					content += "<td>"+ contain.create_time+ "</td>" ;
					content += "</tr>" ;

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
