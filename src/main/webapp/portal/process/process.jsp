<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<head>
<meta charset="UTF-8" />
<title>智数-智慧数字</title>
<meta name="Keywords"
	content="智数,智数平台,采集,爬虫,抓取,数据采集,数据下载,数据抓取,网页爬虫,网页抓取,整站抓取,整站下载,智能抓取,智能爬虫" />
<meta name="Description"
	content="智数,智数平台,爬虫,抓取,采集,数据采集,数据下载,数据抓取,新一代智能云采集平台,智能大数据采集平台,新一代云采集平台." />

<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description"
	content="A Collection of Page Transitions with CSS Animations" />
<meta name="keywords"
	content="page transition, css animation, website, effect, css3, jquery" />
<meta name="author" content="Codrops" />

<link rel="stylesheet" type="text/css"
	href="/assets/plugins/trans/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="/assets/plugins/trans/css/multilevelmenu.css" />
<link rel="stylesheet" type="text/css"
	href="/assets/plugins/trans/css/component.css" />
<link rel="stylesheet" type="text/css"
	href="/assets/plugins/trans/css/animations.css" />
<link rel="stylesheet" type="text/css"
	href="/assets/plugins/trans/css/init.css" />

<script src="/assets/js/jquery-1.8.3.min.js"></script>

<script src="/assets/plugins/trans/js/init.js"></script>

<script src="/assets/plugins/trans/js/modernizr.custom.js"></script>
<script type="text/javascript">
	var uuid = '${uuid}';
	var requestId = '${requestId}';
	$(document).ready(function() {
		setInterval(queryTaskStatus, 5000);
	});

	function queryTaskStatus() {
		$.post("/action/portal/synHtml", {
			"uuid" : uuid,
			"requestId" : requestId
		}, function(data) {
			if (data.code == 200) {
				if (data.data == 2) {
					window.location.href = "/action/portal/rid/" + requestId
				} else if (data.data == 3) {
					window.location.href = "/action/portal/error/" + requestId
				}

			}
		})
	}
</script>


</head>

<body>


	<div id="pt-main" class="pt-perspective">
		<div class="pt-page pt-page-1">
			<h2>点击鼠标左键下载列表，单击右键下载单个页面元素</h2>
		</div>
		<div class="pt-page pt-page-2">
			<h2>支持多种数据格式导出到本地</h2>
		</div>
		<div class="pt-page pt-page-3">
			<h2>多任务批量下载，自定义任务频率，海量数据瞬间下载到本地</h2>
		</div>

	</div>



	<script src="/assets/plugins/trans/js/jquery.dlmenu.js"></script>
	<script src="/assets/plugins/trans/js/pagetransitions.js"></script>

</body>
</html>

