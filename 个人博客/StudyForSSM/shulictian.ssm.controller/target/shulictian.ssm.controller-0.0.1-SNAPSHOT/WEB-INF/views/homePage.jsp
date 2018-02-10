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
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/homePage.css">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>
	<div id="content">
		<div>
			<jsp:include page="head.jsp"></jsp:include>
		</div>
		<div id="userTit">
			<h2>${userCus.nickName} 的个人主页</h2>
			<p>只要功夫深，铁杵磨成针</p>
		</div>
		<div style="width:100%;">
			<div style="width:100%;margin:auto;">
				<div id="rContent">
					<div>
						<div id="photo"><img src="img/${userCus.nickName}.jpg"/></div>
						<div id="name"><a id="userName2" href="javascript:void(0)">${userCus.nickName}</a></div>
						<div id="orderMsg">
							<div style="float:left;"><a>粉丝</a></div><div style="float:right;">大神</div><br/>
							<div style="float:left;">{{original}}</div><div style="float:right;">{{original}}</div>
						</div>
						<div style="width:100%;text-align:center;">
							<hr style="margin:0px;"/>
							<div style="" id="userMsg">
								<div style="text-align:right;">
									<span>原创:</span><br/>
								</div>
								<div style="text-align:left;">
									<span>{{reprint}}</span><br/>
								</div>
								<div style="text-align:right;">
									<span>访问量:</span><br/>
								</div>
								<div style="text-align:left;">
									<span>{{reprint}}</span><br/>
								</div>
							</div>
							<hr style="margin:0px;"/>
						</div>
					</div>
					<div style="height:200px;border:1px solid;">
					
					</div>
					<div style="margin-top:10px;">
						<div id="gitTit">GitHub分享站</div>
						<hr style="margin:0px;"/>
						<div id="gitText">
							<h6 style="margin-top:10px;">主页网址：</h6>
							<p><a href="javascript:void(0);">https://github.com/shulictian/</a></p>
							<hr/>
							<div v-for="">
								<h6 style="text-align:center;margin:10px 0px;">项目名称</h6>
								<h6>项目简介：</h6>
								<p>web商城项目，以SSM基础构建，用到的其他技术又jquery/easyui/vue.js等等等</p>
								<h6>项目路径：</h6>
								<p><a href="javascript:void(0);">WebShop/</a></p>
								<hr/>
								<h6 style="text-align:center;margin:10px 0px;">项目名称</h6>
								<h6>项目简介：</h6>
								<p>web商城项目，以SSM基础构建，用到的其他技术又jquery/easyui/vue.js等等等</p>
								<h6>项目路径：</h6>
								<p><a href="javascript:void(0);">WebShop/</a></p>
								<hr/>
								<h6 style="text-align:center;margin:10px 0px;">项目名称</h6>
								<h6>项目简介：</h6>
								<p>web商城项目，以SSM基础构建，用到的其他技术又jquery/easyui/vue.js等等等</p>
								<h6>项目路径：</h6>
								<p><a href="javascript:void(0);">WebShop/</a></p>
								<hr/>
								<h6 style="text-align:center;margin:10px 0px;">项目名称</h6>
								<h6>项目简介：</h6>
								<p>web商城项目，以SSM基础构建，用到的其他技术又jquery/easyui/vue.js等等等</p>
								<h6>项目路径：</h6>
								<p><a href="javascript:void(0);">WebShop/</a></p>
								<hr/>
							</div>
						</div>
					</div>
				</div>
				
				<div lay-filter="tabs" class="layui-tab layui-tab-brief">
					<div id="topics" style="width:60%;background-color:white;float:right;height:1075px;">
						<ul class="layui-tab-title" id="tabF">
							<li class="layui-this">首页</li>
							<li class="">原创</li>
							<li class="">转发</li>
							<li class="">译文</li>
						</ul>
						<div id="localSo" style="">
							<form class="layui-form">
								<input type="submit" value="本页搜索" style="float:right;margin-right:20px;" class="layui-btn layui-btn-primary layui-btn-sm"/>
								<input type="text" id="inp" name="" placeholder="请输入关键字" style="float:right;margin-right:5px;width:110px;height:30px;" class="layui-input"/>
							</form>
						</div>
						<div style="clear:both;"></div>
						<div v-if="isHave()">此博主暂时没有发帖!&nbsp;</div>
						<div v-for="top in topics" class="topic">
							<div id="imgHead" style="margin-left:20px;"></div>
							<div id="tit" style="width:60%;"><a v-bind:href="'getTopic/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br/></div>
							<div id="time"><span style="color:gray;">{{top.sendTime}}</span></div>
							<div id="userName"><a href="javascript:void(0)">{{top.user.nickName}}</a></div>
							<div id="text">{{top.text | formatText}}</div>
							<div id="readed">
								<a href="javascript:void(0)" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:green"></i>评论({{top.readNum}})</a>
								<a v-bind:href="'getTopic/'+top.id" target="_blank" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:gray"></i>阅读({{top.readNum}})</a>
							</div>
							<br><br><hr style="margin:0px;margin-top:50px;margin-left:20px;"/>
						</div>
					</div>
					<div id="pages" style="padding-right:100px;padding-top:15px;">
						<div style="width:300px;height:27px;margin:0 auto;overflow:hidden;">
							<a href="javascript:void(0)" v-on:click="getPage(1)" class="easyui-linkbutton">首页</a>
							<a href="javascript:void(0)" id="start" v-on:click="getPage(nowPage-1)" class="easyui-linkbutton" style="margin:0px 3px;">上一页</a>
							<a v-for="p in pageNum" :class="{'page':setStyle(start+p-1)}" style="width:20px;display:block;text-align:center;margin-top:2px;">{{start+p-1}}</a>
							<a href="javascript:void(0)" id="end" v-on:click="getPage(nowPage+1)" class="easyui-linkbutton" style="margin:0px 3px;">下一页</a>
							<a href="javascript:void(0)" v-on:click="getPage(pagesNum)" class="easyui-linkbutton">尾页</a>
						</div>																																																																																							
					</div>
					<div id="bottomCtx">
					</div>
				</div>
				
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#inp").focus(function(){
		$("#inp").animate({width:'200px'});
		});
	$("#inp").focusout(function(){
		$("#inp").animate({width:'110px'});
		});
	});
</script>
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  laydate.render({
	    elem: '#dateView'
	    ,position: 'static'
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
		original: ${userCus.zeroNum},
		reprint: ${userCus.oneNum},
		comments: 10000,
		allTopHot: [],
		relatedTop: [],
		newTypeTop: [],
		hotTop: [],
		newTop: [],
		userid: ${userCus.id},
		topics: [],
		pagesNum: 0,
		pageNum: 0,
		nowPage: 1,
		start: 1,
		end: 5
	},
	methods: {
		getUserTops: function(){
			var temp=this;
			$.post("getUserTop",{"id":this.userid},function(data){
				temp.topics=JSON.parse(data.pageTops)
				temp.pagesNum=data.pageNum
				temp.pageNum=data.pageNum>5?5:data.pageNum
			},'json')
		},
		jud: function(top){
			if(top.length<=0){
				return true
			}
			return false
		},
		getPage: function(nowPage){
			if(nowPage>=1 && nowPage<=this.pagesNum){
				this.nowPage=nowPage
				
				if(this.pagesNum>5){
					this.judPage(nowPage)
				}
				
				this.disabled()
				var temp=this
			 	$.post("getUserTopPage",{"nowPage":nowPage},function(data){
			 		temp.topics=data
				},"json")
			}
		},
		disabled: function(){
			if(this.nowPage==1){
				$("#start").css("color","#ADADAD");
			}else{
				$("#start").css("color","#007bff");
			}
			if(this.nowPage==this.pagesNum){
				$("#end").css("color","#ADADAD");
			}else{
				$("#end").css("color","#007bff");
			}
		},
		setStyle: function(page){
			return this.nowPage==page?true:false
		},
		judPage: function(nowPage){
			if(nowPage>this.end){
				if(nowPage>=this.pagesNum-4){
					this.start=this.pagesNum-this.pagesNum%5+1
					this.pageNum=this.pagesNum%5
					this.end=this.pagesNum
				}else{
					this.pageNum=5
					this.start=this.end+1
					this.end=nowPage+4
				}
			}
			if(nowPage<this.start){
				this.pageNum=5
				if(nowPage-4<=0){
					this.start=1
					this.end=5
				}else{
					this.start=nowPage-4
					this.end=nowPage
				}
			}
		},
		isHave:function(){
			if(this.topics.length<=0){
				return true
			}else{
			return false
			}
		}
	},
	created: function(){
		this.disabled()
		this.getUserTops()
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