<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>订单详情</title>
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
				<form action="/action/topay">
					
					  <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">订单号:</label>
                           <div class="form-group col-sm-10">
                          	 ${order.order_no}
							 <input type="hidden" class="form-control" id="WIDout_trade_no" name="WIDout_trade_no" value="${order.order_no}">
                           </div>
                       </div>
					<div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">下单时间:</label>
                           <div class="form-group col-sm-10">
                           	${order.create_time}
							 <input type="hidden" class="form-control" id="create_time" name="create_time" value="${order.create_time}">
                           </div>
                       </div>
                       <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">订单金额:</label>
                           <div class="form-group col-sm-10">
                           	${order.amount}
							 <input type="hidden" class="form-control" id="WIDtotal_amount" name="WIDtotal_amount" value="${order.amount}">
                           </div>
                       </div>
                       <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">商品名:</label>
                           <div class="form-group col-sm-10">
                           	${order.subject}
							 <input type="hidden" class="form-control" id="WIDsubject" name="WIDsubject" value="${order.subject}">
                           </div>
                       </div>
                       <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">商品描述:</label>
                           <div class="form-group col-sm-10">
                           		${order.body}
							 <input type="hidden" class="form-control" id="WIDbody" name="WIDbody" value="${order.body}">
                           </div>
                       </div>
                       
                       <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">支付状态:</label>
                           <div class="form-group col-sm-10">
                           		<!-- 0:草稿\r\n1:待支付\r\n2.支付成功\r\n3.支付失败\r\n4.关闭 -->
                           		${order.order_status eq 0 ? "草稿" : (order.order_status eq 1 ? "待支付" : (order.order_status eq 2 ? "支付成功" : (order.order_status eq 3 ? "支付失败" : (order.order_status eq 4 ? "关闭" : "未知"))))}
							 <input type="hidden" class="form-control" id="WIDbody" name="WIDbody" value="${order.order_status}">
                           </div>
                       </div>
                        <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">交易流水号:</label>
                           <div class="form-group col-sm-10">
							 ${order.trade_no}
                           </div>
                       </div>
                        <div class="form-group col-sm-12">
                           <label class="col-sm-2"
                                  style="font-size: 18px; padding-top: 5px;">支付状态:</label>
                           <div class="form-group col-sm-10">
                           	 ${order.trade_status}
                           </div>
                       </div>
						<c:if test="${order.order_status eq 1}">
							<div class="form-group col-sm-12">
								<input type="submit" value="支付"/>
							</div>
						</c:if>
				</form>
			</div>
			<!--footer section start-->
			<%@ include file="/web/static/footer.jsp"%>

			<!--footer section end-->
		</div>

	</div>
	<!-- main content end--> </section>
	<script>
		$(document).ready(function() {
			
			
		});
	</script>

</body>
</html>
