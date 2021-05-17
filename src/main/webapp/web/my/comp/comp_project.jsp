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
					<li class="active">项目列表</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">项目列表</div>
                        <div class="panel-body">

                            

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">我的项目列表</div>
                                    <div class="panel-body" >
                                    <div class="col-md-12">

			                                <form class="form-horizontal" role="form">
			                                    <div class="form-group col-sm-10">
			                                        <button id="addProjectBtn" type="button"
			                                                class="  btn btn-info">添加项目
			                                        </button>
			
			                                    </div>
			
			                                </form>
			
			                            </div>
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">
 											
                                                <td >项目名称</td>
                                                <td >项目编码</td>
                                                <td >项目相关网址</td>
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

    </section>
    
    <div tabindex="-1" role="dialog" id="createProject"
             class="modal fade bs-example-modal-lg" style="display: none;">
            <div role="document" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close"
                                class="close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title">新建项目</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-3">项目名称</label>
                                <div class="col-sm-9">
                                    <input id="projectName" class="form-control" width="100%" type="text" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">相关网址地址</label>
                                <div class="col-sm-9" id="newTableDiv">
                                    <input id="website" class="form-control" width="100%" type="text">
                                </div>
                            </div>
                            
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" id="saveBtn">保存 </button>
                        <button type="button" class="btn btn-success" id="closeBtn" >关闭</button>
                    </div>
                </div>
            </div>
        </div>

<script type="text/javascript">
var pageSize = 20, pageinit = true;	

$(document).ready(function () {
	queryList(1, true);
	
	 $("#addProjectBtn").click(function () {
		 $("#projectName").val("");
	     $("#website").val("");
	     $("#createProject").modal("show");
	 })
	 $("#saveBtn").click(function () {
		var pname = $("#projectName").val();
		var website = $("#website").val();
		if(pname==''||pname==undefined){
			layer.msg("请输入项目名称");
			return;
		}
		if(website==''||website==undefined){
			layer.msg("请输入网址");
			return;
		}
		
		$.post("/action/my/c/saveCompProject",{"pname" : pname,"website" : website},function(data){
			if(data.code==200){
				layer.msg("操作成功");
				$("#createProject").modal("hide");
				queryList(1, true);
			}else{
				layer.msg(data.msg)
			}
		})
       
     })
	 $("#closeBtn").click(function () {
		$("#projectName").val("");
		$("#website").val("");
        $("#createProject").modal("hide");
     })
});
    
function queryList(page, pageinit) {
	$("#tbody").empty();
	
	 $.get("/action/my/c/getCompProject",{"pageNum" : page,"pageSize" : pageSize},function(data){
		 var contain, content;
			var _totalPage = data.maxPage;
			if (data.data.length) {
				for (var i = 0; i < data.data.length; i++) {
					contain = data.data[i];
					
					content += "<tr><td>"+ contain.project_code + "</td>" ;
					content += "<td>"+ contain.project_name + "</td>" ;
					content += "<td>"+ contain.website + "</td>" ;
				
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
