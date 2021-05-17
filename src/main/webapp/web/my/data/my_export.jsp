<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>我的中心</title>
    <%@ include file="../../static/top.jsp" %>

<style type="text/css">
   .simple {
       width: 5px;
       text-align: center;
   }
   .tablespan {
       overflow: hidden;
       word-break:keep-all;/* 不换行 */
       white-space:nowrap;/* 不换行 */
       text-overflow:ellipsis;
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
					<li><a href="#">数据处理</a></li>
					<li class="active">灌入记录</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">数据灌入记录</div>
                        <div class="panel-body">
                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">数据灌入记录</div>
                                    <div class="panel-body" style="overflow-y: auto; ">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">
                                                <td >顺序</td>
                                                <td >数据库类型</td>
                                                <td >地址</td>
                                                <td >数据库</td>
                                                <td>表名</td>
                                                <td>灌入时间</td>
                                                <td>状态</td>
                                              
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
    $(document).ready(function () {
    	queryList(1, true);
    });
var EXP_STATUS = {
		
			1 : {
				name : "灌入中",
				value : '1'
			},
			2 : {
				name : "灌入完成",
				value : '2'
			},
			3 : {
				name : "灌入失败",
				value : '3'
			},
			
			
			"length" : 3
		};
    function queryList(page, pageinit) {
		$("#tbody").empty();
		
		 $.get("/action/my/myexp/getExportPage",{"pageNum" : page,"pageSize" : pageSize},function(data){
			 var contain, content;
				var _totalPage = data.maxPage;
				if (data.data.length) {
					for (var i = 0; i < data.data.length; i++) {
						contain = data.data[i];
						
						content += "<tr><td>"+ contain.id + "</td>" ;
						content += "<td>"+ contain.table.ds.type + "</td>" ;
						content += "<td>"+ contain.table.ds.host + "</td>" ;
						content += "<td>"+ contain.table.ds.db + "</td>" ;
						content += "<td>"+ contain.table.name + "</td>" ;
						content += "<td>"+ EXP_STATUS[contain.status].name+ "</td>" ;
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
