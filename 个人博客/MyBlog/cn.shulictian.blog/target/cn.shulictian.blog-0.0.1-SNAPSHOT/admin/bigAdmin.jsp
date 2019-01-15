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
<style type="text/css">
#adminHead{width:100%;height:30px;padding-top:20px;margin-top:30px;}
#adminHead ul{width:50%;height:30px;margin:auto;padding:auto;}
#adminHead ul li{float:left;list-style:none;margin-right:5px;border:1px solid gray;height:30px;width:18%;}
#adminHead ul li a{display:block;text-align:center;height:30px;line-height:30px;text-decoration:none;}
#adminHead ul li a:hover{color:blue;background-color:#D2E9FF;}
</style>
</head>
<body>
	<div id="adminHead">
		<ul>
			<li><a href="getAuditAdmin?types=0">审核帖子</a></li>
			<li><a href="getDeleteAdmin?types=1">管理帖子</a></li>
			<li><a href="getUserAdmin?types=2">用户管理</a></li>
			<li><a href="getReportAdmin?types=3">举报处理</a></li>
			<li><a href="goSuperAdmin?types=4">管理员管理</a></li>
		</ul>
	</div>
</body>
</html>