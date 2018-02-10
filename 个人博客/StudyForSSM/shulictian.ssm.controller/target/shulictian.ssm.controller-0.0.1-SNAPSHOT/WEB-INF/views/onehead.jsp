<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
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
<link rel="stylesheet" type="text/css" href="css/oneindex.css">
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var audit=${param.audit};
		if(audit==1){
			$("#sended").css("color","blue");
			$("#auditing").css("background-color","none");
			$("#deleted").css("background-color","none");
			$("#unPass").css("background-color","none");
		}
		if(audit==0){
			$("#auditing").css("color","blue");
			$("#sended").css("background-color","none");
			$("#deleted").css("background-color","none");
			$("#unPass").css("background-color","none");
		}
		if(audit==2){
			$("#deleted").css("color","blue");
			$("#auditing").css("background-color","none");
			$("#sended").css("background-color","none");
			$("#unPass").css("background-color","none");
		}
		if(audit==3){
			$("#unPass").css("color","blue");
			$("#auditing").css("background-color","none");
			$("#sended").css("background-color","none");
			$("#deleted").css("background-color","none");
		}
	});
</script>
</head>
<body>
	<div id="onehead">
		<a href="toadd" id="send">发帖</a>
		<ul>
			<li><a href="getRems?audit=2" id="deleted">已删帖子</a></li>
			<li><a href="getUnPass?audit=3" id="unPass">未过审帖子</a>
			<li><a href="getAudit?audit=0" id="auditing">审核中帖子</a></li>
			<li><a href="getUserTop?audit=1" id="sended">已发帖子</a></li>
		</ul>
	</div>
</body>
</html>