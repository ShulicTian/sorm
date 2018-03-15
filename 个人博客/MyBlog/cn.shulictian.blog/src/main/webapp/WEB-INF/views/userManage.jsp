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
		<div id="globleCtx">
			<div style="height: 820px; background-color: white;">
				<div
					style="width: 100%; height: 50px; border: 1px solid; margin-bottom: 30px;"></div>
				<div v-if="users==null|users==''">暂时没有可处理的内容！</div>
				<div v-for="user in users"
					style="padding: 15px 30px; padding-bottom: 0px;"
					@click="toPage('bigCow/user/getUserCus/'+user.id)">
					<div id="photo"
						style="border: 1px solid; width: 50px; height: 50px; display: inline; float: left; margin-bottom: 5px;">
						<img :src="'img/'+user.id+'.jpg'" />
					</div>
					<div id="name"
						style="float: left; display: inline; margin-left: 15px;">
						<a id="userName" style="text-align: left;"
							v-bind:href="'bigCow/user/getUserCus/'+user.id" target="_blank">{{user.nickName}}</a>
					</div>
					<div id="readed" style="margin: 0px;" v-if="user.type==0">
						<a href="javascript:void(0)" class="pun"
							@mouseenter="pun(user.id)" style="width: 60px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green">✘</i>封号</a>
					</div>
					<div id="readed" style="margin: 0px;" v-else-if="user.type==1">
						<a href="javascript:void(0)" class="cancel"
							@mouseenter="cancel(user.id)" style="width: 60px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green">✘</i>撤职</a>
					</div>
					<div id="readed" style="margin: 0px;" v-else>
						<a href="javascript:void(0)" class="unPun"
							@mouseenter="unPun(user.id)" style="width: 60px;"
							class="layui-btn layui-btn-normal layui-btn-sm"><i
							class="layui-icon" style="color: green">✔</i>解封</a>
					</div>
					<hr style="margin: 0px;" />
					<div style="clear: both;"></div>
				</div>
			</div>
			<div id="managePaging" style="text-align: center;"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".pun").click(function(){
			var id=app.userId
			$.post("bigCow/admin/punish",{"id":id})
			$(this).parent().parent().remove()
		})
		$(".unPun").click(function(){
			var id=app.userId
			$.post("bigCow/admin/unPunish",{"id":id})
			$(this).parent().parent().remove()
		})
		$(".cancel").click(function(){
			var id=app.userId
			$.post("bigCow/admin/revocation",{"id":id})
			$(this).parent().parent().remove()
		})
	})
</script>
	<script type="text/javascript">
 	layui.use(['carousel', 'element','laypage'], function(){
		  var carousel = layui.carousel,
		  laypage = layui.laypage,
		  element = layui.element;
		  
		  laypage.render({
			    elem: 'managePaging'
			    ,count: app.count
			    ,limit: app.limit
			    ,theme: '#1E9FFF'
			    ,jump: function(obj, first){
			    	var url = 'bigCow/paging/userManage'
			    	if(${type==3}){
			    		url = 'bigCow/paging/punish'
			    	}
			    	if(${type==1}){
			    		url = null
			    	}
			    	 $.post(url,{'nowPage':obj.curr},function(data){
							app.users=JSON.parse(data.data.users)
						},'json')
			     }
			  });
		}); 
</script>
	<script type="text/javascript">
	var app=new Vue({
			el:'#globleCtx',
			data:{
				users: ${pages.pageJSON},
				count: ${pages.allNum},
				limit: ${pages.nowPageNum},
				userId: ''
			},
			methods:{
				pun:function(id){
					this.userId=id
				},
				unPun:function(id){
					this.userId=id
				},
				cancel:function(id){
					this.userId=id
				},
				toPage:function(href){
					/* window.open(href) */
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
				}
			}
		})
	</script>
</body>
</html>