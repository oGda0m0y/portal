<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="Keywords"
	content="智数,智数平台,采集,爬虫,抓取,数据采集,数据下载,数据抓取,网页爬虫,网页抓取,整站抓取,整站下载,智能抓取,智能爬虫" />
<meta name="Description"
	content="智数,智数平台,爬虫,抓取,采集,数据采集,数据下载,数据抓取,新一代智能云采集平台,智能大数据采集平台,新一代云采集平台." />
    <link rel="shortcut icon" href="#" type="image/png">

    <title>智数-智慧数字</title>

    <link href="/web/res/css/style.css" rel="stylesheet">
    <link href="/web/res/css/style-responsive.css" rel="stylesheet">
    <style>
        .__thinkdata_highlight {
            outline: #61DA67 solid 2px;
            box-shadow: 0 0 0 4px #61DA67;
        }

        .__thinkdata_hover {
            outline: #4f7da0 solid 2px;
            box-shadow: 0 0 0 4px #4f7da0;
            background: rgba(95, 162, 214, 0.67);
        }
        th{
        	min-width: 150px;
        }
    </style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/web/res/js/html5shiv.js"></script>
    <script src="/web/res/js/respond.min.js"></script>

    <![endif]-->
</head>

<body class="horizontal-menu-page">

<section>

    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
            <!-- 
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                
              -->   
                <a class="navbar-brand" href="index.html"> <img width=150px height=50px  src="/web/res/images/logo-white.png" alt="">
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse"
                 id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/action/toIndex">返回</a></li>


                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li id="loginLi" class="dropdown">
                        <a data-toggle="modal" href="/portal/static/login.html" data-target="#myLogin"
                           class="button loginBtn special">登录/注册</a>
                    </li>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>


    <!--body wrapper start-->
    <div class="wrapper">
        <div class="row">
            <div id="myPage" class="col-md-12">
                <input type="hidden" id="reqid" value="${requestId}">
                <input type="hidden" id="dname" value="${domainName}">
                <iframe frameborder="no" border="0" id="myiframe"
                        style="width: 100%;" src="${remote_url}" scrolling="yes">
                </iframe>

            </div>
        </div>

        <div class="row" style="margin-top: 10px;">
            <div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading">
                        &nbsp;&nbsp;&nbsp;
                        <span class="tools pull-left"> 
                        	<button class="btn btn-primary" id="saveTpl" type="button">保存</button>
                        	&nbsp;&nbsp;&nbsp;
                        	<button class="btn btn-primary" id="submitTpl" type="button">提交</button>
						</span>
                        &nbsp;&nbsp;&nbsp;
                    </header>
                    <div class="panel-body" style="overflow-x:scroll">
                    
                     <div class="col-md-12">
                     
                      <table id="t-data" class="table table-bordered  table-hover general-table">
                           <thead>
                           <tr id="head">
                              
                              
                           </tr>
                           </thead>
                           <tbody id="cols">


                           </tbody>
                       </table>
                     
                     
                     </div>
                    
                 

                    </div>
                </section>
            </div>
        </div>
    </div>


</section>

<div class="modal fade" id="editColModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="">新的列名</h4>
            </div>
            <div class="modal-body">
            	<div class="row">
            	<form class="form-horizontal  col-md-12" method="post">
                    <div class="form-group">
                        <%--<label class="col-sm-2 control-label">列表路径</label>--%>
 						<input type="hidden" class="form-control" id="coluuid">
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="colName"
                                   placeholder="请输入">
                        </div>
                       
                    </div>
                </form>
            	</div>
                
                <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" id="saveColName" class="btn btn-primary">确认</button>
            	</div>
            </div>

            
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>


<div class="modal fade modal-info bs-example-modal-sm" id="myLogin"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm ">
        <div class="modal-header" style="border-bottom:0px;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel" style="color: #fff">登录</h4>
        </div>
        <div class="modal-content">

            <div class="modal-body">

            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<div class="modal fade modal-info bs-example-modal-md" id="myRegister"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-md ">
        <div class="modal-header" style="border-bottom:0px;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel" style="color: #fff">注册</h4>
        </div>
        <div class="modal-content">

            <div class="modal-body"
                 style="overflow-y: auto; padding-top: 0px;">

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myEle" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">编辑元素路径</h4>
            </div>
            <div class="modal-body">
            	<div class="row">
            	<form class="form-horizontal  col-md-12" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">列表路径</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="list_path"
                                   placeholder="修改" disabled = "true">
                        </div>
                       
                    </div>
                    <div class="form-group">
                       
                        <label class="col-sm-2 control-label">元素路径</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="node_path"
                                   placeholder="修改" disabled = "true">
                        </div>
                    </div>
                </form>
            	</div>
                
                <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" id="saveBtn" class="btn btn-primary">确认</button>
            	</div>
            </div>

            
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<input type="hidden" id="rows" value="${rows}">

<!-- Placed js at the end of the document so the pages load faster -->
<script src="/web/res/js/jquery-1.10.2.min.js"></script>
<script src="/web/res/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="/web/res/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/web/res/js/bootstrap.min.js"></script>
<script src="/web/res/js/modernizr.min.js"></script>
<script src="/web/res/js/jquery.nicescroll.js"></script>
<script src="/web/res/js/uuid.js"></script>
<script src="/web/res/js/md5.js"></script>
<script type="text/javascript">

    var session = "${sessionScope._sessionkey}";

    if (session) {
        $("#loginLi").html("<a class='dropdown-toggle' data-toggle='dropdown' href='javascript:void(0);'>${sessionScope._sessionkey.user_name}</a><ul class='dropdown-menu'><li><a href='/action/my/m/toMyCenter'><i class='fa fa-user'></i>  个人中心</a></li> <li><a href='/action/my/m/logout'><i class='fa fa-sign-out'></i>  退出</a></li>  </ul>");
    }
    var map = new Map();
    var cur_uuid;
    var cur_cn;
    var cur_obj;

    (function () {
        var ifm = document.getElementById("myiframe");
        var height = document.documentElement.clientHeight;
        var ifheight = height * 0.75;
        ifm.height = ifheight;


        $("#submitTpl").click(function () {
            $.post("/action/user/u/islogin", function (data) {
                if (data.code == 200) {
                    if (!map.isEmpty()) {
                        var tmpl = JSON.stringify(map);
                        var requestId = $("#reqid").val();
                        var doc = $('#myiframe').prop('contentWindow').document;
                        var title = $(doc).attr("title");
                        var trLength = $("#cols tr").length;
                        $.post("/action/tmpl/t/saveTemplate", {
                            "requestId": requestId,
                            "title": title,
                            "rows": trLength,
                            "tmpl": tmpl
                        }, function (data) {
                            if (data.code == 200) {
                                window.location.href = "/action/my/m/toMyCenter/";
                            }
                        });
                    } else {
                        layer.msg("请配置您要下载的数据模板")
                    }
                } else {
                    $("#myLogin").modal({
                        remote: "/portal/static/login.html"
                    });
                }
            });
        });

//        $("#saveBtn").click(function () {
//            document.getElementById('myiframe').contentWindow.editElePath($("#list_path").val(),$("#node_path").val(),cur_uuid);
//            $('#myEle').modal('hide');
//        });


        $("#saveTpl").click(function () {
            $.post("/action/user/u/islogin", function (data) {
                if (data.code == 200) {
                    if (!map.isEmpty()) {
                        var tmpl = JSON.stringify(map);
                        var requestId = $("#reqid").val();
                        var doc = $('#myiframe').prop('contentWindow').document;
                        var title = $(doc).attr("title");
                        $.post("/action/tmpl/t/saveTemplate", {
                            "requestId": requestId,
                            "title": title,
                            "tmpl": tmpl
                        }, function (data) {
                            if (data.code == 200) {
                                layer.msg("保存成功")
                            } else {
                                layer.msg(data.msg)
                            }
                        });
                    } else {
                        layer.msg("请配置您要下载的数据模板")
                    }
                } else {
                    $("#myLogin").modal({
                        remote: "/portal/static/login.html"
                    });
                }
            });
        });

        $('#tplType').change(function () {
            var type = $(this).children('option:selected').val();
            if (type == 'batch') {
                $('#maxPage').show();
            } else {
                $('#maxPage').hide();
            }
        })

    })();

    /**
     function callBack(xpath) {
        var cn = $("#xpaths").attr("classname");
        var uuid = $("#xpaths").attr("param");
        var doc = $('#myiframe').prop('contentWindow').document;
        callFrameEle2(doc.getElementsByClassName(cn), uuid, cn, xpath);
    }
     **/
    function showModal(list_path,node_path,unique_id,request_id,column_name,column_type) {
        if(list_path.indexOf('null')<0){
            $('#list_path').removeAttr('disabled');
        }else{
            $('#list_path').attr('disabled','true');
        }
        if(node_path.indexOf('null')<0){
            $('#node_path').removeAttr('disabled');
        }else{
            $('#node_path').attr('disabled','true');
        }
        $('#list_path').val(list_path);
        $('#node_path').val(node_path);
        $('#myEle').modal('show');

        $("#saveBtn").click(function () {
            var domainName = $("#dname").val();
            if (domainName.indexOf('http') < 0) {
                domainName = 'http://' + domainName;
            }
            document.getElementById('myiframe').contentWindow.parseElePath(domainName,$("#list_path").val(),$("#node_path").val(),unique_id,request_id,column_name,column_type);
            $('#myEle').modal('hide');
        });
    }

    function delDataCallBack(unique_id) {
        var doc = $('#myiframe').prop('contentWindow').document;
        $("[unique_id=" + unique_id + "]", doc).removeClass('__thinkdata_highlight');//移除左键高亮
        $("[unique_id=" + unique_id + "]", doc).removeClass('__thinkdata_highlight2');//移除右键高亮
        var eles = $("[unique_id=" + unique_id + "]", doc);
        for (var i = 0; i < eles.size(); i++) {
            eles[i].removeAttribute("unique_id");
        }
        $("#row_" + unique_id).remove();//清除左侧相应列表数据
        $(".list-group-item").remove();//清除右侧列表数据
        $('#collist').append('<a href="#" class="list-group-item active"><h4 class="list-group-item-heading">列表数据</h4></a>');
        map.remove(unique_id);
    }

 
    function delCol(obj,uuid) {
        if($("#head").children().length == 1){//删除最后一列的同时清空tbody，否则出现空行
            $('#cols').html('');
        }

    	var tdIndex = $(obj).parent().parent().parent().parent().index(); 
    	
        $("#head").eq(0).find("th").eq(tdIndex).remove();
        var ld = $("#cols  tr").length;
        for (var i = 0; i < ld; i++) {
            $("#cols tr").eq(i).find("td").eq(tdIndex).remove();
        }
        var doc = $('#myiframe').prop('contentWindow').document;
        var temp_uuid = uuid.indexOf('_a') > 0 ? uuid.substr(0,uuid.indexOf('_a')) : uuid;
      
        if (($("[uuid="+temp_uuid+"]").length == 0) && ($("[uuid="+temp_uuid+"_a]").length == 0)) {
            //当前属性和其链接同时删除才去除高亮
            $("[unique_id=" + temp_uuid + "]", doc).removeClass('__thinkdata_highlight');//移除左键高亮
            $("[unique_id=" + temp_uuid + "]", doc).removeClass('__thinkdata_highlight2');//移除右键高亮
            var eles = $("[unique_id=" + temp_uuid + "]", doc);
            for (var i = 0; i < eles.size(); i++) {
                eles[i].removeAttribute("unique_id");
            }
        }
        deleteEmptyRow();
        map.remove(uuid);
    }

    function editCol(uuid) {
        //console.log($(obj).text())
        var a = $("th[uuid='"+uuid+"']").find("a");
        
        var name =  $(a[0]).text();
        $('#colName').val(name)
        $('#editColModal').modal('show');
        $("#coluuid").val(uuid)
        //cur_obj = obj;
    }

    $('#saveColName').click(function(){
        var newColName = $('#colName').val().trim();
        if(newColName == ''){
            layer.msg('列名不能为空')
            return;
        }
        var uuid = $("#coluuid").val();
        uuid = uuid.indexOf('_a') > 0 ? uuid.substr(0,uuid.indexOf('_a')) : uuid;
        $("[uuid="+uuid+"]").children('a').text(newColName);
        $("[uuid="+uuid+"_a]").children('a').text(newColName)
        map.get(uuid).column_name = newColName;
        $('#editColModal').modal('hide');
    });

    function deleteEmptyRow() {
        var isEmpty = true;
        $("#cols tr").each(function (index, item) {
            $(item).find("td").each(function (tdindex, tditem) {
                if ($(tditem).text()) {
                    isEmpty = false;
                }
            });
            if (isEmpty) {
                $(item).remove();
            }
            isEmpty = true;
        });
    }

    function editColNameCallBack(unique_id) {
        $("#editBtn_"+unique_id).css("display","block");
        $("#editBtn_"+unique_id).click(function(){
            //修改列名
            var newColumnName = $("#columnName_"+unique_id).val() || '';
            if (newColumnName != '') {
                map.get(unique_id).column_name = newColumnName;
            }
            $("#editBtn_"+unique_id).css("display","none");
        });
    }

    function hideBtnCallBack(unique_id,column_name) {
        setTimeout("$('#editBtn_"+unique_id+"').css('display','none');",150);//避免与确定按钮冲突
        //setTimeout("$('#editBtn_"+unique_id+"').css('display','none');$('#columnName_"+unique_id+"').val('"+column_name+"');",150);//避免与确定按钮冲突
    }

    function addMap(uuid, data) {

        map.put(uuid, data);

    }

    function Map() {
        var struct = function (key, value) {
            this.key = key;
            this.value = value;
        }

        var put = function (key, value) {
            for (var i = 0; i < this.arr.length; i++) {
                if (this.arr[i].key === key) {
                    this.arr[i].value = value;
                    return;
                }
            }
            this.arr[this.arr.length] = new struct(key, value);
        }

        var get = function (key) {
            for (var i = 0; i < this.arr.length; i++) {
                if (this.arr[i].key === key) {
                    return this.arr[i].value;
                }
            }
            return null;
        }

        var remove = function (key) {
            var v;
            for (var i = 0; i < this.arr.length; i++) {
                v = this.arr.pop();
                if (v.key === key) {
                    continue;
                }
                this.arr.unshift(v);
            }
        }

        var size = function () {
            return this.arr.length;
        }

        var isEmpty = function () {
            return this.arr.length <= 0;
        }
        this.arr = new Array();
        this.get = get;
        this.put = put;
        this.remove = remove;
        this.size = size;
        this.isEmpty = isEmpty;
    }
</script>
<%--<script src="/web/res/js/fetch-element.js"></script>--%>

<!--common scripts for all pages-->
<script src="/web/res/js/scripts.js"></script>

</body>
</html>

