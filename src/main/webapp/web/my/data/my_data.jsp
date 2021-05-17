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
					<li><a href="#">数据处理</a></li>
					<li class="active">我的数据</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">我的采集数据</div>
                        <div class="panel-body">

                            <div class="col-md-12">

                                <form class="form-horizontal" role="form">
                                    <div class="form-group">

                                        <label for="username" class="col-sm-2 control-label">我的任务</label>

                                        <div class="col-sm-3 ">
                                            <select id="request" class="form-control"> </select>
                                        </div>
                                        <div class="col-sm-1 ">
                                            <button id="searchBtn" type="button" class="btn btn-success">查询</button>
                                        </div>
                                        <div class="btn-group ">
                                            <button data-toggle="dropdown" type="button"
                                                    class="btn btn-primary  dropdown-toggle">
                                                导出 <span class="caret"></span>
                                            </button>
                                            <ul role="menu" class="dropdown-menu">
                                                <li id="exportExcel"><a href="javascript:void(0)">excel</a></li>
                                                <!--
                                                <li id="exportCsv"><a href="javascript:void(0)">csv</a></li>
                                                 -->
                                                <li id="exportJson"><a href="javascript:void(0)">json</a></li>

                                            </ul>
                                        </div>
                                        <div class="btn-group ">
                                            <button data-toggle="dropdown" type="button"
                                                    class="btn btn-primary  dropdown-toggle">
                                                灌入数据库 <span class="caret"></span>
                                            </button>
                                            <ul role="menu" class="dropdown-menu">
                                                <li id="mysql"><a href="javascript:void(0)">msyql</a></li>
                                                <li id="sqlserver"><a href="javascript:void(0)">sqlserver</a></li>
                                            </ul>
                                        </div>
                                    </div>

                                </form>

                            </div>

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">采集数据列表</div>
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
                                        <li class="first"><a id="first" href="javascript:void(0)">首页</a></li>
                                        <li class="prev"><a id="prev" href="javascript:void(0)">上页</a></li>
                                        <li class="page active"><input id="page" type="text" value="1" name="page"/>
                                        </li>
                                        <li class="jump"><a id="jump" href="javascript:void(0)">跳转</a></li>
                                        <li class="next"><a id="next" href="javascript:void(0)">下页</a></li>
                                        <li class="last"><a id="last" href="javascript:void(0)">尾页</a></li>
                                        <li class="page"><a id="maxPage" href="javascript:void(0)"></a></li>
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

        <div tabindex="-1" role="dialog" id="importDB"
             class="modal fade bs-example-modal-lg" style="display: none;">
            <div role="document" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close"
                                class="close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title">灌入数据库</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="username" class="col-sm-3 control-label">数据库连接:</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="sql_url" onchange="urlChange()">

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="tables" class="col-sm-3 control-label">表</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="tables" onchange="tableChange()">
                                    </select>
                                </div>
                                <a class="col-sm-2" href="javascript:void(0)" onclick="newTable()"
                                   style="padding-top:5px">点我建表</a>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="exportSource">开始灌入数据库
                            <div class="touch-ripple"></div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div tabindex="-1" role="dialog" id="createTable"
             class="modal fade bs-example-modal-lg" style="display: none;">
            <div role="document" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close"
                                class="close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title">新建表</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-3">数据库</label>
                                <div class="col-sm-9">
                                    <input id="select_sqlurl" class="form-control" width="100%" type="text" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">表名</label>
                                <div class="col-sm-9" id="newTableDiv">
                                    <input id="newTableName" class="form-control" width="100%" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">建表语句</label>
                                <div class="col-sm-9">
                                    <textarea id="createSql" class="form-control" disabled rows="3" cols="20">

                                    </textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" id="saveTableBtn">生成语句
                        </button>
                        <button type="button" class="btn btn-success" id="reloadCloseBtn" style="display: none">关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- main content end--> </section>

<script type="text/javascript">
    var list = ${page};
    var jobs = ${jobs};
    var requestid = '${request_id}';
    var dataConfig;
    var sqlUrlId;
    var sel_requestId;

    $("#maxPage").text("共" + list.maxPage + "页");

    function initData(page, list) {
        $("#thead").empty();
        $("#tbody").empty();
        var ttitle = "";
        var ts = [];
        if (list.info && list.info.length > 0) {
            ts = list.info;
            for (var i = 0; i < ts.length; i++) {
                $("#thead").append("<th>" + ts[i].title + "</th>");
            }
        }
      

        var tbody = "";
        if (list.data && list.data.length > 0) {
            var rows = list.data;
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                tbody += "<tr>"
                for (var j = 0; j < ts.length; j++) {
                	
              
                    var key = ts[j].prop;
                
                    var val = row[key];
                    if (val == undefined || val == null || val == 'null') {
                        tbody += "<td  class='tdspan'></td>"
                    } else {
                        tbody += "<td  class='tdspan'>" + val + "</td>"
                    }

                }

                tbody += "</tr>"

            }

            $("#tbody").html(tbody);
        }

        $("#page").val(page)
    }

    function getdataConfig() {

        $.ajax({
            type: "POST",
            url: "/action/my/m/getRequestId",
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    dataConfig = data.data;
                }
            },
            error: function () {
                console.log('err');
            }
        });
    }

    function queryPageList(page) {
        var rid = $("#request").val();
        $.post("/action/my/m/getMyDagePage", {"requestid": rid, "page": page}, function (data) {
            initData(page, data);
        })
    }

    function createSql() {
        var requestid = $("#request").val();

    }

    function urlChange() {
        var url_id = $("#sql_url").val();
        var table_html = "<option value=''>请选择...</option>";
        for (var i = 0; i < dataConfig.length; i++) {
            if (url_id == dataConfig[i].id) {
                var tableList = dataConfig[i].tableList;
                if (!(tableList == null || tableList == "")) {
                    for (var j = 0; j < tableList.length; j++) {
                        table_html += "<option value='" + tableList[j].id + "'>" + tableList[j].name + "</option>";
                    }
                }
            }
        }
        $("#tables").html(table_html);
    }

    function tableChange() {
        var url_id = $("#sql_url").val();
        var table_id = $("#tables").val();
        for (var i = 0; i < dataConfig.length; i++) {
            if (url_id == dataConfig[i].id) {
                var tableList = dataConfig[i].tableList;
                if (!(tableList == null || tableList == "")) {
                    for (var j = 0; j < tableList.length; j++) {
                        if (tableList[j].id == table_id) {
                            $("#createSql").html(tableList[j].create_table_sql)
                        }
                    }
                }
            }
        }
    }

    function newTable() {
        var sql_id = $("#sql_url").val();
        var sql_url = $("#sql_url").find("option[value=" + sql_id + "]")[0].innerHTML;
        if (sql_id == "" || sql_id == null) {
            layer.msg("请先选择数据库连接");
        } else {
            sqlUrlId = sql_id;
            $("#selec" +
                "t_sqlurl").val(sql_url);
            $("#select_sqlurl").attr("s_id", sql_id);
            $("#createTable").modal("show");
            $("#importDB").modal("hide");
            $("#saveTableBtn").show();
            $("#reloadCloseBtn").hide();
            $("#createSql").html("");
        }
    }

    $(document).ready(function () {

        getdataConfig();
        initData(1, list);


        $("#exportExcel").click(function () {
            var requestid = $("#request").val();
            var url = "/action/my/m/export?type=excel&requestid=" + requestid;

            window.open(url, 'excelWin')

        });

        $("#exportCsv").click(function () {
            var requestid = $("#request").val();
            var url = "/action/my/m/export?type=csv&requestid=" + requestid;
            $.post(url, function (data) {
                alert(data)
            })

        });

        $("#exportJson").click(function () {
            var requestid = $("#request").val();
            var url = "/action/my/m/export?type=json&requestid=" + requestid;
            window.open(url, 'jsonWin')

        });

        $("#mysql").click(function () {
            sel_requestId = $("#request").val();
            var type_html = "<option value=''>请选择...</option>";
            for (var i = 0; i < dataConfig.length; i++) {
                if (dataConfig[i].type == "mysql") {
                    type_html += "<option value='" + dataConfig[i].id + "'>" + dataConfig[i].host + "-" + dataConfig[i].db + "</option>";
                }
            }
            $("#sql_url").html(type_html);
            $("#importDB").modal("show");
        });

        $("#sqlserver").click(function () {
            sel_requestId = $("#request").val();
            var type_html = "<option value=''>请选择...</option>";
            for (var i = 0; i < dataConfig.length; i++) {
                if (dataConfig[i].type == "sqlserver") {
                    type_html += "<option value='" + dataConfig[i].id + "'>" + dataConfig[i].host + "-" + dataConfig[i].db + "</option>";
                }
            }
            $("#sql_url").html(type_html);
            $("#importDB").modal("show");
        });

        $("#first").click(function () {
            queryPageList(1);
        });

        $("#prev").click(function () {
            var page = parseInt($("#page").val());
            if (page <= 1) {
                page = 1;
            } else {
                page--;
            }
            queryPageList(page);

        });

        $("#jump").click(function () {
            var page = parseInt($("#page").val());
            queryPageList(page);
        });
        $("#next").click(function () {

            var page = parseInt($("#page").val());
            page++;
            queryPageList(page);
        });
        $("#last").click(function () {

            queryPageList(100);
        });


        var op = "";
        for (var i = 0; i < jobs.length; i++) {
            if (requestid == jobs[i].request_id) {
                op += "<option value=" + jobs[i].request_id + " selected>" + jobs[i].template_name + "</option>";
            } else {
                op += "<option value=" + jobs[i].request_id + ">" + jobs[i].template_name + "</option>";
            }

        }
        $("#request").html(op);

        $("#searchBtn").click(function () {
            var requestid = $("#request").val();

            $.post("/action/my/m/getMyDagePage", {
                "requestid": requestid,
                "page": 1
            }, function (data) {
                initData(1, data);
            })
        });

        $("#saveTableBtn").click(function () {
            var name = $("#newTableName").val();
            $.post("/action/my/m/saveTableBtn", {
                "sqlUrlId": sqlUrlId,
                "name": name,
                "sel_requestId": sel_requestId
            }, function (data) {
                if (data.code == 200) {
                    $("#createSql").html(data.msg);
                    layer.msg("请将sql语句在对应的数据库运行，并生成对应的表");
                    $("#saveTableBtn").hide();
                    $("#reloadCloseBtn").show();
                    getdataConfig();
                } else {
                	layer.msg(data.msg);
                }
            })

        })
        $("#reloadCloseBtn").click(function () {
            $("#createTable").modal("hide");
        })
        
        $("#exportSource").click(function () {
            var sql_id=$("#sql_url").val();
            var table_id=$("#tables").val();
            $.post("/action/my/m/exportSource", {
                "sql_id": sql_id,
                "table_id": table_id,
                "sel_requestId":sel_requestId
            }, function (data) {
                layer.msg("正在灌入数据库");
                $("#importDB").modal("hide");
            })
        })

    })


</script>

</body>
</html>
