<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
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
#u2{
	padding-top: 20px;
}
#u2 li{
	list-style-type: none;
	margin:10px 0px 10px 0px;
}
#u2 li a{	
	text-decoration: none;
	color:#000000;
}
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	
	<% if(request.getSession().getAttribute("user")!=null){ %>
	<ul id="u2">
		<li>
			<a href="usermsg">个人资料 </a>
		</li>
		<li>
			<a href="useradd">收货地址</a>
		</li>
		<li>
			<a href="">待付款</a>
		</li>
		<li>
			<a href="">待收货</a>
		</li>
		<li>
			<a href="">待评价</a>
		</li>
	</ul>
	<% }else{ %>
	<br/>请登入!
	<% } %>

</body>
</html>