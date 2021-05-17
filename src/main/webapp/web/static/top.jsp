<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<link rel="shortcut icon" href="#" type="image/png">

<link href="/web/res/css/style.css" rel="stylesheet">

<link href="/web/res/css/style-responsive.css" rel="stylesheet">
<link href="/web/res/js/iCheck/skins/square/square.css" rel="stylesheet">

<script src="/web/res/js/jquery-1.10.2.min.js"></script>
<script src="/web/res/js/jquery.blockUI.js"></script>

<script src="/web/res/js/layer-v3.0.3/layer/layer.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="/web/res/js/html5shiv.js"></script>
  <script src="/web/res/js/respond.min.js"></script>
  <![endif]-->
<script>
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}

	function getFormatDate(date, pattern) {
		if (date == undefined) {
			date = new Date();
		}
		if (pattern == undefined) {
			pattern = "yyyy-MM-dd hh:mm:ss";
		}
		return date.format(pattern);
	}

	function formatTime(t) {
		if (t == 'null' || t == null || t == undefined) {
			return '';
		}
		if (t == 0) {
			return '';
		}
		var pattern = "yyyy-MM-dd hh:mm:ss";
		return getFormatDate(new Date(t), pattern);
	}

	function formatDate(t) {
		if (t == 'null' || t == null || t == undefined || t == '') {
			return '';
		}
		return getFormatDate(new Date(t), "yyyy-MM-dd");
	}

	function format(t, pattern) {
		if (t == 'null' || t == null || t == undefined || pattern == 'null'
				|| pattern == null || pattern == undefined) {
			return '';
		}
		if (t == 0) {
			return '';
		}
		return getFormatDate(new Date(t), pattern);
	}

	var BALANCE_TYPE = {

		0 : {
			name : "充值",
			value : '0'
		},
		1 : {
			name : "消费",
			value : '1'
		},

		"length" : 2
	};
</script>