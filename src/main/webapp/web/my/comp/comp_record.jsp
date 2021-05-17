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
					<li class="active">消费明细</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">消费明细</div>
                        <div class="panel-body">

                            

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">消费明细列表</div>
                                    <div class="panel-body" style="overflow-x:scroll">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">

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

    

    $(document).ready(function () {

    })


</script>

</body>
</html>
