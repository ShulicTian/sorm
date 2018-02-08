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
<link rel="stylesheet" type="text/css" href="css/backstyle.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/stylesheets/login.css">
<style type="text/css">
body{
	margin:0px;
	padding:0px;
}
</style>
<jsp:include page="resources.jsp"/>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
</body>
</html>