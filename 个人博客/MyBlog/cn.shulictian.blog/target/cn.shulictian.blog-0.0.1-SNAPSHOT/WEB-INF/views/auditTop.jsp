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
			<div v-if="topics==null|topics==''">暂时没有可处理的内容！</div>
			<div id="topics"
				style="height: 752px; width: 99%; margin: auto; background-color: white; padding-top: 45px;">
				<div v-for="top in topics" class="topic" style="padding: 0px 45px;">
					<div id="imgHead"></div>
					<div id="tit">
						<a v-bind:href="'bigCow/topic/get/'+top.id" target="_blank"><h6>{{top.title}}</h6></a><br />
					</div>
					<div id="time">
						<span style="color: gray;">{{top.sendTime | formatDate}}</span>
					</div>
					<div id="userName">
						<a>{{top.user.nickName}}</a>
					</div>
					<div id="text">{{top.text | formatText}}</div>
					<div id="readed" style="clear: right;" v-if="${state!=4}">
						<a :id="top.id" href="javascript:void(0)" class="pass"
							@mouseenter="auditPass(top.user.id)"
							class="layui-btn layui-btn-normal layui-btn-sm"
							style="width: 60px;"><i class="layui-icon"
							style="color: green">✔</i>通过</a> <a :id="top.id"
							href="javascript:void(0)" class="nopass"
							@mouseenter="auditNoPass(top.user.id)"
							class="layui-btn layui-btn-normal layui-btn-sm"
							style="width: 60px;"><i class="layui-icon"
							style="color: gray">✘</i>不通过</a>
					</div>
					<div id="readed" style="clear: right;" v-else>
						<a :id="top.id" href="javascript:void(0)" class="rec"
							@mouseenter="rec(top.user.id)"
							class="layui-btn layui-btn-normal layui-btn-sm"
							style="width: 60px;"><i class="layui-icon"
							style="color: gray"></i>恢复</a>
					</div>
					<div style="clear: both;"></div>
					<hr style="margin: 0px;" />
				</div>
			</div>
			<div id="auditPaging" style="text-align: center;"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".pass").click(function(){
			var userId=app.userId
			var topId = $(this).attr("id")
			$.post("bigCow/admin/pass",{"topId":topId,"userId":userId})
			$(this).parent().parent().remove()
		})
		$(".nopass").click(function(){
			var userId=app.userId
			var topId = $(this).attr("id")
			$.post("bigCow/admin/nopass",{"topId":topId,"userId":userId})
			$(this).parent().parent().remove()
		})
		$(".rec").click(function(){
			var userId=app.userId
			var topId = $(this).attr("id")
			$.post("bigCow/admin/recoverAdmin",{"topId":topId,"userId":userId})
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
 	layui.use(['carousel', 'element' ,'laypage'], function(){
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
				    elem: 'auditPaging'
				    ,count: app.count
				    ,limit: app.limit
				    ,theme: '#1E9FFF'
				    ,jump: function(obj, first){
				    	var url = 'bigCow/paging/manage';
				    	if(${state!=4}){
				    		url = "bigCow/paging/audit"
				    	}
				    	 $.post(url,{"nowPage":obj.curr},function(data){
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
				pageUrl: "audit",
				userId: ''
			},
			methods:{
				isHave:function(){
					if(this.topics.length<=0){
						return true
					}else{
					return false
					}
				},
				auditPass:function(id){
					this.userId=id
				},
				auditNoPass:function(id){
					this.userId=id
				},
				rec:function(id){
					this.userId=id
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