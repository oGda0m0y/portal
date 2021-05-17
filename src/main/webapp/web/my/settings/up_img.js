$(document).ready(function(){
        $("#up-img-touch").click(function(){
        		  $("#up-modal-frame").modal({});
        });
});
$(function() {
    'use strict';
    // 初始化
    var $image = $('#up-img-show');
    $image.cropper({
        aspectRatio: '1',
        autoCropArea:0.8,
        preview: '.up-pre-after',
        responsive:true,
    });

    // 上传图片
    var $inputImage = $('.up-modal-frame .up-img-file');
    var URL = window.URL || window.webkitURL;
    var blobURL;
  
    if (URL) {
        $inputImage.change(function () {
        	
            var files = this.files;
            var file;

            if (files && files.length) {
               file = files[0];

               if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $image.one('built.cropper', function () {
                        // Revoke when load complete
                       URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $inputImage.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }
    
    //绑定上传事件
    $('.up-modal-frame .up-btn-ok').on('click',function(){
    	 
    	var $modal_loading = $('#up-modal-loading');
    	var $modal_alert = $('#up-modal-alert');
    	var img_src=$image.attr("src");
    	console.log(img_src);
    	if(img_src==""){
    		set_alert_info("没有选择上传的图片");
    		$modal_alert.modal();
    		return false;
    	}
    	
    	$modal_loading.modal();
    	
    	var url=$(this).attr("url");
    	//parameter
    	var parameter=$(this).attr("parameter");
    	//console.log(parameter);
    	var parame_json = eval('(' + parameter + ')');
    	var width=parame_json.width;
    	var height=parame_json.height;
    

    	//控制裁剪图片的大小
    	var canvas=$image.cropper('getCroppedCanvas',{width: width,height: height});
    	var data=canvas.toDataURL(); //转成base64
        $.ajax({  
                url:'/action/my/s/uploadImage',  
                dataType:'json',  
                type: "POST",  
                data: {"image":data.toString()}, 
                success: function(data){
                	$modal_loading.modal('close');
                	set_alert_info(data.result);
                	$modal_alert.modal();
                	if(data.code==200){
                		$("#up-img-touch img").attr("src",data.file);
                		//var img_name=data.file.split('/')[2];
                		//console.log(img_name);
                		//$(".up-img-txt a").text(img_name);
                		 $("#imgPath").attr("src", data.data);
                		$("#up-modal-frame").modal('close');
                	}
                },
                error: function(){
                	$modal_loading.modal('close');
                	set_alert_info("上传文件失败了！");
                	$modal_alert.modal();
                	console.log('Upload error');  
                }  
         });  
    	
    	/*
    	
		   	 $.ajaxFileUpload({
		         url: '/action/my/s/uploadImage', //用于文件上传的服务器端请求地址
		         secureuri: false, //是否需要安全协议，一般设置为false
		         fileElementId: "fileId", //文件上传域的ID
		         type : "POST",
		         dataType: 'multipart/form-data', //返回值类型 一般设置为json
		         success: function (data, status){
		        
		         	var json = eval('('+data + ')');
		         	if(json.code==200){
		         		  $("#imgPath").attr("src", json.data);
	                      $("#imgSuc").show();
		         	} else{
		       			layer.msg(json.msg);
		         	} 
		         },
		         error: function (data, status, e){
		       	  var json = eval('('+data + ')');
		             layer.msg(json.msg);
		         }
		     });
    	
    	
    	**/
    	 
    	
    });
    
    $('#up-btn-left').on('click',function(){
    	$("#up-img-show").cropper('rotate', 90);
    });
    $('#up-btn-right').on('click',function(){
    	$("#up-img-show").cropper('rotate', -90);
    });
});


function set_alert_info(content){
	$("#alert_content").html(content);
}



 
