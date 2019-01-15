<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shulictian.ssm.po.*"%>
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
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if(${user==null}){
			alert("请登入！");
			$(location).attr('href', 'bigCow/page/toGlobalLogin');
		}
	});

</script>
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="succ" style="margin: auto;text-align: center;">
		<h1>上传成功</h1>
		<a class="layui-btn layui-btn-primary" href="bigCow/page/toAdd">继续添加</a><a href="bigCow/page/" class="layui-btn layui-btn-primary">返回首页</a>
	</div>
</body>
</html>