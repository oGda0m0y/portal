<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="/"><img width=150px height=50px  src="/web/res/images/logo-white.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="/action/my/m/toMyCenter"><img width=40px height=30px  src="/web/res/images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->


        <div class="left-side-inner">

            <!-- visible to small devices only -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media logged-user">
                    <img alt="" src="/web/res/images/photos/user-avatar.png" class="media-object">
                    <div class="media-body">
                        <h4><a href="#">John Doe</a></h4>
                        <span>"Hello There..."</span>
                    </div>
                </div>

                <h5 class="left-nav-title">智数</h5>
                <ul class="nav nav-pills nav-stacked custom-nav">
                    <li><a href="/action/my/s/accountSetting?mid=a3&aid=ch31"><i class="fa fa-user"></i> <span>信息设置</span></a></li>
                    <li><a href="/action/my/s/safetySetting?mid=a3&aid=ch32"><i class="fa fa-cog"></i> <span>安全设置</span></a></li>
                    <li><a href="/action/my/m/logout"><i class="fa fa-sign-out"></i> <span>退出</span></a></li>
                </ul>
            </div>

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li id="a0"><a href="/action/my/m/toMyCenter?mid=a0"><i class="fa fa-home"></i> <span>我的中心</span></a></li>
                <li  id="a1" class="menu-list"><a href="javascript:void(0)"><i class="fa fa-laptop"></i> <span>我的采集</span></a>
                    <ul class="sub-menu-list">
                        <li id="ch11"><a  href="/action/my/m/toMySnatch?mid=a1&aid=ch11"> 采集列表</a></li>
                        <li id="ch12"><a  href="/action/my/m/toTemplateConfig?mid=a1&aid=ch12"> 模板配置</a></li>
                        <li id="ch13"><a  href="/action/my/m/toMyJob?mid=a1&aid=ch13"> 我的任务</a></li>
                    </ul>
                </li>
                <li id="a2" class="menu-list"><a  href="javascript:void(0)"><i class="fa fa-book"></i> <span>数据处理</span></a>
                    <ul class="sub-menu-list">
                        <li  id="ch21"><a href="/action/my/m/toMyData?mid=a2&aid=ch21"> 我的数据</a></li>
                        <li  id="ch22"><a href="/action/my/m/toMyDataConfig?mid=a2&aid=ch22"> 数据源配置</a></li>
                        <li  id="ch23"><a href="/action/my/m/toMyExportDB?mid=a2&aid=ch23"> 数据灌入记录</a></li>
                        
                    </ul>
                </li>
                <li  id="a3" class="menu-list"><a  href="javascript:void(0)"><i class="fa fa-cogs"></i> <span>个人中心</span></a>
                    <ul class="sub-menu-list">
                        <li id="ch31"><a  href="/action/my/s/accountSetting?mid=a3&aid=ch31"> 个人账户</a></li>
                        <li id="ch32"><a  href="/action/my/s/safetySetting?mid=a3&aid=ch32"> 安全设置</a></li>
                        <li id="ch33"><a  href="/action/my/s/toRecharge?mid=a3&aid=ch33"> 我要充值</a></li>
                        <li id="ch37"><a  href="/action/my/s/toOrder?mid=a3&aid=ch37"> 我的订单</a></li>
                        <li id="ch34"><a  href="/action/my/s/toRechargeRecord?mid=a3&aid=ch34"> 充值记录</a></li>
                        <li id="ch35"><a  href="/action/my/s/toConsumptionRecord?mid=a3&aid=ch35"> 消费记录</a></li>
                        <li id="ch36"><a  href="nestable.html?mid=a3&aid=ch36"> 帮助中心</a></li>

                    </ul>
                </li>
                <c:if test="${sessionScope._sessionkey.type eq 1}">
                   <li id="a4" class="menu-list"><a  href="javascript:void(0)"><i class="fa fa-star"></i> <span>VIP服务</span></a>
                    <ul class="sub-menu-list">
                        <li  id="ch41"><a href="/action/my/c/toCompProject?mid=a4&aid=ch41"> 项目列表</a></li>
                        <li  id="ch42"><a href="/action/my/c/toCompWorkOrder?mid=a4&aid=ch42"> 我的工单</a></li>
                        <li  id="ch43"><a href="/action/my/c/toCompSubWorkOrder?mid=a4&aid=ch43"> 提交工单</a></li>
                        <!--  <li  id="ch44"><a href="/action/my/c/toCompRecord?mid=a4&aid=ch44"> 消费明细</a></li> -->
                       
                    </ul>
                	</li>
                </c:if>

             

                

            </ul>
            <!--sidebar nav end-->

        </div>
    </div>