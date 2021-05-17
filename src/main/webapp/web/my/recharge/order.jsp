<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>我的订单</title>
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
					<li class="active">我的订单</li>
				</ul>
			</div>

			<!--body wrapper end-->
			<div>
			
			
			
			</div>
			<!--footer section start-->
			<%@ include file="/web/static/footer.jsp"%>

			<!--footer section end-->
		</div>

	</div>
	<!-- main content end--> </section>
	<script>
		$(document).ready(function() {
			$("div[name='priceDiv']").hover(function() {
				$(this).attr("class", "pricing-table most-popular");
			}, function() {
				$(this).attr("class", "pricing-table");
			});
			
			$(".price-actions>a").click(function(){
				alert($(this).attr("t"));
			});
			
		});
	</script>

</body>
</html>
