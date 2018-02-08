<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030" import="shulictian.ssm.po.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#ret").click(function(){
			history.go(-1);			
			return false;
		});
	});
</script>
<style type="text/css">
#aTag{width:100%;text-align:center;}
#aTag a{text-decoration:none;color:blue;margin-right:10px;}
#aTag a:hover{color:red;}
</style>
</head>
<body>
	<h2 style="text-align:center;padding-top:30px;">亲爱的${user.userName}用户，您还不是管理员！</h2>
	<div id="aTag"><a href="toGetAdmin">申请管理员</a></div>
</body>
</html>