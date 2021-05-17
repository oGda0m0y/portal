$(function () {
    var mouseIndex = 0;
    var nodeType;
    var requestId = window.parent.document.getElementById("reqid").getAttribute("value");
    var domainName = window.parent.document.getElementById("dname").getAttribute("value");
    if (domainName.indexOf('http') < 0) {
        domainName = 'http://' + domainName;
    }
    getTemplateDetail(domainName, requestId);//获取模板details回显

    //防止右键时出现浏览器菜单
    $("body").contextmenu(function () {
        return false;
    }).mousedown(function (e) {
        if (e.button == 0) {
            mouseIndex = 0;
            nodeType = 'list';
        }

        if (e.button == 2) {
            mouseIndex = 2;
            nodeType = 'element';
        }

        var ft = $(e.target).first();
        var id = ft.attr("__thinkdata_id") || '';
        var unique_id = ft.attr("unique_id") || '';
        if (id != '') {
            if (unique_id == '') {//未点击
                var trIndex = $("#head th", window.parent.document).length;
                var columnName = '属性' + (trIndex + 1);
                addTemplateDetail(domainName, requestId, columnName, mouseIndex, nodeType, id);
            } else {//再次点击取消
                delTemplateDetail(domainName, unique_id);
            }
        }


        //  var trIndex = $('#cols', window.parent.document).find("tr").length;
        //  $('#cols', window.parent.document).append("<tr><td>属性"+(trIndex+1)+"</td><td>"+selVal+"</td></tr>");

    }).mouseover(function (e) {
        var ft = $(e.target).first();
        var id = ft.attr("__thinkdata_id") || '';
        if (id != '') {
            ft.addClass("__thinkdata_hover");

           var path = readXPath(ft.context);
           layer.tips('路径:'+path, "[__thinkdata_id=" + id + "]", {
        	   tips: [1, '#3595CC'],
        	   time: 4000
        	 });

          // $("[__thinkdata_id=" + id + "]").tooltip({text: '路径！'+path});
        }
    }).mouseout(function (e) {
        var ft = $(e.target).first();
        var id = ft.attr("__thinkdata_id") || '';
        if (id != '') {
            ft.removeClass("__thinkdata_hover");
        }
    });


});

function addTemplateDetail(domainName, requestId, columnName, mouseIndex, type, id) {

    $.post(domainName + "/action/tmpl/t/addTemplateDetail", {
        "requestId": requestId,
        "columnName": columnName,
        "type": type,
        "thinkDataId": id
    }, function (data) {
        console.log(data)
        if (data.code == 200) {
        	var eles = data.data;
        	if(eles!=null&&eles!=undefined){
        		for (var i = 0; i < eles.length; i++) {
        	        addData(eles[i], mouseIndex,false); 
        	    }
        	}
        	
        	
           
        }
    });

}

function delTemplateDetail(domainName, unique_id) {
    $.ajax({
        type: "POST",
        url: domainName + "/action/tmpl/t/delTemplateDetail",
        async: false,
        data: {uniqueId: unique_id},
        dataType: "json",
        success: function (data) {
            if (data.ret) {
                delData(unique_id);//删除高亮和list
            } else {
                console.log(data.msg);
            }
        },
        error: function () {
            console.log('err');
        }
    });
}



function delData(unique_id) {
    $("[unique_id=" + unique_id + "]").removeClass('__thinkdata_highlight');//移除左键高亮
    $("[unique_id=" + unique_id + "]").removeClass('__thinkdata_highlight2');//移除右键高亮
    var eles = $("[unique_id=" + unique_id + "]");
    for (var i = 0; i < eles.size(); i++) {
        eles[i].removeAttribute("unique_id");
    }

    if ($("#head", parent.document).children().length == 2) {
        var obj0 = new Object();
        var obj1 = new Object();
        $('#head', parent.document).children().each(function (index, element) {
            //console.log(index + ';' + $(element));
            if (index == 0) {
                obj0 = element;
            }
            if (index == 1) {
                obj1 = element;
            }
        });
        if (obj0.attributes[1].nodeValue.indexOf(obj1.attributes[1].nodeValue) || obj1.attributes[1].nodeValue.indexOf(obj0.attributes[1].nodeValue)) {
            //剩下的两列如果是一对，那么将多余tr标签删除
            $('#cols', parent.document).html('');
        }
    } else if ($("#head", parent.document).children().length == 1) {//删除最后一列的同时清空tbody，否则出现空行
        $('#cols', parent.document).html('');
    }

    $("[uuid=" + unique_id + "]", parent.document).remove();//清除td
    $("[uuid=" + unique_id + "_a]", parent.document).remove();//清除td_a
    deleteEmptyRow();//删除多余空行
    window.parent.map.remove(unique_id);//在map对象中移除
}


function addData(row, mouseIndex,callBack) {//callBack:true表示是回显
    var unique_id = row.unique_id;
    var th = row.th;
  
    /**
    if(row.tag_name=='a'||row.tag_name=='A'){
        th = "<th  uuid='" + unique_id + "' index="+index+">" + row.column_name + " <i class='fa fa-trash-o' style='cursor:pointer' onclick=\"delCol(this,'" + unique_id + "')\"></i> </th><th index="+index+"  uuid='" + unique_id + "_a'>" + row.column_name + "链接 <i class='fa fa-trash-o' style='cursor:pointer'  onclick=\"delCol(this,'" + unique_id + "_a')\"></i></th>"
    } else if (row.elements[0].src != '') {
        th = "<th  index="+index+" uuid='" + unique_id + "'>" + row.column_name + "链接  <i class='fa fa-trash-o' style='cursor:pointer'  onclick=\"delCol(this,'" + unique_id + "')\"></i> </th>"
    } else {
        th = "<th index="+index+"  uuid='" + unique_id + "'>" + row.column_name + " <i class='fa fa-trash-o' style='cursor:pointer'  onclick=\"delCol(this,'" + unique_id + "')\"></i> </th>"
    }
	**/
    var rlength =  $("#rows", window.parent.document).val();
    rlength =  parseInt(rlength);

    $("#head", window.parent.document).append(th);
    var thLength = $("#head th", window.parent.document).length;
    var trLength = $("#cols tr", window.parent.document).length;
    var rowLength = row.elements.length;
    if (rowLength > 0) {
        var eles = row.elements;
        var rows = eles.length;
        if (rowLength > trLength) {
            var last = rowLength - trLength;
            for (var i = 0; i < last; i++) {
                $("#cols", window.parent.document).append("<tr id='r"+i+"'></tr>");
            }
            rows = rowLength;
        } else if (trLength > rowLength) {
            rows = trLength;
        }
        
        if(callBack){
        	rows=rlength;
        }
      
        if (mouseIndex == 0) {
            for (var i = 0; i < rows; i++) {
                var ele = eles[i];
                if (ele == null || ele == undefined || ele == '') {
                	continue;
                }
                var td = ele.td;
                var tr = $("#cols", window.parent.document).find("tr").eq(i);
                var tdLength = $(tr).find("td").length;
                var j = 1;
                if (ele != null && ele != undefined && ele != '') {
                

                    $("[__thinkdata_id=" + eles[i].id + "]").addClass("__thinkdata_highlight");//添加左键高亮

                    $("[__thinkdata_id=" + eles[i].id + "]").attr("unique_id", unique_id);

                }
                
                var colsLength = tdLength + j;
                if (thLength > colsLength) {
                    for (var k = 0; k < (thLength - colsLength); k++) {
                    	td = "<td name='tb_" + unique_id + "' uuid='" + unique_id + "'></td>" + td;
                    }
                }
                
                $(tr, window.parent.document).append(td);
                
                var hdIndex = $("#head th[index=1]", window.parent.document);
                var array= new Array();
                for(var m=0;m<hdIndex.length;m++){
                
                	array.push(hdIndex[m].cellIndex);
                }
                
                
                var trs = $("#cols", window.parent.document).find("tr").eq(0);
                var tds = $(trs).find("td");
            	for(var j=0;j<tds.length;j++){
            		var tdj = tds[j];
            		var v = $(tdj).text();
            		var is = $.inArray(j, array);
            		if(is>=0){
            			for(var r=0;r<=rows;r++){
            				var ttd = $("#cols", window.parent.document).find("tr").eq(r).find("td");
            				$(ttd[j]).text(v);
            				 td = "<td name='tb_" + unique_id + "' uuid='" + unique_id + "'></td>"
            			}
            		}
            	}
  
            }
        } else {
            var ele = eles[0];
            var td = "";
           
            var tdd = ele.td;
            for(var l=0;l<rows;l++){
                 var tr = $("#r"+l, window.parent.document);
                 //var clength = tr.cells.length;
                 console.log(tdd);
                 $(tr, window.parent.document).append(tdd);
            }
           
            $("[__thinkdata_id=" + eles[0].id + "]").addClass("__thinkdata_highlight2");//添加右键高亮
        }
    
      
        window.parent.addMap(unique_id, row);
       
       
        
    }
}





/**
 function addData(row, trIndex, mouseIndex, callBack) {//callBack:true表示是回显
    var unique_id = row.unique_id;
    var btn = "<button class='btn btn-primary' type='button'>显示数据→</button>";
    if (row.elements.length > 0) {
        if (callBack) {
            $('#cols', window.parent.document).append("<tr id='row_" + unique_id + "' ><td><div class='col-md-8'><input type='text' id='columnName_" + unique_id + "' class='form-control' value='" + trIndex + "' onfocus=\"editColNameCallBack('" + unique_id + "')\" onblur=\"hideBtnCallBack('" + unique_id + "','" + trIndex + "')\"></div><div class='col-md-3'><button id='editBtn_" + unique_id + "' style='display: none'>确定</button></div></td><td><button class='btn btn-primary' type='button' id='' onclick=\"delDataCallBack('" + unique_id + "')\">删除</button>&nbsp;&nbsp;&nbsp;<button class='btn btn-primary' type='button' id='' onclick=\"showModal('" + row.list_path + "','" + row.node_path + "','" + unique_id + "','" + row.request_id + "','" + row.column_name + "','" + row.column_type + "')\">编辑</button></td><td style='cursor:pointer' onclick=\"showCols('" + unique_id + "')\">" + btn + "</td></tr>");
        } else {
            $('#cols', window.parent.document).append("<tr id='row_" + unique_id + "' ><td><div class='col-md-8'><input type='text' id='columnName_" + unique_id + "' class='form-control' value='属性" + (trIndex + 1) + "' onfocus=\"editColNameCallBack('" + unique_id + "')\" onblur=\"hideBtnCallBack('" + unique_id + "','属性" + (trIndex + 1) + "')\"></div><div class='col-md-3'><button id='editBtn_" + unique_id + "' style='display: none'>确定</button></div></td><td><button class='btn btn-primary' type='button' id='' onclick=\"delDataCallBack('" + unique_id + "')\">删除</button>&nbsp;&nbsp;&nbsp;<button class='btn btn-primary' type='button' id='' onclick=\"showModal('" + row.list_path + "','" + row.node_path + "','" + unique_id + "','" + row.request_id + "','" + row.column_name + "','" + row.column_type + "')\">编辑</button></td><td style='cursor:pointer' onclick=\"showCols('" + unique_id + "')\">" + btn + "</td></tr>");
        }
        $('#collist', window.parent.document).empty();
        $('#collist', window.parent.document).append('<a href="#" class="list-group-item active"><h4 class="list-group-item-heading">列表数据</h4></a>');
        var eles = row.elements;
        var its = "";
        for (var i = 0; i < eles.length; i++) {
            var ele = eles[i];
            var title = ele.value;
            if (mouseIndex == 0) {
                $("[__thinkdata_id=" + eles[i].id + "]").addClass("__thinkdata_highlight");//添加左键高亮
            } else {
                $("[__thinkdata_id=" + eles[i].id + "]").addClass("__thinkdata_highlight2");//添加右键高亮
            }
            $("[__thinkdata_id=" + eles[i].id + "]").attr("unique_id", unique_id);
            var it = " <a href='javascript:void(0)' class='list-group-item'><p class='list-group-item-text'>" + title + "</p> </a>";
            $("#collist", window.parent.document).append(it);
            its = its + it;
        }
        window.parent.addMap(unique_id, row);
    }
}
 **/
function getTemplateDetail(domainName, requestId) {
    $.post(domainName + "/action/tmpl/t/getTemplateDetail", {
        "requestId": requestId
    }, function (data) {
        if (data.code == 200) {
            renderTemplate(data.data);
        }
    });
}

function renderTemplate(eles) {
	if(eles!=null&&eles!=undefined){
		for (var i = 0; i < eles.length; i++) {
	        if (eles[i].column_type == 'list') {
	        	addData(eles[i], 0,true);
	        } else {
	        	addData(eles[i], 2,true);
	        }
	    }
	}
    
}
/**
function parseElePath(domainName, list_path, node_path, unique_id, request_id, column_name, column_type) {
    $.post(domainName + "/action/tmpl/t/parseElePath", {
        "listPath": list_path,
        "nodePath": node_path,
        "requestId": request_id,
        "columnName": column_name,
        "columnType": column_type
    }, function (data) {
        if (data.code == 200) {
            if (data.data.elements.length > 0) {
                delData(unique_id);//成功后清除页面数据
                var trIndex = $("#head th", window.parent.document).length;
                var mouseIndex = column_type == 'list' ? 0 : 1;
                addData(data.data, trIndex, mouseIndex, false);
            }
        }
    });
}
**/
function deleteEmptyRow() {
    var isEmpty = true;
    $("#cols tr", parent.document).each(function (index, item) {
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


function readXPath(element) {
    if (element.id !== "" && typeof(element.id) != 'undefined') {//判断id属性，如果这个元素有id，则显 示//*[@id="xPath"]  形式内容
        return '//*[@id=\"' + element.id + '\"]';
    }
    //这里需要需要主要字符串转译问题，可参考js 动态生成html时字符串和变量转译（注意引号的作用）
    if (element == document.body) {//递归到body处，结束递归
        return '/html/' + element.tagName.toLowerCase();
    }
    var ix = 1,//在nodelist中的位置，且每次点击初始化
        siblings = element.parentNode.childNodes;//同级的子元素

    for (var i = 0, l = siblings.length; i < l; i++) {
        var sibling = siblings[i];
        //如果这个元素是siblings数组中的元素，则执行递归操作
        if (sibling == element) {
            return arguments.callee(element.parentNode) + '/' + element.tagName.toLowerCase() + '[' + (ix) + ']';
            //如果不符合，判断是否是element元素，并且是否是相同元素，如果是相同的就开始累加
        } else if (sibling.nodeType == 1 && sibling.tagName == element.tagName) {
            ix++;
        }
    }
};
