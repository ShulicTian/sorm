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
				<div v-if="users==null|users==''">此处还没有内容，您可以发表文章吸引粉丝，或关注博主成为粉丝!</div>
				<div v-for="user in users"
					style="padding: 15px 30px; padding-bottom: 0px;"
					@click="toPage('bigCow/user/getUserCus/'+user.id)">
					<div id="photo"
						style="border: 1px solid; width: 50px; height: 50px; display: inline; float: left; margin-bottom: 5px;">
						<img :src="'img/'+user.nickName+'.jpg'" />
					</div>
					<div id="name"
						style="float: left; display: inline; margin-left: 15px;">
						<a id="userName" style="text-align: left;"
							v-bind:href="'getUserCus/'+user.id" target="_blank">{{user.nickName}}</a>
					</div>
					<hr style="margin: 0px;" />
					<div style="clear: both;"></div>
				</div>
			</div>
			<div id="fansPaging" style="text-align: center;"></div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
 	layui.use(['carousel', 'element','laypage'], function(){
		  var carousel = layui.carousel,
		  	  laypage = layui.laypage,
		  	  element = layui.element;
		  
		  laypage.render({
			    elem: 'fansPaging'
			    ,count: app.count
			    ,limit: app.limit
			    ,theme: '#1E9FFF'
			    ,jump: function(obj, first){
			    	var url = 'bigCow/paging/fans'
			    	if('${jud}'!="fans"){
			    		url = 'bigCow/paging/atts'
			    	}
			    	 $.post(url,{'nowPage':obj.curr,"userId":${user.id}},function(data){
							app.conts=JSON.parse(data.data.users)
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
			},
			methods:{
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