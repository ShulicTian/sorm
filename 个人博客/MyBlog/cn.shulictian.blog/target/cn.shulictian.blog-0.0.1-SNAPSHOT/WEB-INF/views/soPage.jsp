<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="shulictian.ssm.po.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div style="background-color: #f5f6f7;">
		<div style="height: 345px; padding: 10px 0px; margin-bottom: 20px;">
			<div class="layui-carousel" id="imgs"
				style="margin: auto; box-shadow: 0px 0px 5px black;">
				<div carousel-item="">
					<div>
						<img src="img/logo.jpg" />
					</div>
					<div>
						<img src="img/back.jpg" />
					</div>
					<div>
						<img src="" />
					</div>
				</div>
			</div>
		</div>
		<div id="globleCtx">
			<div id="topics"
				style="height: 1020px; width: 75%; margin: auto; background-color: white;">
				<div v-if="isHave()">
					此处暂时没有任何人发帖，奉献你的第一次吧!&nbsp;<a v-bind:href="sendUrl" target="_blank">点击发帖</a>
				</div>
				<div v-for="top in topics" class="topic">
					<div id="imgHead"></div>
					<div id="tit">
						<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br />
					</div>
					<div id="time">
						<span style="color: gray;">{{top.sendTime}}</span>
					</div>
					<div id="userName">
						<a v-bind:href="'bigCow/user/getUserCus/'+top.user.id"
							target="_blank">{{top.user.nickName}}</a>
					</div>
					<div id="text">{{top.text | formatText}}</div>
					<div id="readed">
						<a href="javascript:void(0)"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green"></i>评论({{top.readNum}})</a>
						<a v-bind:href="'getTopic/'+top.id" target="_blank"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: gray"></i>阅读({{top.readNum}})</a>
					</div>
					<br> <br>
					<hr style="margin: 0px; margin-top: 50px;" />
				</div>
			</div>
			<div id="soPaging" style="text-align: center;"></div>
			<div id="bottomCtx"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
$(document).ready(function(){
	$(".topic").click(function(){
		var elem = $(this).children("div")[1]
		var href = $(elem).children("a").attr("href")
		window.open(href)
	})
})
</script>
	<script type="text/javascript">
	layui.use(['carousel', 'form','laypage','element'], function(){
		  var carousel = layui.carousel,
		  element = layui.element,
		  laypage = layui.laypage,
		  form = layui.form;
		  
		  carousel.render({
		    elem: '#imgs'
		    ,width: '75%'
		    ,height: '350px'
		    ,interval: 4000
		  });
		  
/* 		  carousel.render({
			    elem: '#ad'
			    ,width: '99%'
			    ,height: '260px'
			    ,interval: 4000
			  }); */
		  
		  laypage.render({
			    elem: 'soPaging'
			    ,count: app.count
			    ,limit: app.limit
			    ,theme: '#1E9FFF'
			    ,jump: function(obj, first){
			    	 $.post('bigCow/paging/find',{'nowPage':obj.curr,"sele":'${sele}'},function(data){
							app.topics=JSON.parse(data.data.topics)
						},'json')
			     }
			  });
		  
		});
</script>
	<script type="text/javascript">
		var app=new Vue({
			el:'#globleCtx',
			data:{
				topics: ${pages.pageJSON},
				count: ${pages.allNum},
				limit: ${pages.nowPageNum},
				sendUrl: ${user==null}?'bigCow/page/toGlobalLogin':'bigCow/page/toAdd'
			},
			methods:{
				isHave:function(){
					if(this.topics.length<=0){
						return true
					}else{
					return false
					}
				}
				
			},
			created: function(){
			},
			filters: {
				formatText(text){
					var reg=new RegExp(/\<p\>([\w\s\.\u4e00-\u9fa5]+)\<\/p\>/g)
					text=reg.exec(text)
					text=text[1].substring(0,155)
					text+=" ..."
					return text
				}
			}
		})
	</script>
</body>
</html>