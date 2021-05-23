<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>模板配置</title>
    <%@ include file="../../static/top.jsp" %>

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
					<li><a href="#">我的采集</a></li>
					<li class="active">模板配置</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">模板配置</div>
                        <div class="panel-body">
                            <div class="col-md-12">
                                <table class="table table-bordered table-striped table-condensed">
                                    <thead>
                                    <tr>
                                       
                                        <th>模板名称</th>
                                        <th>采集地址</th>
                                        <th>创建时间</th>
                                        <th>操作</th>

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
        <%@ include file="../../static/footer.jsp" %>
        <!--footer section end-->


    </div>
    <!-- main content end--> </section>

<script type="text/javascript">
    var tplList = ${detailList};
    $(document).ready(function () {
        if (tplList.length > 0) {
            queryList(tplList);
        }

    });

    function queryList(data) {
        $("#tbody").empty();

        if (data == null || data == '' || data == undefined) {
            return;
        }
        var content;
        var _totalPage = data.length;
        if (data.length) {
            for (var i = 0; i < data.length; i++) {
                var contain = data[i];
             
                $("#tbody").append("<tr><td>" + contain.template_name + "</td><td>" + contain.url + "</td><td>" + contain.create_time+ "</td><td><a href='/action/portal/rid/" + contain.request_id + "'>查看</a>|<a href='/action/portal/rid/" + contain.request_id + "'>关联链接</a>|<a href='/action/portal/rid/" + contain.request_id + "'>下载</a></td></tr>");
              
              
                
            }

        }

        $('#Pagination').empty();
        $.jqPaginator('#Pagination', {
            totalPages: _totalPage,
            visiblePages: 10,
            currentPage: 1,
            prev: '<li class="prev"><a href="javascript:scroll(0,0)">上页</a></li>',
            next: '<li class="next"><a href="javascript:scroll(0,0)">下页</a></li>',
            first: '<li class="first"><a href="javascript:scroll(0,0)">首页</a></li>',
            last: '<li class="last"><a href="javascript:scroll(0,0)">尾页</a></li>',
            page: '<li class="page"><a href="javascript:scroll(0,0)">{{page}}</a></li>',
            onPageChange: function (num, type) {

            }
        });


    }

</script>

</body>
</html>
