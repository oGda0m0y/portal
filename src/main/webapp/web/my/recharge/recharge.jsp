<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>我要充值</title>
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
					<li class="active">我要充值</li>
				</ul>
			</div>

			<!--body wrapper end-->
			<div>
			
			
			<section id="two" class="main style1 special">
		<div class="container" style="width: 100%">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<!--price start-->
				<div class="text-center">
					<h1 class="color-terques">请选择套餐</h1>
				</div>
				<div class="col-lg-4 col-sm-4">
					<div name="priceDiv" class="pricing-table">
						<div class="pricing-head">
							<h1>基础版</h1>

						</div>
						<div class="pricing-quote">
							￥58<span class="note">/月</span>
							<p>10000次网页采集</p>
						</div>

						<ul class="list-unstyled"
							style="font-size: 18px; font-family: arial, sans-seri;">
							<li>7*24 技术支持</li>
							<li>10000次网页采集</li>
							<li>翻页无限制</li>
							<li>支持数据导出</li>
							<li>单页成本0.0058元</li>
						</ul>
						<div class="price-actions">
							<a href="javascript:;" class="btn" t="0">立即下单</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-sm-4">
					<div name="priceDiv" class="pricing-table">
						<div class="pricing-head">
							<h1>专业版</h1>

						</div>
						<div class="pricing-quote">
							￥158<span class="note">/月</span>
							<p>50000次网页采集</p>
						</div>
						<ul class="list-unstyled"
							style="font-size: 18px; font-family: arial, sans-seri;">
							<li>7*24 技术支持</li>
							<li>50000次网页采集</li>
							<li>支持数据导出</li>
							<li>解锁导入数据库</li>
							<li>单页成本0.00316元</li>
						</ul>
						<div class="price-actions">
							<a href="javascript:;" class="btn" t="1">立即下单</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-sm-4">
					<div name="priceDiv" class="pricing-table">
						<div class="pricing-head">
							<h1>VIP版</h1>

						</div>
						<div class="pricing-quote">
							VIP<span class="note"></span>
							<p>定制化套餐</p>
						</div>
						<ul class="list-unstyled"
							style="font-size: 18px; font-family: arial, sans-seri;">
							<li>7*24 技术支持</li>
							<li>满足您的采集需求</li>
							<li>支持手机app采集</li>
							<li>支持限ip采集</li>
							<li>定制个性化数据报告</li>
						</ul>
						<div class="price-actions">
							<a href="javascript:;" class="btn" t="2">立即下单</a>
						</div>
					</div>
				</div>


				<!--price end-->
			</div>
			<div class="col-sm-1"></div>
		</div>
	</section>
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
				var t = $(this).attr("t");
				document.location.href="/action/my/s/createOrder?t="+t;
				
			});
			
		});
	</script>

</body>
</html>
