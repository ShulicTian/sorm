<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<jsp:include page="resources.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript">
$(document).ready(function(){
	$(".topType").mouseover(function(){
		$(this).css("background-color","#20B2AA")
		$(this).children("ul").show();
	});
 	$(".topType").mouseout(function(){
		$(this).css("background","none")
		$(this).children("ul").hide();
	});
 	$(".par:last").children("li").mouseover(function(){
 		if($(this).nextAll().length*$(this).height()<$(this).children("ul").height()){
			var a=$(this).children("ul").height();
			$(this).children("ul").css("margin-top",-(a+30));
		}
 	})
});
function getUserName(value,row){	
	return value.userName;
}
</script>
<style type="text/css">
body{margin:0px;padding:0px;overflow-x:hidden;}
</style>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;border:1px solid;margin:auto;">
		<div data-options="region:'center',border:false">
		
		<jsp:include page="head.jsp"></jsp:include>
		
			<div style="width:100%;height:1430px;" id="globleCtx">
			
				<div id="menus">
						<h4>AAA</h4>
						<div id="menuList" style="padding:0px;">
							<ul class="par" v-for="h in dataHead">
									<h6>{{h.title}}</h6>
									<li class="topType" border="false" v-for="name in h.names">
										<a class="tabBtn" data-type="tabAdd" href="javascript:void(0)" v-bind:id="name.type">{{name.name}}</a>
									</li>
							</ul>
						</div>
				</div>
				
				<div id="ctx" lay-filter="tabs" class="layui-tab layui-tab-brief">
					<ul class="layui-tab-title" style="margin:0px;" id="tabF">
					    <li class="layui-this">首页</li>
					</ul>
					
					<div id="context" class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<div class="layui-carousel" id="imgs" style="margin:auto;box-shadow:0px 0px 5px black;">
							  <div carousel-item="">
							    <div><img src="img/logo.jpg" /></div>
							    <div><img src="img/back.jpg" /></div>
							    <div><img src="" /></div>
							  </div>
							</div>
						</div>
							<div id="topics">
								<div v-if="isHave()">此处暂时没有任何人发帖，奉献你的第一次吧!&nbsp;<a v-bind:href="sendUrl" target="_blank">点击发帖</a></div>
								<div v-for="top in topics" class="topic">
									<div id="imgHead"></div>
									<div id="tit"><a v-bind:href="'getTopic/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br/></div>
									<div id="time"><span style="color:gray;">{{top.sendTime}}</span></div>
									<div id="userName"><a v-bind:href="'getUserCus/'+top.user.id" target="_blank">{{top.user.nickName}}</a></div>
									<div id="text">{{top.text | formatText}}</div>
									<div id="readed">
										<a href="javascript:void(0)" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:green"></i>评论({{top.readNum}})</a>
										<a v-bind:href="'getTopic/'+top.id" target="_blank" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:gray"></i>阅读({{top.readNum}})</a>
									</div>
									<br><br><hr style="margin:0px;margin-top:50px;"/>
								</div>
							</div>
							<div id="pages">
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
				
				<div id="rightCtx">
					<h4 style="">AAA</h4>
					<div class="layui-carousel" id="ad" style="margin:auto;">
					  <div carousel-item="">
					    <div><img src="img/logo.jpg" /></div>
					    <div><img src="img/back.jpg" /></div>
					    <div><img src="" /></div>
					  </div>
					</div>
					<div id="topList">
						<h6>今日排名</h6>
						<div class="topTit">
							<div v-if="">暂时没有帖子</div>
							<!-- <p v-for=""><a v-bind:href="'getTopic/'">{{}}</a></p> -->
						</div>
						<h6>今日佳作</h6>
						<div class="topTit">
							<div v-if="jud(todayHot)">暂时没有帖子</div>
							<p v-for="t1 in todayHot"><a v-bind:href="'getTopic/'+t1.id">{{t1.title}}</a></p>
						</div>
						<h6>今日推荐</h6>
						<div class="topTit">
							<div v-if="jud(todayNew)">暂时没有帖子</div>
							<p v-for="t2 in todayNew"><a v-bind:href="'getTopic/'+t2.id">{{t2.title}}</a></p>
						</div>
					</div>
				</div>
			</div>
			<div style="width:100%;height:100px;border:1px solid red;float:left;">
			</div>
		</div>
	</div>
<script type="text/javascript">

	layui.use('element', function(){
	  var $ = layui.jquery
	  element = layui.element;
	  
	  var active={
			  tabAdd:function(){
				  var titles=[];
					  $("#tabF li").each(function(){
						  titles.push($(this).html())
					  });
					  if($.inArray($(this).html(),titles)!=-1){
						  element.tabChange('tabs',$(this).attr("id"));
					  }else{
						  element.tabAdd('tabs', {
						        title: $(this).html(),
						        id: $(this).attr("id")
						      });
						  element.tabChange('tabs',$(this).attr("id"));
					  }
			  }
	  }
	  
	  $('.tabBtn').on('click', function(){
		    var othis = $(this);
		    var type = othis.data('type');
		    active[type] ? active[type].call(this, othis) : '';
		  });
	  element.on('tab(tabs)', function(data){
		  if(this.attributes[0].value==="layui-this"){
			  app.firstPage()
		  }else{
			  app.changeTop(this.attributes[0].value)
		  }
		});
	  
	});
	
	layui.use(['carousel'], function(){
		  var carousel = layui.carousel
		  carousel.render({
		    elem: '#imgs'
		    ,width: '99%'
		    ,height: '260px'
		    ,interval: 4000
		  });
		  carousel.render({
			    elem: '#ad'
			    ,width: '99%'
			    ,height: '260px'
			    ,interval: 4000
			  });
		});
</script>
<script type="text/javascript">
var app=new Vue({
			el: '#globleCtx',
			data: {
				topics: ${pages.pageTops},
				pagesNum: ${pages.pageNum},
				pageNum: ${pages.pageNum}>5?5:${pages.pageNum},
				nowPage: 1,
				start: 1,
				end: 5,
				url: 'page',
				sendUrl: ${user==null}?'logreg':'toadd',
				dataHead: [
					{title:'前端技术',names:[
						{name:'Vue.js',type:0},
						{name:'Node.js',type:1},
						{name:'JavaScript',type:2},
						{name:'Ajax',type:3},
						{name:'JQuery',type:4},
						{name:'JQueryUI',type:5},
						{name:'EasyUI',type:6},
						{name:'LayUI',type:7},
						{name:'BootStrap',type:8}
						]},
					{title:'编程语言',names:[
						{name:'C/C++',type:9},
						{name:'Java',type:10},
						{name:'Java框架',type:11}
						]},
					{title:'编程工具',names:[
						{name:'Maven',type:12},
						{name:'Svn/Git',type:13},
						{name:'Solr',type:14},
						{name:'Navicat for Mysql',type:15},
						{name:'Eclipse',type:16},
						{name:'IntelliJ IDEA',type:17},
						{name:'WebStorm',type:18}
					]},
					{title:'其他技术',names:[
						{name:'Mysql',type:19},
						{name:'Tomcat',type:20},
						{name:'Weblogic',type:21},
						{name:'Servlet/JSP',type:22},
						{name:'Log4j',type:23},
						{name:'Nginx',type:24},
						{name:'redis',type:25},
						{name:'shiro',type:26}
					]}],
					todayHot:[],
					todayRanking:[],
					todayNew:[]
			},
			methods: {
				getPage: function(nowPage){
					if(nowPage>=1 && nowPage<=this.pagesNum){
						this.nowPage=nowPage
						
						if(this.pagesNum>5){
							this.judPage(nowPage)
						}
						
						this.disabled()
						var temp=this
					 	$.post(this.url,{"nowPage":nowPage},function(data){
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
				changeTop: function(type){
					var temp=this
					$.post("getType",{"type":type},function(data){
						temp.topics=JSON.parse(data.pageTops)
						temp.pagesNum=data.pageNum
						temp.pageNum=data.pageNum>5?5:data.pageNum
						temp.nowPage=1
						temp.start=1
						temp.end=5
						temp.url='typePage'
					},"json")
				},firstPage: function(){
					var temp=this
					$.post("first",function(data){
						temp.topics=data
						temp.pagesNum=data.pageNum
						temp.pageNum=data.pageNum>5?5:data.pageNum
						temp.nowPage=1
						temp.start=1
						temp.end=5
					},"json")
				},
				isHave:function(){
					if(this.topics.length<=0){
						return true
					}else{
					return false
					}
				},
				getUserTops: function(){
					var temp=this;
										
					$.post("newAllTop",function(data){
						temp.todayNew=data
					},'json')
					
					$.post("allTopHot",function(data){
						temp.todayHot=data
					},'json')
					
					/* $.post("todayRanking",function(data){
						temp.todayRanking=data
					},'json') */
				},
				jud:function(data){
					if(data.length<=0){
						return true
					}
					return false
				}
			},
			created: function(){
				this.getUserTops()
				this.disabled()
			},
			filters: {
				formatText(text){
					if(text!=null){
						var reg=new RegExp(/\<p\>([\w\s\.\u4e00-\u9fa5]+)\<\/p\>/g)
						text=reg.exec(text)
						text=text[1].substring(0,155)
						text+=" ..."
					}
					return text
				}
			}
		})
</script>
</body>
</html>
