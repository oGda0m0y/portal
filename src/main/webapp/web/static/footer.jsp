<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <footer class="sticky-footer">
            2017 &copy;  think-data.cn by <a href="http://data.bds-analytics.com/" target="_blank">慧采集</a>
        </footer>
        
        
        

<script src="/web/res/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="/web/res/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/web/res/js/bootstrap.min.js"></script>
<script src="/web/res/js/modernizr.min.js"></script>
<script src="/web/res/js/jquery.nicescroll.js"></script>
<script src="/web/res/js/date/WdatePicker.js"></script>
<script src="/web/res/js/jqPaginator.js"></script>
<script src="/web/res/js/iCheck/jquery.icheck.js"></script>
<script src="/web/res/js/ajaxfileupload.js"></script>
<script src="/web/res/js/scripts.js"></script>
<script src="/web/res/js/md5.js"></script>
<script>


function getParam(paramName) { 
    paramValue = "", isFound = !1; 
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) { 
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0; 
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++ 
    } 
    return paramValue == "" && (paramValue = null), paramValue 
} 
$(document).ready(function () {
        	
    	var url = location.search; 
    	
    	var mid = getParam("mid");
    	var aid = getParam("aid");
    	
    	if(mid!=null&&mid!=''&&mid!=undefined){
    		if(mid=='a0'){
    			$("#"+mid).addClass("nav-hover");
    			return
    		}
    	}
    	console.log(mid)
    	if(mid!=null&&mid!=''&&mid!=undefined){
    		$("#"+mid).addClass("nav-active");
    	}
    	
    	if(aid!=null&&aid!=''&&aid!=undefined){
    		$("#"+aid).addClass("active");
    		
    	}
    	
    	
        
});
</script>
