<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="shulictian.ssm.po.*"%>
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
<title>Insert title here</title>
<jsp:include page="resources.jsp"></jsp:include>
</head>
<body>
	<div style="background-color: #f5f6f7;">
		<div style="height: 100px; width: 100%;">广告位</div>
		<div id="globleCtx">
			<div id="topics"
				style="height: 752px; width: 99%; margin: auto; background-color: white; padding-top: 40px;">
				<div v-if="isHave()" style="padding: 15px 30px;">此处暂无帖子！</div>
				<div v-for="top in topics" class="topic" style="padding: 0px 45px;">
					<div id="imgHead"></div>
					<div id="tit">
						<a v-bind:href="'getTopic/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br />
					</div>
					<div id="time" style="width: 150px; padding: 0px; display: inline;">
						<span style="color: gray;">{{top.sendTime | formatDate}}</span>
					</div>
					<div id="text">{{top.text | formatText}}</div>
					<div id="readed" style="clear: right;" v-if="top.sendTime!=null">
						<a class="rec" href="javascript:void(0)" @mouseenter="rec(top.id)"
							style="width: 60px; margin-right: 15px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green;"></i>恢复</a> <a class="del"
							href="javascript:void(0)" @mouseenter="del(top.id)"
							style="width: 80px; margin-right: 30px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: gray"></i>彻底删除</a>
					</div>
					<div id="readed" style="clear: right;" v-else>
						<a href="javascript:void(0)" style="width: 60px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green"></i>编辑</a> <a class="rem"
							href="javascript:void(0)" @mouseenter="rem(top.id)"
							style="width: 80px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: gray"></i>删除</a>
					</div>
					<div style="clear: both;"></div>
					<hr style="margin: 0px; margin-top: 10px;" />
				</div>
			</div>
			<div id="handlerPaging" style="text-align: center;"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".rem").click(function(){
			var topId=app.topId
			$.post("bigCow/topic/remove",{"topId":topId})
			$(this).parent().parent().remove()
		})
		$(".del").click(function(){
			var topId=app.topId
			$.post("bigCow/topic/delete",{"topId":topId})
			$(this).parent().parent().remove()
		})
		$(".rec").click(function(){
			var topId=app.topId
			$.post("bigCow/topic/recover",{"topId":topId})
			$(this).parent().parent().remove()
		})
	})
/* $(document).ready(function(){
	$(".topic").click(function(){
		var elem = $(this).children("div")[1]
		var href = $(elem).children("a").attr("href")
		window.open(href)
	})
}) */
</script>
	<script type="text/javascript">
 	layui.use(['carousel', 'element','laypage'], function(){
		  var carousel = layui.carousel,
		  laypage = layui.laypage,
		  element = layui.element;
		  
		  carousel.render({
		    elem: '#imgs'
		    ,width: '75%'
		    ,height: '350px'
		    ,interval: 4000
		  });
		  
		  carousel.render({
			    elem: '#ad'
			    ,width: '99%'
			    ,height: '260px'
			    ,interval: 4000
			  });
		  
		  laypage.render({
			    elem: 'handlerPaging'
			    ,count: app.count
			    ,limit: app.limit
			    ,theme: '#1E9FFF'
			    ,jump: function(obj, first){
			    	var url = 'bigCow/paging/draft'
			    	if(${state}!=6){
			    		url = 'bigCow/paging/dustbin'
			    	}
			    	 $.post(url,{'nowPage':obj.curr,"userId":${user.id}},function(data){
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
				sendUrl: ${user==null}?'bigCow/page/toGlobalLogin':'bigCow/topic/toAdd',
				topId: ''
			},
			methods:{
				isHave:function(){
					if(this.topics.length<=0){
						return true
					}else{
					return false
					}
				},
				rem:function(id){
					this.topId=id
				},
				del:function(id){
					this.topId=id
				},
				rec:function(id){
					this.topId=id
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