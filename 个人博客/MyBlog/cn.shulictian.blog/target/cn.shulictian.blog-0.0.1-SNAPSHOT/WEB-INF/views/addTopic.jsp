<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

#butt {
	width: 70%;
	margin: 0 auto;
	text-align: center;
}

#d1 {
	width: 60%;
	margin: 0 auto;
	background-color: white;
	padding: 20px;
	margin-top: 20px;
}

#texts {
	width: 90%;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div style="background-color: #f5f6f7;">
		<jsp:include page="head.jsp"></jsp:include>
		<div id="d1">
			<form class="layui-form" action="bigCow/topic/add" id="send"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="topicToken" value="${sessionScope.topicToken}">
				<div class="layui-form-item">
					<div class="layui-input-block"
						style="margin-left: 50px; width: 100px; float: left; padding: 10px 5px;">
						<select name="genre" lay-filter="aihao">
							<option value="0">原创</option>
							<option value="1">转载</option>
							<option value="2">翻译</option>
						</select>
					</div>
					<div class="layui-input-block"
						style="margin: 0px; padding: 10px 5px; width: 900px; float: left;">
						<input name="title" lay-verify="title" autocomplete="off"
							placeholder="请输入标题" class="layui-input" type="text">
					</div>
				</div>
				<div id="texts">
					<textarea rows="3" cols="3" name="text"></textarea>
					<br>
				</div>

				<div class="layui-form-item" style="margin-left: 50px;">
					<label class="layui-form-label" style="width: 100px;">文章类型</label>
					<div class="layui-input-inline" style="width: 200px;">
						<select name="type" lay-filter="aihao">
							<option value=""></option>
							<option v-for="types in names" v-bind:value="types.type">{{types.name}}</option>
						</select>
					</div>
					<div class="layui-input-inline"
						style="margin: 5px 15px; margin-left: 30px;">
						<input class="easyui-filebox" name="file"
							data-options="prompt:'上传小图片'" style="width: 200px;">
					</div>
				</div>

				<div id="butt">
					<input class="layui-btn layui-btn-primary" type="submit" value="发布">
					<input class="layui-btn layui-btn-primary" type="button"
						value="存入草稿" @click="draft"> <input
						class="layui-btn layui-btn-primary" type="button" value="取消"
						@click="cancel">
				</div>
			</form>
		</div>
		<div style="width: 100%; height: 50px;"></div>
	</div>

	<script type="text/javascript">
	layui.use(['form', 'layedit','upload'], function(){
		  var form = layui.form
		  ,layedit = layui.layedit
		  ,upload = layui.upload;
		  
		  
		  //创建一个编辑器
		  var editIndex = layedit.build('LAY_demo_editor');
		  
		});
	</script>
	<script type="text/javascript">
		new Vue({
			el: '#d1',
			data: {
				names:[
					{name:'Vue.js',type:0},
					{name:'Node.js',type:1},
					{name:'JavaScript',type:2},
					{name:'Ajax',type:3},
					{name:'JQuery',type:4},
					{name:'JQueryUI',type:5},
					{name:'EasyUI',type:6},
					{name:'LayUI',type:7},
					{name:'BootStrap',type:8},
					{name:'C/C++',type:9},
					{name:'Java',type:10},
					{name:'Java框架',type:11},
					{name:'Maven',type:12},
					{name:'Svn/Git',type:13},
					{name:'Solr',type:14},
					{name:'Navicat for Mysql',type:15},
					{name:'Eclipse',type:16},
					{name:'IntelliJ IDEA',type:17},
					{name:'WebStorm',type:18},
					{name:'Mysql',type:19},
					{name:'Tomcat',type:20},
					{name:'Weblogic',type:21},
					{name:'Servlet/JSP',type:22},
					{name:'Log4j',type:23},
					{name:'Nginx',type:24},
					{name:'redis',type:25},
					{name:'shiro',type:26}
				]
			},
			methods:{
				cancel: function(){
					location.href=""
				},
				draft:function(){
					$("#send").attr("action","bigCow/page/toDraft")
					$("#send").submit() 
				},
				sub:function(){
					$("form").submit()
				}
			},
			created:function(){
				console.log()
			}
		})
	</script>
	<script type="text/javascript">CKEDITOR.replace('text',{height:500});</script>
	<script type="text/javascript">
	$(document).ready(function(){
			if(${user==null}){
				alert("请登入！");
				$(location).attr('href', 'bigCow/page/toGlobalLogin');
			}
	});
</script>
</body>
</html>