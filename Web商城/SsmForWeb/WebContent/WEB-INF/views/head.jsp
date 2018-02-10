<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="web.ssm.po.user.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
<style type="text/css">
body{
	margin: 0px;
	padding: 0px;
}
#u1 li{
	float: left;
	list-style-type: none;
	margin-right:5px;
	width: 100px; 
}
#u1 li a{	
	text-decoration: none;
	color:#000000;
}
</style>
</head>
<body>

	<% 
	User u = (User) request.getSession().getAttribute("user");
	if(u == null){
	%>
	<a href="reg.jsp">注册</a>
	<a href="login.jsp">登入</a>
	<% }else{%>
	欢迎！
	${requestScope.user.name}
	<a href="exit">注销</a>
	<ul style="float: right;" id="u1">
	
		<li>
			<a href="index" >首页</a>
		</li>
		<li>
			<a href="userindex" >个人主页</a>
		</li>
		<li>
			<a href="cart" >购物车</a>
		</li>
		<li>
			<a href="sell" >卖家中心</a>
		</li>
		<li>
			<a href="" >联系客服</a>
		</li>
	</ul>
	
	<%
	}
	%>

</body>
</html>