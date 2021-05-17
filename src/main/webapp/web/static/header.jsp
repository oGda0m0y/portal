<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="header-section">

        <!--toggle button start-->
        <a class="toggle-btn"><i class="fa fa-bars"></i></a>
      
        <!--notification menu start -->
        <div class="menu-right">
            <ul class="notification-menu">
                
               
                <li>
                    <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span id="msgTotal" class="badge"></span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-head pull-right">
                        <h5 class="title">消息通知</h5>
                        <ul id="topmsg" class="dropdown-list normal-list">
                            
                            
                        </ul>
                    </div>
                </li>
                <li>
                    <a href="#" class="btn btn-default dropdown-toggle user_name" data-toggle="dropdown">
                        <img src="${sessionScope._sessionkey.img}" alt="" />
                        <span id="username"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                        <li><a href="/action/my/s/accountSetting"><i class="fa fa-user"></i> <span>信息设置</span></a></li>
                        <li><a href="/action/my/s/safetySetting"><i class="fa fa-cog"></i>  安全设置</a></li>
                        <li><a href="/action/my/m/logout"><i class="fa fa-sign-out"></i> 退出</a></li>
                    </ul>
                </li>

            </ul>
        </div>
        <!--notification menu end -->

        </div>
        
        <script>

        (function() {
        	
        	$.post("/action/user/u/islogin", function(data) {
        		if (data.code == 200) {
        			$("#username").text(data.data.user_name);
        		}
        	});
        	
        	
        	 $.get("/action/my/m/getMessagePage",{"pageNum" : 1,"pageSize" : 5,"isread" : 0},function(data){
        		 var msgrow = "";
        		 var contain;
        		 if (data.data.length) {
						for (var i = 0; i < data.data.length; i++) {
							contain = data.data[i];
							msgrow+=" <li class='new'><a href='/action/my/m/readMsg'><span class='label label-danger'><i class='fa fa-bolt'></i></span><span class='name'>【"+contain.type+"】"+contain.msg+".  </span> </a></li>";
						}
        		 }
        		 msgrow+="<li class='new'><a href='/action/my/m/readMsg'>已看完所有消息</a></li>";
        		 
        		 $("#topmsg").html(msgrow);
        		 $("#msgTotal").text(data.totalCount);
        	 })
        	
        })();
        
        </script>