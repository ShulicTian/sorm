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
			<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="manage" style="">
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">资料管理</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toUploadImg')">更改头像</a></dd>
			      <dd class="layui-this"><a href="javascript:;" v-on:click="toPage('bigCow/page/toUpMsg')">修改资料</a></dd>
			      <dd id="pwd"><a href="javascript:;" v-on:click="toPage('bigCow/page/toUpPwd')">更改密码</a></dd>
			      <dd><a href="javascript:;">我的消息</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toSetGit')">资源分享</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">文章管理</a>
			    <dl class="layui-nav-child">
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toSetOriginal')">我的原创</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toSetTransmit')">我的转发</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toSetTranslate')">我的译文</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toDraft')">草稿箱</a></dd>
			      <dd><a href="javascript:;" v-on:click="toPage('bigCow/page/toDustbin')">垃圾箱</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">粉丝&大神</a>
			    <dl class="layui-nav-child">
			      <dd id="fans"><a href="javascript:;" v-on:click="toPage('bigCow/page/getFans')">我的粉丝</a></dd>
			      <dd id="toAtt"><a href="javascript:;" v-on:click="toPage('bigCow/page/getAtt')">我的大神</a></dd>
			      <dd><a href="javascript:;">订阅管理</a></dd>
			    </dl>
			  </li>
			  <li class="layui-nav-item layui-nav-itemed">
			    <a href="javascript:;">管理员页面</a>
			    <dl class="layui-nav-child">
			      <dd v-if="${user.type==0}" id="apply"><a href="javascript:;" v-on:click="toPage('bigCow/page/toApplyAdmin')">申请管理员</a></dd>
			      <dd v-if="${user.type>0}" id="audio"><a href="javascript:;" v-on:click="toPage('bigCow/page/toAudit')">审核文章</a></dd>
			      <dd v-if="${user.type>0}"><a href="javascript:;" v-on:click="toPage('bigCow/page/toTopicManage')">文章管理</a></dd>
			      <dd v-if="${user.type>0}"><a href="javascript:;">处理申诉</a></dd>
			      <dd v-if="${user.type>0}"><a href="javascript:;" v-on:click="toPage('bigCow/page/toUserManage')">用户管理</a></dd>
			      <dd v-if="${user.type>0}"><a href="javascript:;" v-on:click="toPage('bigCow/page/toPunishManage')">封号管理</a></dd>
			      <dd v-if="${user.type==2}"><a href="javascript:;" v-on:click="toPage('bigCow/page/toAdminManage')">设置管理员</a></dd>
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
layui.use(['element'], function(){
	  var element = layui.element;
	  
	  element.on('nav(manage)', function(elem){
	  });
	  
});
</script>
<script type="text/javascript">
var app=new Vue({
		el:'#all',
		data:{
			
		},
		methods:{
			toPage:function(str){
				$("#content").attr("src",str)
			}
		},
		created:function(){
			if(${param.p==1}){
				$("dd").attr("class","")
				$("#apply").attr("class","layui-this")
				$("#content").attr("src",'bigCow/page/toApply')
			}else if(${param.p==2}){
				$("dd").attr("class","")
				$("#audio").attr("class","layui-this")
				$("#content").attr("src",'bigCow/page/toAudit')
			}else if(${param.p==3}){
				$("dd").attr("class","")
				$("#fans").attr("class","layui-this")
				$("#content").attr("src",'bigCow/page/getFans')
			}else if(${param.p==4}){
				$("dd").attr("class","")
				$("#toAtt").attr("class","layui-this")
				$("#content").attr("src",'bigCow/page/getAtt')
			}else if(${param.p==5}){
				$("dd").attr("class","")
				$("#pwd").attr("class","layui-this")
				$("#content").attr("src",'bigCow/page/toUpPwd')
			}else{
				$("#content").attr("src",'bigCow/page/toUpMsg')
			}
		}
	})
</script>
</body>
</html>