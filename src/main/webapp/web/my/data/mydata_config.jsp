<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>我的中心</title>
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
        <div tabindex="-1" role="dialog" id="addDataSourceModal"
             class="modal fade bs-example-modal-lg" style="display: none;">
            <div role="document" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close"
                                class="close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title">添加数据源</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">数据库:</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="a_sqltype">
                                        <option value="">请选择数据库</option>
                                        <option value="mysql">mysql</option>
                                        <option value="sqlserver">sqlserver</option>
                                        <option value="oracle">oracle</option>
                                        <option value="postgresql">postgresql</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">主机名或IP</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="a_host"
                                               placeholder="请输入主机名或IP">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">端口</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="a_port"
                                               placeholder="请输入端口号">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="a_username"
                                               placeholder="请输入数据库用户名">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="password" class="form-control" id="a_password"
                                               placeholder="请输入数据库密码">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">数据库</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="a_sqldb"
                                               placeholder="请输入数据库名">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="saveAddSource">保存
                            <div class="touch-ripple"></div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div tabindex="-1" role="dialog" id="modifyDataSourceModal"
             class="modal fade bs-example-modal-lg" style="display: none;">
            <div role="document" class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" data-dismiss="modal" aria-label="Close"
                                class="close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title">修改数据源</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">数据库:</label>
                                <div class="col-sm-10">
                                    <select class="form-control" id="m_sqltype">
                                        <option value="">请选择数据库</option>
                                        <option value="mysql">mysql</option>
                                        <option value="sqlserver">sqlserver</option>
                                        <option value="oracle">oracle</option>
                                        <option value="postgresql">postgresql</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">主机名或IP</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="m_host">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">端口</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="m_port">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="m_username">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="username" class="form-control" id="m_password">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">数据库</label>
                                <div class="col-sm-10">
                                    <div class="el-input">
                                        <input type="text" class="form-control" id="m_sqldb"
                                               placeholder="请输入数据库名">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="modifySource">保存
                            <div class="touch-ripple"></div>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="wrapper">
			<div class="page-heading">
				<ul class="breadcrumb">
					<li><a href="#">数据处理</a></li>
					<li class="active">我的数据源</li>
				</ul>
			</div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">数据源配置</div>
                        <div class="panel-body">

                            <div class="col-md-12">

                                <form class="form-horizontal" role="form">
                                    <div class="form-group col-sm-10">
                                        <button id="addDataSoruseBtn" type="button"
                                                class="  btn btn-info">添加数据源
                                        </button>

                                    </div>

                                </form>

                            </div>

                            <div class="col-md-12">

                                <div class="panel ">
                                    <div class="panel-heading">数据源列表</div>
                                    <div class="panel-body" style="overflow-y: auto; padding-top: 0px;">
                                        <table class="table table-bordered table-striped table-condensed">
                                            <thead>
                                            <tr id="thead">
                                                <td align='center'>Id</td>
                                                <td align='center'>数据库类型</td>
                                                <td align='center' style="width: 80%">url</td>
                                                <td align='center'>用户名</td>
                                                <td>数据库</td>
                                                <td>操作</td>
                                            </tr>
                                            </thead>
                                            <tbody id=msConfigList>

                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                                <div class="paging"></div>


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
    var modifyId;
    var list = ${list};
    $(document).ready(function () {
        var mslist = "";
        if (list != null) {
            for (var i = 0; i < list.length; i++) {
                mslist = mslist + "<tr><td class='simple'>" + list[i].id + "</td><td class='tdspan'>" + list[i].type + "</td><td class='tdspan' sytle='width:60%'>" + list[i].url + "</td><td class='tdspan'>" + list[i].username + "</td>";
                mslist = mslist + "<td class='tdspan'>" + list[i].db + "</td><td class='tdspan'><a href='javascript:void(0)' onclick='testCon(" + list[i].id + ")'>连接测试</a>|<a href='javascript:void(0)' onclick='mSource(" + list[i].id + ")'>修改</a>|<a href='javascript:void(0)' onclick='delSource(" + list[i].id + ")'>删除</a></td></tr>";
            }
        }
        $("#msConfigList").html(mslist);
    });
    //增加数据源
    $("#addDataSoruseBtn").click(function () {
        $("#addDataSourceModal").modal("show");
    })
    //保存增加数据源
    $("#saveAddSource").click(function () {
        var sqltype = $("#a_sqltype").val();
        if(sqltype==""||sqltype==null){
            alert("数据库类型不能为空")
            return;
        }
        var host = $("#a_host").val();
        var port = $("#a_port").val();

        var username = $("#a_username").val();
        var password = $("#a_password").val();
        var sqldb = $("#a_sqldb").val();
        $.post("/action/my/ds/addSaveDataSourse", {
            "type": sqltype,
            "host": host,
            "port": port,
            "username": username,
            "password": password,
            "sqldb": sqldb
        }, function (result) {
            if (result.ret == true) {
                alert("保存成功");
                $("#addDataSourceModal").modal("hide");
            } else {
                alert(result.message);
            }
            window.location.reload();
        })
    });

    //修改数据源
    function mSource(id) {
        modifyId = id;
        $("#modifyDataSourceModal").modal("show");
        for (var i = 0; i < list.length; i++) {
            if (id == list[i].id) {
                $("#m_host").val(list[i].host);
                $("#m_port").val(list[i].port);
                $("#m_sqlUrl").val(list[i].sql_url);
                $("#m_username").val(list[i].username);
                $("#m_password").val(list[i].pwd);
                $("#m_sqldb").val(list[i].db);
                var types = $("#m_sqltype").find("option");
                for (var j = 0; j < types.length; j++) {
                    if (types[j].value == list[i].type) {
                        types.eq(j).attr("selected", "");
                    }
                }
            }
        }
    };
    //保存修改数据源
    $("#modifySource").click(function () {
        var sqltype = $("#m_sqltype").val();
        if(sqltype==""||sqltype==null){
            layer.msg("数据库类型不能为空")
            return;
        }
        var host = $("#m_host").val();
        var port = $("#m_port").val();

        var username = $("#m_username").val();
        var password = $("#m_password").val();
        var sqldb = $("#m_sqldb").val();
        $.post("/action/my/ds/modifySaveDataSourse", {
            "type": sqltype,
            "host": host,
            "port": port,
            "username": username,
            "password": password,
            "sqldb": sqldb,
            "id": modifyId
        }, function (result) {
            if (result == true) {
            	layer.msg("修改成功");
                $("#modifyDataSourceModal").modal("hide");
            } else {
                alert("修改失败");
            }
            window.location.reload();
        })
    });

    //测试数据库能否连接
    function testCon(id) {
        $.post("/action/my/ds/testConnection", {
            "id": id
        }, function (result) {
            if (result.code == 200) {
            	layer.msg("连接成功");
            } else {
            	layer.msg("连接失败，请核对信息");
            }
        })
    }

    function delSource(id) {
        $.post("/action/my/ds/delDataSourse", {
            "id": id
        }, function (result) {
            if (result.code == 200) {
            	layer.msg("操作成功");
                location.reload();
            }
        })
    }
</script>
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
</body>
</html>
