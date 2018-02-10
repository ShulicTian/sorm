<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="web.ssm.po.item.*,java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>商品列表</title>
<style type="text/css">
body{
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<%
		for(Item i:(List<Item>)request.getAttribute("items")){
	%>
		<a href="item?id=<%=i.getId()%>&name=<%=i.getName()%>&much=<%=i.getMuch()%>&photo=<%=i.getPhoto()%>"><%=i.getName()+" ￥"+i.getMuch() %></a><br/>
	<% } %>
</body>
</html>