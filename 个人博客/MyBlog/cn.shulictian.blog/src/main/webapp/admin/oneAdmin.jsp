<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030" import="shulictian.ssm.po.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="css/adminstyle.css">
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
</script>
<style type="text/css">

</style>
</head>
<body>
	<div id="oneHead">
		<ul>
			<li><a href="getAuditAdmin?types=0">审核帖子</a></li>
			<li><a href="getDeleteAdmin?types=1">管理帖子</a></li>
			<li><a href="getUserAdmin?types=2">用户管理</a></li>
			<li><a href="getReportAdmin?types=3" onclick="return false">举报处理</a></li>
		</ul>
	</div>
	<!-- <iframe width="90%" height="86%" name="iframeText" style="background-color:#D2E9FF;margin-left:55px;margin-top:20px;"></iframe> -->
</body>
</html>