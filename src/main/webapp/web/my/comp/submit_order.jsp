<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>我的中心</title>
    <%@ include file="../../static/top.jsp" %>

    <style>

        th {
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
                    <li class="active">提交工单</li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">提交工单</div>
                        <div class="panel-body">


                            <div class="panel-body">
                                <form role="form" class="form-horizontal adminex-form">
                                  
                                    <div class="form-group has-success">
                                      <label class="col-lg-2 control-label">所属项目</label>
                                        <div class="col-lg-10">
                                            <select placeholder="" id="f_project" class="form-control">
                                            </select>

                                            <p class="help-block">请选所属项目</p>
                                        </div>
                                       
                                    </div>
                                     <div class="form-group has-success">
                                      
                                        <label class="col-lg-2 control-label">服务项目</label>
                                        <div class="col-lg-10">
                                            <select placeholder="" id="type" class="form-control">
                                                <option value="新增URL">新增URL</option>
                                                <option value="新增层级">新增层级</option>
                                                <option value="新增字段">新增字段</option>
                                                <option value="数据对接">数据对接</option>
                                                <option value="APP采集">APP采集</option>
                                            </select>

                                            <p class="help-block">请选择您需求的服务项目</p>
                                        </div>
                                    </div>
                                    <div class="form-group has-error">
                                        <label class="col-lg-2 control-label">工单标题</label>
                                        <div class="col-lg-4">
                                             <input type="text" placeholder="" id="title" class="form-control">
                                        </div>
                                        <label class="col-lg-2 control-label">关联工单号</label>
                                        <div class="col-lg-4">
                                            <input type="text" placeholder="" id="work_num" class="form-control">
                                        
                                        </div>
                                    </div>
                                
                                    
                                    <div class="form-group has-error">
                                        <label class="col-lg-2 control-label">需求内容</label>
                                        <div class="col-lg-10">
                                            <textarea rows="6" class="form-control" id="content"></textarea>
                                         
                                        </div>
                                    </div>
                                    <div class="form-group has-warning" >
                                        <label class="col-lg-2 control-label">附件</label>
                                        <div class="col-lg-10">
                                            <div class="col-md-4">
										    	<input type="file" id="fileId"   onchange="getFileName(this);" class="form-control" name="file" /> 
										    	<input type="hidden" class="form-control" value="" id="filename">
												<input type="hidden" class="form-control" value="" id="filepath">
										    </div>
										     <div class="col-md-2">
										      <input type="button" id="fileSaveBtn" class="form-control btn  btn-primary" value="上传" />
										     </div>
                                            <p class="help-block">如果有其他附件</p>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-offset-2 col-lg-10">
                                            <button class="btn btn-primary" type="submit" id="submitBtn">提交工单</button>
                                        </div>
                                    </div>
                                </form>

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

        <!-- main content end-->
</section>

<script type="text/javascript">
function getFileName(target){  
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;//兼容IE6以上的版本  
    var fileSize = 0;  
    var fileName = null;  
    if(isIE && !target.files){  
        var filePath = target.value;  
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");  
        var file = fileSystem.GetFile(filePath);  
        fileName = file.Name;  
     	$("#filename").val(fileName);
    }else{  
        fileName = target.files[0].name;  
        $("#filename").val(fileName);
      
    }  
    
}  
    var p_list =${p_list};
    //p_list=('(' + p_list + ')');
    $(document).ready(function () {
    	
    	
    	$("#fileSaveBtn").click(function(){
			
			 var filename =  $("#filename").val();
			 $.ajaxFileUpload({
		          url: '/action/my/m/uplaod/uploadFile', //用于文件上传的服务器端请求地址
		          secureuri: false, //是否需要安全协议，一般设置为false
		          fileElementId: "fileId", //文件上传域的ID
		          type : "POST",
		          dataType: 'multipart/form-data', //返回值类型 一般设置为json
		          success: function (data, status){
		         
		          	var json = eval('('+data + ')');
		          	if(json.code==200){
		          		layer.msg("上传成功");	
		          		$("#filepath").val(json.data);
		          	} else{
		        		layer.msg(json.msg);
		        	} 
		          },
		          error: function (data, status, e){
		        	  var json = eval('('+data + ')');
		              layer.msg(json.msg);
		          }
		      });
			
		});
    	
    	
    	
        var project_html = "";
        if (p_list != null) {
            for (var i = 0; i < p_list.length; i++) {
                project_html += "<option value='" + p_list[i].id + "'>" + p_list[i].project_name + "</option>";
            }
            $("#f_project").html(project_html);
        }
        $("#submitBtn").click(function () {
            var project_id = $("#f_project").val();
            var type = $("#type").val();
            var work_num = $("#work_num").val();
            var title = $("#title").val();
            var content = $("#content").val();
            var filepath =$("#filepath").val();
            var filename =$("#filename").val();
            
            if(project_id==''||project_id==undefined){
    			layer.msg("请选择所属项目");
    			return;
    		}
            if(type==''||type==undefined){
    			layer.msg("请选择需求类型");
    			return;
    		}
            if(title==''||title==undefined){
    			layer.msg("请输入工单标题");
    			return;
    		}
            if(content==''||content==undefined){
    			layer.msg("请输入需求描述");
    			return;
    		}
            
            var workorder = {};
            
            workorder.project_id = project_id;
            workorder.bind_no =work_num;
            workorder.title =title;
            workorder.type =type;
            workorder.content =content;
            workorder.attch_path =filepath;
            workorder.attch_name =filename;
            
            $.post("/action/my/c/saveWorkOrder", workorder,function (data) {
                if(data.code==200){
                    layer.msg("保存成功");
                    window.location.href="/action/my/c/toCompWorkOrder?mid=a4&aid=ch42";
                }else{
                    layer.msg(data.msg)
                }
            })
        })
    })


</script>

</body>
</html>
