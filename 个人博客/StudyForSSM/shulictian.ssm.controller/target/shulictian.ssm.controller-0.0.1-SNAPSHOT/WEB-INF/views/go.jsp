<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shulictian.ssm.po.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/go.css">
<title>Insert title here</title>
</head>
<body>
	<div id="content">
		<div>
			<jsp:include page="head.jsp"></jsp:include>
		</div>
		
		<div style="width:100%;">
			<div style="width:5%;height:100px;float:left;"></div>
			<div id="rContent">
				<div>
					<div id="userMsgs" style="background-color:#393D49;">个人资料</div>
					<div id="photo"><img src="img/${top.userCus.nickName}.jpg"/></div>
					<div id="name"><a id="userName" v-bind:href="'logreg':'getUserCus/'+${top.userCus.id}">${top.userCus.nickName}</a></div>
					<div style="width:100%;text-align:center;">
						<div class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon"></i>关注</div>
						<div class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon"></i>订阅</div>
						<hr style="margin-bottom:0px;"/>
						<div id="userMsg">
							<div style="padding:5px;margin-left:20px;float:left;">
								<span>原创：{{original}} 篇</span><br/>
								<span>译文：{{translation}} 篇</span>
							</div>
							<div style="padding:5px 5px;float:right;">
								<span>转载：{{reprint}} 篇</span><br/>
								<span>评论：{{comments}} 条</span>
							</div>
						</div>
						<hr>
					</div>
				</div>
				
				<div>
					<div id="gitTit" style="background-color:#393D49;">GitHub分享站</div>
					<div id="gitText">
						<h6 style="margin-top:10px;">主页网址：</h6>
						<p><a href="javascript:void(0);">https://github.com/shulictian/</a></p>
						<hr/>
						<div v-for="">
							<h6>项目简介：</h6>
							<p>web商城项目，以SSM基础构建，用到的其他技术又jquery/easyui/vue.js等等等</p>
							<h6>项目路径：</h6>
							<p><a href="javascript:void(0);">WebShop/</a></p>
							<hr/>
						</div>
					</div>
				</div>
			</div>
			
			<div id="text">
				<div id="paper">
					<h3 style="padding:30px;padding-bottom:0px">${top.title}</h3>
					<div id="tit2" style="padding-left:40px;">
						<div class="layui-btn layui-btn-primary layui-btn-disabled" style="display:inline;">{{getGenreName()}}</div>
						<div style="display:inline;">&nbsp;<sub>{{sendTime | formatDate}}</sub></div>
						<div style="display:inline;float:right;padding:10px 10px 0 0;"><i id="btn" disabled="disabled" class="easyui-linkbutton" iconCls="icon-man"></i><sub>${top.readNum}</sub></div>
					</div>
					<hr/>
					<div id="ctx">${top.text}</div>
				</div>
				<c:choose>
					<c:when test="${user.id==top.userCus.id}">
						<!-- <a href="##" id="rem">删除</a> -->
					</c:when>
				</c:choose>
			</div>
			
			<div id="lContent">
				<div class="site-demo-laydate">
				  <div class="layui-inline" id="dateView"></div>
				</div>
				<div class="layui-carousel" id="ad" style="margin:auto;margin-top:5px;">
						  <div carousel-item="">
						    <div><img src="img/logo.jpg" /></div>
						    <div><img src="img/back.jpg" /></div>
						    <div><img src="" /></div>
						  </div>
				</div>
				<div id="topList">
					<h6>博主最新佳作</h6>
					<div class="topTit" style="">
						<div v-if="jud(newTop)">暂时没有帖子</div>
						<p v-for="t1 in newTop"><a v-bind:href="'getTopic/'+t1.id">{{t1.title}}</a></p>
					</div>
					<h6>博主最热佳作</h6>
					<div class="topTit" style="width:100%;text-align:left;padding:5px;">
						<div v-if="jud(hotTop)">暂时没有帖子</div>
						<p v-for="t2 in hotTop"><a v-bind:href="'getTopic/'+t2.id">{{t2.title}}</a></p>
					</div>
					<h6>Vue.js</h6>
					<div class="topTit" style="width:100%;text-align:left;padding:5px;">
						<div v-if="jud(newTypeTop)">暂时没有帖子</div>
						<p v-for="t3 in newTypeTop"><a v-bind:href="'getTopic/'+t3.id">{{t3.title}}</a></p>
					</div>
					<h6>相关推荐</h6>
					<div class="topTit" style="width:100%;text-align:left;padding:5px;">
						<div v-if="jud(relatedTop)">暂时没有帖子</div>
						<p v-for="t4 in relatedTop"><a v-bind:href="'getTopic/'+t4.id">{{t4.title}}</a></p>
					</div>
					<h6>其他热门</h6>
					<div class="topTit" style="width:100%;text-align:left;padding:5px;">
						<div v-if="jud(allTopHot)">暂时没有帖子</div>
						<p v-for="t5 in allTopHot"><a v-bind:href="'getTopic/'+t5.id">{{t5.title}}</a></p>
					</div>
				</div>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  laydate.render({
	    elem: '#dateView'
	    ,position: 'static'
	    ,showBottom: false
	    ,trigger: 'click'
	  });
});

layui.use(['carousel', 'form'], function(){
	  var carousel = layui.carousel
	  form = layui.form;
	  carousel.render({
		    elem: '#ad'
		    ,width: '99%'
		    ,height: '270px'
		    ,interval: 4000
		  });
	  
	});

</script>
<script type="text/javascript">

new Vue({
	el: '#content',
	data: {
		original: ${top.userCus.zeroNum},
		reprint: ${top.userCus.oneNum},
		translation: ${top.userCus.towNum},
		comments: 10000,
		allTopHot: [],
		relatedTop: [],
		newTypeTop: [],
		hotTop: [],
		newTop: [],
		userid: ${top.userCus.id},
		nowtype: ${top.type},
		nowGenre: ${top.genre},
		sendTime: "${top.sendTime}"
	},
	methods: {
		getUserTops: function(id,type){
			var temp=this;
			$.post("newTop",{'id':id},function(data){
				temp.newTop=data
			},'json')
			
			$.post("hotTop",{'id':id},function(data){
				temp.hotTop=data
			},'json')
			
			$.post("newTypeTop",{'type':type},function(data){
				temp.newTypeTop=data
			},'json')
			
			/* $.post("relatedTop",{'type':type-1},function(data){
				temp.relatedTop=data
			},'json') */
			
			$.post("allTopHot",function(data){
				temp.allTopHot=data
			},'json')
		},
		jud: function(top){
			if(top.length<=0){
				return true
			}
			return false
		},
		getGenreName: function(){
			var a = this.nowGenre
			if(a==0){
				return "原创"
			}else if(a==1){
				return "转载"
			}else if(a==2){
				return "译文"
			}else{
				return ""
			}
		}
	},
	created: function(){
		this.getUserTops(this.userid,this.nowtype)
	},
	filters: {
		formatDate: function(date){
			date = new Date(date)
			var m=(date.getMinutes()+1)<9?'0'+(date.getMinutes()+1):(date.getMinutes()+1)
			date=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()-1)+" "+(date.getHours()+10)+":"+m
			return date
		}
	}
	
})

</script>
</body>
</html>