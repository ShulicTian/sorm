<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="shulictian.ssm.po.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
#sdf {
	width: 600px;
	border: none;
	color: gray;
	background-color: #f5f6f7;
}
</style>
</head>
<body>
	<div id="content">
		<div>
			<jsp:include page="head.jsp"></jsp:include>
		</div>
		<div id="userTit">
			<h2>我的个人主页</h2>
			<p>
				<input id="sdf" type="text" v-model="sdf" @click="upSdf"
					@blur="saveSdf" placeholder="您还没有设置签名,点击此行进行编辑。"
					class="layui-input" />
			</p>
		</div>
		<div style="width: 100%;">
			<div style="width: 100%; margin: auto;">
				<div id="rContent">
					<div>
						<div id="photo">
							<img src="img/user/${userCus.id}.jpg" />
						</div>
						<div id="name">
							<a id="userName2" href="javascript:void(0)">${userCus.nickName}</a>
						</div>
						<div id="orderMsg">
							<div style="float: left;">
								<a :href="toFans(3)">粉丝</a>
							</div>
							<div style="float: right;">
								<a :href="toFans(4)">大神</a>
							</div>
							<br />
							<div style="float: left;">{{fans}}</div>
							<div style="float: right;">{{attention}}</div>
						</div>
						<div style="width: 100%; text-align: center;">
							<hr style="margin: 0px;" />
							<div style="" id="userMsg">
								<div style="text-align: right;">
									<span>原创:</span><br />
								</div>
								<div style="text-align: left;">
									<span>{{original}}</span><br />
								</div>
								<div style="text-align: right;">
									<span>访问量:</span><br />
								</div>
								<div style="text-align: left;">
									<span>{{look}}</span><br />
								</div>
							</div>
							<hr style="margin: 0px;" />
						</div>
					</div>
					<div style="height: 200px;">
						<h6 style="width: 100%; text-align: center; padding-top: 10px;">公告栏</h6>
						<p
							style="text-align: left; width: 190px; margin: auto; font-size: 14px; line-height: 25px; font-family: STHeiTi; letter-spacing: 3px; color: black;">测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏测试公告栏</p>
					</div>
					<div style="margin-top: 10px;">
						<div id="gitTit">GitHub分享站</div>
						<hr style="margin: 0px;" />
						<div id="gitText">
							<h6 style="margin-top: 10px;">主页网址：</h6>
							<p>
								<a :href="git" target="_blank">{{git}}</a>
							</p>
							<hr />
							<div v-for="pro in pros">
								<h6>项目名称：{{pro.name}}</h6>
								<h6>项目简介：</h6>
								<p>{{pro.descs}}</p>
								<h6>
									项目路径：<a :href="git+pro.address" target="_blank">{{pro.address}}</a>
								</h6>
								<hr />
							</div>
						</div>
					</div>
				</div>

				<div lay-filter="tabs" class="layui-tab layui-tab-brief">
					<div id="topics"
						style="width: 60%; background-color: white; float: right; height: 1075px;">
						<ul class="layui-tab-title" id="tabF">
							<li class="layui-this"
								@click="getUserTops('bigCow/topic/getByUserId')">首页</li>
							<li class=""
								@click="getUserTops('bigCow/topic/getOriginalByUserId')">原创</li>
							<li class=""
								@click="getUserTops('bigCow/topic/getTransmitByUserId')">转发</li>
							<li class=""
								@click="getUserTops('bigCow/topic/getTranslateByUserId')">译文</li>
						</ul>
						<div id="localSo" style="">
							<input type="hidden" name="userId" value="${userCus.id}" /> <input
								id="findLocal" type="submit" value="本页搜索" @click="homeFind"
								style="float: right; margin-right: 20px;"
								class="layui-btn layui-btn-primary layui-btn-sm" /> <input
								type="text" id="inp" name="ctx" placeholder="请输入关键字"
								style="float: right; margin-right: 5px; width: 110px; height: 30px;"
								class="layui-input" />
						</div>
						<div style="clear: both;"></div>
						<div v-if="isHave()">此博主暂时没有发帖!&nbsp;</div>
						<div v-for="top in topics" class="topic"
							@click="toPage('bigCow/topic/get/'+top.id)">
							<div id="imgHead" style="padding-left: 30px;">
								<img :src="'img/topic/'+top.id+'.jpg'" />
							</div>
							<div id="tit" style="width: 59%; padding-left: 18px;">
								<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br />
							</div>
							<div id="time">
								<span style="color: gray;">{{top.sendTime | formatDate}}</span>
							</div>
							<div id="userName" style="float: left;">
								<a href="javascript:void(0)">{{top.user.nickName}}</a>
							</div>
							<div id="text" style="padding-left: 20px; font-size: 12px;">{{top.text
								| formatText}}</div>
							<div id="readed">
								<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"
									class="layui-btn layui-btn-normal layui-btn-sm"><i
									class="layui-icon" style="color: green"></i>评论({{top.readNum}})</a>
								<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"
									class="layui-btn layui-btn-normal layui-btn-sm"><i
									class="layui-icon" style="color: gray"></i>阅读({{top.readNum}})</a>
							</div>
							<br> <br>
							<hr style="margin: 0px; margin-left: 20px;" />
						</div>
					</div>
					<div id="homePaging"
						style="text-align: center; clear: both; margin-right: 120px;"></div>
					<div id="bottomCtx"></div>
				</div>

			</div>
		</div>
		<div style="clear: both;"></div>
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
/* $(document).ready(function(){
	$(".topic").click(function(){
		var elem = $(this).children("div")[1]
		var href = $(elem).children("a").attr("href")
		window.open(href)
	})
}) */
</script>
	<script type="text/javascript">

layui.use(['carousel', 'form','laydate','layedit','laypage'], function(){
	  var laydate = layui.laydate
	  ,layedit = layui.layedit,
	  carousel = layui.carousel,
	  laypage = layui.laypage,
	  form = layui.form;
	  
	  element.on('tab(tabs)', function(data){
		  if(this.attributes[0].value==="layui-this"){
			  laypage.render({
				    elem: 'homePaging'
				    ,count: app.count
				    ,limit: app.limit
				    ,theme: '#1E9FFF'
				    ,jump: function(obj, first){
				    	 $.post(app.url,{'nowPage':obj.curr,"userId":app.userid,"ctx":app.ctx,"genre":app.genre,"num":10},function(data){
								app.topics=JSON.parse(data.data.topics)
							},'json')
				     }
				  });
		  }else{
			  laypage.render({
				    elem: 'homePaging'
				    ,count: app.count
				    ,limit: app.limit
				    ,theme: '#1E9FFF'
				    ,jump: function(obj, first){
				    	 $.post(app.url,{'nowPage':obj.curr,"userId":app.userid,"ctx":app.ctx,"genre":app.genre,"num":10},function(data){
								app.topics=JSON.parse(data.data.topics)
							},'json')
				     }
				  });
		  }
		});
	  
	  $('#findLocal').on('click', function(){
		  laypage.render({
			    elem: 'homePaging'
			    ,count: app.count
			    ,limit: app.limit
			    ,theme: '#1E9FFF'
			    ,jump: function(obj, first){
			    	 $.post(app.url,{'nowPage':obj.curr,"userId":app.userid,"ctx":app.ctx,"genre":app.genre,"num":10},function(data){
							app.topics=JSON.parse(data.data.topics)
						},'json')
			     }
			  });
		  });
	  
	  laydate.render({
		    elem: '#dateView'
		    ,position: 'static'
		  });
	  
	  carousel.render({
		    elem: '#ad'
		    ,width: '99%'
		    ,height: '270px'
		    ,interval: 4000
		  });
	  
	  laypage.render({
		    elem: 'homePaging'
		    ,count: app.count
		    ,limit: app.limit
		    ,theme: '#1E9FFF'
		    ,jump: function(obj, first){
		    	 $.post(app.url,{'nowPage':obj.curr,"userId":app.userid,"ctx":app.ctx,"genre":app.genre,"num":10},function(data){
						app.topics=JSON.parse(data.data.topics)
					},'json')
		     }
		  });
	  
	});

</script>
	<script type="text/javascript">

var app=new Vue({
	el: '#content',
	data: {
		original: ${userCus.zeroNum},
		look: ${userCus.lookNum},
		fans: ${userCus.fansNum},
		attention: ${userCus.atteNum},
		allTopHot: [],
		relatedTop: [],
		newTypeTop: [],
		hotTop: [],
		newTop: [],
		userid: ${userCus.id},
		topics: [],
		count: "",
		limit: "",
		ctx: '',
		genre: "",
		sdf: '',
		pros: [],
		prosById: [],
		git:'https://www.github.com/',
		url: 'bigCow/paging/userTopic'
	},
	methods: {
		getUserTops: function(url){
			var temp=this;
			temp.url=url=="bigCow/topic/getByUserId"?"bigCow/paging/userTopic":"bigCow/paging/genreTopic"
			temp.genre= url=="bigCow/topic/getOriginalByUserId"?0:url=="bigCow/topic/getTransmitByUserId"?1:2
		    $.ajax({ 
	              type : "post", 
	              url : url, 
	              data : {"userId":this.userid}, 
	              async : false, 
	              dataType: 'json',
	              success : function(data){ 
	  				temp.topics=JSON.parse(data.data.topics.pageJSON)
					temp.count=data.data.topics.allNum
					temp.limit=data.data.topics.nowPageNum
	              } 
	         }); 
		},
		jud: function(top){
			if(top.length<=0){
				return true
			}
			return false
		},
		isHave:function(){
			if(this.topics.length<=0){
				return true
			}else{
				return false
			}
		},
		upSdf:function(){
			$("#sdf").css({"border":"1px solid"})
		},
		saveSdf:function(){
			var descs=this.sdf
			$("#sdf").css({"border":"none"})
			$.post("bigCow/other/upSdf",{"userId":temp.userid,"descs":descs})
		},
		getSdf:function(){
			var temp=this
			$.post("bigCow/other/getSdf",{"userId":temp.userid},function(data){
				temp.sdf=data
			})
		},
		getPros:function(){
			var temp=this
			$.post("bigCow/other/getPro",{"userId":temp.userid},function(data){
				temp.pros=data
			},"json")
		},
		getGit:function(){
			var temp=this
			$.post("bigCow/other/getGit",{"userId":temp.userid},function(data){
				temp.git+=data
			})
		},
		toFans:function(num){
			return "bigCow/page/toBlog?p="+num
		},
		homeFind:function(){
			var userId = ${userCus.id}
			var temp = this
			temp.ctx = $("#inp").val()
			temp.url="bigCow/paging/homeFind"
		    $.ajax({ 
	              type : "post", 
	              url : "bigCow/topic/findFromHome", 
	              data : {"userId":temp.userid,"ctx":temp.ctx}, 
	              async : false, 
	              dataType: 'json',
	              success : function(data){ 
	  				temp.topics=JSON.parse(data.data.topics.pageJSON)
					temp.count=data.data.topics.allNum
					temp.limit=data.data.topics.nowPageNum
	              } 
	         }); 
		},
		toPage:function(href){
			/* window.open(href) */
		}
	},
	created: function(){
		this.getPros()
		this.getGit()
		this.getUserTops('bigCow/topic/getByUserId')
		this.getSdf()
	},
	filters: {
		formatText(text){
			var reg=new RegExp(/\<p\>([\w\s\.\u4e00-\u9fa5]+)\<\/p\>/g)
			text=reg.exec(text)
			text=text[1].substring(0,155)
			text+=" ..."
			return text
		},
		formatDate: function(date){
			date = new Date(date)
			var m=(date.getMinutes())<9?'0'+(date.getMinutes()):(date.getMinutes())
			return date=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+m
		}
	}
	
})

</script>
</body>
</html>