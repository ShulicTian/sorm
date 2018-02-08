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
			$(location).attr('href', 'logreg');
		}
	});

</script>
<style type="text/css">
#succ a{margin:auto;margin-left:10px;text-decoration:none;color:black;}
#succ a:hover{color:red;}
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="succ">
		<h1>上传成功</h1>
		<a href="toadd">继续添加</a><a href="oneIndex">返回</a>
	</div>
</body>
</html>