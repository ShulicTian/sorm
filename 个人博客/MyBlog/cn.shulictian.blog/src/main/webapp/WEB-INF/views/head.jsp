<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="resources.jsp"/>
<style type="text/css">
</style>
</head>
<body>
	<div style="position:relative;">
			<h2 style="margin:0px;width:160px;padding:10px;font-family:fantasy;margin-left:20px;color:#393D49;float:left;"><b>@</b><a href="bigCow/page/" style="color:#393D49;">BigCow</a></h2>
			<div id="send" style="z-index:4;width:335px;height:60px;position:absolute;left:985px;top:20px;">
				<div style="width:65px;height:60px;float:left;border-radius:35px;background-color:gray;"></div>
				<div style="width:270px;height:40px;float:right;">
					<ul style="float:left;margin-top:5px;">
						<li style="float:left;border:1px solid;margin-left:15px;line-height:30px;width:65px;text-align:center;"><a href="bigCow/page/toAdd">写文章</a></li>
						<li style="float:left;border:1px solid;margin-left:15px;line-height:30px;width:65px;text-align:center;">写文章</li>
						<li style="float:left;border:1px solid;margin-left:15px;line-height:30px;width:65px;text-align:center;">写文章</li>
					</ul>
				</div>
			</div>
			<div style="width:100%;height:60px;background-color:#23262E;color:white;">
				<ul class="layui-nav selTab" style="width:920px;float:right;background-color:#23262E;margin:0px;z-index:5;">
					  <li class="layui-nav-item"><a href="bigCow/page/">首页</a></li>
					  <li class="layui-nav-item"><a v-bind:href="jud('bigCow/page/homePage')">个人主页</a></li>
					  <li class="layui-nav-item"><a v-bind:href="jud('bigCow/page/toBlog')">博客管理</a></li>
					  <li class="layui-nav-item">
					    <a href="javascript:;">账号管理</a>
					    <dl class="layui-nav-child">
					      <dd><a v-bind:href="jud('bigCow/page/toBlog')">修改资料</a></dd>
					      <dd><a v-bind:href="findPwd(5)">找回密码</a></dd>
					      <dd><a href="javascript:void(0)">封号申述</a></dd>
					    </dl>
					  </li>
					  
					  <li class="layui-nav-item">
					    <a href="javascript:;">管理员入口</a>
					    <dl class="layui-nav-child">
					      <dd><a :href="manage(2)">管理页面</a></dd>
					      <dd><a :href="apply(1)">申请管理员</a></dd>
					    </dl>
					  </li>
					  
					  <li class="layui-nav-item" v-if="${user!=null&&user!=''}" style="float:right;width:150px;">
					  	<a href="javascript:void(0)" style="padding-left:5px;text-align:right;">欢迎!${user.userName}</a>
					  	<dl class="layui-nav-child" style="text-align:center;">
					      <dd><a :href="jud('bigCow/page/homePage')">我的主页</a></dd>
					      <dd><a href="">消息</a></dd>
					      <dd><a href="">设置</a></dd>
					      <dd><a href="">反馈</a></dd>
					      <dd><a href="">帮助</a></dd>
					      <dd><a href="bigCow/user/logout">注销</a></dd>
					    </dl>
					  </li>
					  <li class="layui-nav-item" v-else style="float:right;width:75px;"><a href="bigCow/page/toGlobalLogin" style="padding-left:5px;">注册</a></li>
					  <li class="layui-nav-item" v-if="${user==null}" style="float:right;width:75px;"><a href="bigCow/page/toGlobalLogin" style="padding-right:5px;text-align:right;">登入</a></li>
					  
					  <div style="float:right;margin:20px 0px 0px 15px;">
					  	<form id="fo" action="bigCow/topic/soTop" method="post">
							<input id="textb" placeholder="请输入关键字" name="sele" class="layui-input" style="width:200px;color:white;height:25px;background-color:#23262E;border:none;box-shadow:0px 0px 2px white;display:inline;"/>
						  	<input class="layui-btn layui-btn-primary layui-btn-xs" type="submit" style="float:right;margin:0px 5px;width:35px;height:25px;background-color:#23262E;border:none;box-shadow:0px 0px 2px white;display:inline;color:white;" value="搜索"/>
					  	</form>
					  </div>
				</ul>
				
			</div>
		
		</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#send").mouseover(function(){
		$(this).attr("style","z-index:1;width:335px;height:60px;position:absolute;left:985px;top:60px;")
	})
	$("#send").mouseout(function(){
		$(this).attr("style","z-index:1;width:335px;height:60px;position:absolute;left:985px;top:20px;")
	})
})

</script>
		<script type="text/javascript">
		layui.use(['element','jquery'], function(){
			  var $ = layui.jquery
			  element = layui.element;
			  });
		</script>
		<script type="text/javascript">
			new Vue({
				el:'.selTab',
				data: {
				},
				methods:{
					sub:function(){
						$("#fo").submit();
					},
					jud:function(url){
						if(${user!=null}){
							return url
						}
						return "bigCow/page/toGlobalLogin"
					},
					apply:function(num){
						if(${user!=null}){
							if(${user.type=='0'}){
								return "bigCow/page/toBlog?p="+num
							}else{
								return "bigCow/page/toBlog"
							}
							
						}else{
							return "bigCow/page/toGlobalLogin"
						}
					},
					manage:function(num){
						if(${user!=null}){
							if(${user.type>0}){
								return "bigCow/page/toBlog?p="+num
							}
							return "bigCow/page/toBlog"
						}
						return "bigCow/page/toGlobalLogin"
					},
					findPwd:function(num){
						if(${user!=null}){
							if(${user.type>0}){
								return "bigCow/page/toBlog?p="+num
							}
							return "bigCow/page/toBlog"
						}
						return "bigCow/page/toGlobalLogin"
					}
				}
			})
		</script>
</body>
</html>