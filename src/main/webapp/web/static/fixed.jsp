<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

  <link rel="stylesheet" href="/web/res/css/fixed.css" />
	<style>
	
		div,ul,li,p,a,img{
			padding: 0;
			margin: 0;
		}
		
	</style>
	<div class="slide" >
		<ul class="icon">
			<li class="up" title="上一页"></li>
			<li class="qq"></li>
			<li class="tel"></li>
			<li class="wx"></li>
			<li class="down" title="下一页"></li>
		</ul>
		<ul class="info">
			<li class="qq">
				<p >在线沟通请点我<a href="http://wpa.qq.com/msgrd?v=3&uin=1830497776&site=qq&menu=yes" target="_blank">在线咨询</a></p>
			</li>
			<li class="tel">
				<p >咨询热线<br>01059440764<br><br>客服QQ<br>1830497776</p>
			</li>
			<li class="wx">
				<div style="padding:0;margin:0;"><img width="140" height="140" src="/web/res/img/mmqrcode1507712466292.png" /></div>
			</li>
		</ul>
	</div>
	<div id="fixbtn"  class="index_cy"></div>
	
	
	
	<script type="text/javascript">
	$(function(){
	
		$('.slide .icon li').not('.up,.down').mouseenter(function(){
			$('.slide .info').addClass('hover');
			$('.slide .info li').hide();
			$('.slide .info li.'+$(this).attr('class')).show();//.slide .info li.qq
		});
		$('.slide').mouseleave(function(){
			$('.slide .info').removeClass('hover');
		});
		
		$('#fixbtn').click(function(){
		
			$('.slide').toggle();
			if($(this).hasClass('index_cy')){
				$(this).removeClass('index_cy');
				$(this).addClass('index_cy2');
			}else{
				$(this).removeClass('index_cy2');
				$(this).addClass('index_cy');
			}
			
		});
		
	});
	
	
	</script>