<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="webUtils/stylesheets/login.css">
<link rel="stylesheet" type="text/css" href="css/logreg.css">
<style type="text/css">
body{
	background: url("img/back.jpg") fixed;
	overflow:hidden;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="head.jsp"></jsp:include>
		<div style="width:80%;height:36px;">
			<div style="float:left;width:420px;height:36px;margin-left:20px;"></div>
			<div style="float:left;width:1170px;height:36px;margin-left:22px;"></div>
		</div>
	</div>
	<div id="inCore">
		<div style="height:62px;">
			<div id="reg"><a href="javascript:void(0)" style="color:#b63b4d;">注册</a></div>
			<div id="log"><a href="javascript:void(0)" style="color:#b63b4d;">登录</a></div>
		</div>
		<div id="forms">
			<div id="redORlog"></div>
		</div>
	</div>
<script type="text/javascript">

	$(document).ready(function(){
			var url="tolog?msg=<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>";
			$("#reg").css("box-shadow","0px 5px 5px #EEE8AA");
			$.get(url,function(data){
				$("#redORlog").html(data);
			});
	});
	$(document).ready(function(){
		$("#log").click(function(){
			$("#reg").css("box-shadow","0px 5px 5px #EEE8AA");
			$(this).css("box-shadow","0px 5px 5px #b63b4d");
			$.get("tolog",function(data){
				$("#redORlog").html(data);
			});
		});
	});
	$(document).ready(function(){
		$("#reg").click(function(){
			$("#log").css("box-shadow","0px 5px 5px #EEE8AA");
			$(this).css("box-shadow","0px 5px 5px #b63b4d");
			$.get("toreg",function(data){
				$("#redORlog").html(data);
			});
		});
	});
</script>
</body>
</html>