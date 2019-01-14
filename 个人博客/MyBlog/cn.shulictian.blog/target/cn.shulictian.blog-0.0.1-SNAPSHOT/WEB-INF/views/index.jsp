<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	overflow-x: hidden;
}

#bottomCtx {
	line-height: 60px;
	color: gray;
}

#bottomCtx ul li {
	float: left;
	text-align: center;
	margin-left: 10px;
}
</style>
</head>
<body>
	<div class="easyui-layout"
		style="width: 100%; height: 100%; margin: auto;">
		<div data-options="region:'center',border:false">

			<jsp:include page="head.jsp"></jsp:include>

			<div style="width: 100%; height: 1450px;" id="globleCtx">

				<div id="menus">
					<h4 style="font-family: cursive;">大牛博客站</h4>
					<div id="menuList" style="padding: 0px;">
						<ul class="par" v-for="h in dataHead">
							<h6>{{h.title}}</h6>
							<li class="topType" border="false" v-for="name in h.names">
								<a class="tabBtn" data-type="tabAdd" href="javascript:void(0)"
								v-bind:id="name.type">{{name.name}}</a>
							</li>
						</ul>
					</div>
				</div>

				<div id="ctx" lay-filter="tabs" class="layui-tab layui-tab-brief">
					<ul class="layui-tab-title" style="margin: 0px;" id="tabF">
						<li class="layui-this">首页</li>
					</ul>

					<div id="context" class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<div class="layui-carousel" id="imgs"
								style="margin: auto; box-shadow: 0px 0px 5px black;">
								<div carousel-item="">
									<div>
										<img src="img/advertising/index/5.jpg" />
									</div>
									<div>
										<img src="img/advertising/index/2.jpg" />
									</div>
									<div>
										<img src="img/advertising/index/1.jpg" />
									</div>
								</div>
							</div>
						</div>
						<div id="topics">
							<div v-if="isHave()">
								此处暂时没有任何人发帖，奉献你的第一次吧!&nbsp;<a v-bind:href="sendUrl"
									target="_blank">点击发帖</a>
							</div>
							<div v-for="top in topics" class="topic">
								<div id="imgHead">
									<img :src="'img/topic/'+top.id+'.jpg'" />
								</div>
								<div id="tit">
									<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br />
								</div>
								<div id="time">
									<span style="color: gray;">{{top.sendTime | formatDate}}</span>
								</div>
								<div id="userName">
									<a v-bind:href="'bigCow/user/getUserCus/'+top.user.id"
										target="_blank">{{top.user.nickName}}</a>
								</div>
								<div id="text">{{top.text | formatText}}</div>
								<div id="readed">
									<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"
										class="layui-btn layui-btn-normal layui-btn-sm"><i
										class="layui-icon" style="color: green"></i>评论({{top.readNum}})</a>
									<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"
										class="layui-btn layui-btn-normal layui-btn-sm"><i
										class="layui-icon" style="color: gray"></i>阅读({{top.readNum}})</a>
								</div>
								<br> <br>
								<hr style="margin: 0px; margin-top: 50px;" />
							</div>
						</div>
						<div id="indexPaging" style="text-align: center;"></div>

						<div id="bottomCtx" style="position: relative;">
							<div style="display: inline; float: left; margin-left: 30px;">友情链接：</div>
							<div style="display: inline; float: left;">
								<ul>
									<li>AAAAAA</li>
									<li>BBBBBB</li>
									<li>CCCCCC</li>
									<li>DDDDDD</li>
									<li>EEEEEE</li>
									<li>FFFFFF</li>
									<li>GGGGGG</li>
									<li>HHHHHH</li>
									<li>IIIIII</li>
									<li>JJJJJJ</li>
									<li>KKKKKK</li>
									<li>LLLLLL</li>
									<li>MMMMMM</li>
									<li>NNNNNN</li>
									<li>OOOOOO</li>
									<li>PPPPPP</li>
									<li>QQQQ</li>
									<li>RRRR</li>
									<li>SSSS</li>
									<li>TTTT</li>
									<li>UUUU</li>
									<li>VVVV</li>
									<li>WWWW</li>
									<li>XXXX</li>
									<li>YYYY</li>
									<li>ZZZZ</li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div id="rightCtx">
					<h4 style="">A</h4>
					<div class="layui-carousel" id="ad"
						style="margin: auto; margin-top: 10px;">
						<div carousel-item="">
							<div>
								<img src="img/advertising/index/3.jpg" />
							</div>
							<div>
								<img src="img/advertising/index/4.jpg" />
							</div>
							<div>
								<img src="img/topic/71.jpg" />
							</div>
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
							<p v-for="t1 in todayHot">
								<a v-bind:href="'bigCow/topic/get/'+t1.id">{{t1.title}}</a>
							</p>
						</div>
						<h6>今日推荐</h6>
						<div class="topTit">
							<div v-if="jud(todayNew)">暂时没有帖子</div>
							<p v-for="t2 in todayNew">
								<a v-bind:href="'bigCow/topic/get/'+t2.id">{{t2.title}}</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div style="width: 100%; height: 100px; float: left;">
				<div
					style="width: 100%; text-align: center; padding-top: 15px; margin-bottom: 10px;">
					<P>
						<a href="javascript:void(0)">关于BigCow</a>&nbsp;&nbsp;<a
							href="javascript:void(0)">联系我们</a>&nbsp;&nbsp;&copy;2017-2018&nbsp;&nbsp;<a
							href="javascript:void(0)">BigCow</a>&nbsp;&nbsp;保留所有权利&nbsp;&nbsp;<a
							href="javascript:void(0)">粤ICP备123456号</a>
					</P>
					<h2
						style="border: 1px solid gray; width: 160px; margin: auto; background-color: #E6E6E6;">
						<b>@</b><a href="javascript:void(0)" style="color: #393D49;">BigCow</a>
					</h2>
				</div>
			</div>
		</div>
	</div>
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

/* $(document).ready(function(){
	$(".topic").click(function(){
		var elem = $(this).children("div")[1]
		var href = $(elem).children("a").attr("href")
		window.open(href)
	})
}) */
</script>
	<script type="text/javascript">

	layui.use(['element','layer','carousel','laypage'], function(){
	  var $ = layui.jquery,
	  element = layui.element, 
	  layer = layui.layer,
	  carousel = layui.carousel,
	  laypage = layui.laypage;
	  
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
			  laypage.render({
				    elem: 'indexPaging'
					    ,count: app.count
					    ,limit: app.limit
					    ,theme: '#1E9FFF'
					    ,jump: function(obj, first){
					    	 if(!first){
					    		 $.post('bigCow/paging/'+app.pagingUrl,{'nowPage':obj.curr,"type":app.type},function(data){
										app.topics=JSON.parse(data.data.topics)
									},'json')
					    	 }
					     }
					  })
			  $("#globleCtx").css({"height":"1450px"});
		  }else{
			  app.changeTop(this.attributes[0].value)
			  laypage.render({
				    elem: 'indexPaging'
					    ,count: app.count
					    ,limit: app.limit
					    ,theme: '#1E9FFF'
					    ,jump: function(obj, first){
					    	 if(!first){
					    		 $.post('bigCow/paging/'+app.pagingUrl,{'nowPage':obj.curr,"type":app.type},function(data){
										app.topics=JSON.parse(data.data.topics)
									},'json')
					    	 }
					     }
					  })
			  $("#globleCtx").css({"height":"1200px"});
		  }
		});
	  
	  carousel.render({
		    elem: '#imgs'
		    ,width: '99%'
		    ,height: '260px'
		    ,interval: 4000
		  });
		  carousel.render({
			    elem: '#ad'
			    ,width: '95%'
			    ,height: '250px'
			    ,interval: 4000
			  });
	  
	  laypage.render({
		    elem: 'indexPaging'
		    ,count: app.count
		    ,limit: app.limit
		    ,theme: '#1E9FFF'
		    ,jump: function(obj, first){
		    	 $.post('bigCow/paging/'+app.pagingUrl,{'nowPage':obj.curr,"type":app.type},function(data){
						app.topics=JSON.parse(data.data.topics)
					},'json')
		     }
		  });
	  
	});
	
</script>
	<script type="text/javascript">
var app=new Vue({
			el: '#globleCtx',
			data: {
				topics: [],
				count: ${pages.allNum},
				limit: ${pages.nowPageNum},
				sendUrl: ${user==null}?'bigCow/page/toGlobalLogin':'bigCow/page/toAdd',
				pagingUrl: 'firstPage',
				type: null,
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
				changeTop: function(type){
					var temp=this
/* 					$.post("bigCow/topic/getByType",{"type":type},function(data){
						temp.topics=JSON.parse(data.data.topics.pageJSON)
						temp.pagingUrl = "topicType"
						temp.count = data.data.topics.allNum
						temp.type=type
					},"json") */
				    $.ajax({ 
			              type : "post", 
			              url : "bigCow/topic/getByType", 
			              data : {"type":type}, 
			              async : false, 
			              dataType: 'json',
			              success : function(data){ 
			            	  	temp.topics=JSON.parse(data.data.topics.pageJSON)
								temp.pagingUrl = "topicType"
								temp.count = data.data.topics.allNum
								temp.type=type
			              } 
			       });  
				},firstPage: function(){
					var temp=this
/* 					$.post("bigCow/topic/getForIndex",function(data){
						temp.pagingUrl = "firstPage"
						temp.count = data.data.topics.allNum
						temp.topics=JSON.parse(data.data.topics)
					},"json") */
				    $.ajax({ 
			              type : "post", 
			              url : "bigCow/topic/getForIndex", 
			              data : null, 
			              async : false, 
			              dataType: 'json',
			              success : function(data){ 
			            	  	temp.topics=JSON.parse(data.data.topics.pageJSON)
								temp.pagingUrl = "firstPage"
								temp.count = data.data.topics.allNum
			              } 
			         }); 
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
										
					$.post("bigCow/topic/getNew",function(data){
						temp.todayNew=JSON.parse(data.data.topics)
					},'json')
					
					$.post("bigCow/topic/getGlobalHot",function(data){
						temp.todayHot=JSON.parse(data.data.topics)
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
