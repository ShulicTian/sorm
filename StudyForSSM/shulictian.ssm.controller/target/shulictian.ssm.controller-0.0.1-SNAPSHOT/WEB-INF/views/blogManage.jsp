<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shulictian.ssm.po.*"%>
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
<script type="text/javascript">
	$(document).ready(function(){
		if(${user==null}){
			alert("请登入！");
			$(location).attr('href', 'logreg');
		}
	});

</script>
<style type="text/css">
body{overflow:scroll;overflow-x:hidden;}
#gps dl{margin:0px;}
#gps dd{margin:0px;}
</style>
</head>
<body>
	<div id="all">
		<jsp:include page="head.jsp"></jsp:include>
		<div id="gps" style="overflow:hidden;width:200px;height:943px;background-color:#23262E;float:left;">
			<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="">
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">资料管理</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;" v-on:click="toPage('toUpMsg')">修改资料</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toPwd')">更改密码</a></dd>
			      <dd><a href="javascript:;">我的消息</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toGit')">资源分享</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">文章管理</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;" v-on:click="toPage('toZero')">我的原创</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toOne')">我的转发</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toTow')">我的译文</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toDraft')">草稿箱</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toDustbin')">垃圾箱</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">粉丝&大神</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;" v-on:click="toPage('getFans')">我的粉丝</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('getAtt')">我的大神</a></dd>
			      <dd><a href="javascript:;">订阅管理</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item">
			    <a href="javascript:;">管理员页面</a>
			    <dl class="layui-nav-child">
			      <dd v-if="${user.type==0}"><a href="javascript:;" v-on:click="toPage('toApply')">申请管理员</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toAudit')">审核文章</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('toManageTop')">文章管理</a></dd>
			      <dd><a href="javascript:;">处理申诉</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('getUserAdmin')">用户管理</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('getPunish')">封号管理</a></dd>
			      <dd v-if="${user.type==2}"><a href="javascript:;" v-on:click="toPage('goSuperAdmin')">设置管理员</a></dd>
			    </dl>
			  </li>
			</ul>
		</div>
		<iframe id="content" scrolling="no" frameborder="0" src="" style="width:89%;float:right;">
		</iframe>
		<script type="text/javascript">
		function reinitIframe(){
		var iframe = document.getElementById("content");
		try{
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		iframe.height = height;
		console.log(height);
		}catch (ex){}
		}
		window.setInterval("reinitIframe()", 200);
		</script>
		<div style="clear:both;"></div>
	</div>
	
<script type="text/javascript">
layui.use('element', function(){
	  var element = layui.element;
	  
	  element.on('nav(demo)', function(elem){
	    layer.msg(elem.text());
	  });
});
</script>
<script type="text/javascript">
	new Vue({
		el:'#all',
		data:{
			
		},
		methods:{
			toPage:function(str){
				$("#content").attr("src",str)
			}
		}
	})
</script>
</body>
</html>