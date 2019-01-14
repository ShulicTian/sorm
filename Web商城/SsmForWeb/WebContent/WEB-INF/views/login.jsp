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
<style type="text/css">
body{
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<% if(request.getSession().getAttribute("user")==null){ %>
	${requestScope.msg}
	<% 
	}
	%>
	<form action="login" method="post">
		UserName:<input type="text" name="name"/><br/>
		PassWord:<input type="password" name="password" />
		<input type="submit" value="登入" />
	</form>

</body>
</html>