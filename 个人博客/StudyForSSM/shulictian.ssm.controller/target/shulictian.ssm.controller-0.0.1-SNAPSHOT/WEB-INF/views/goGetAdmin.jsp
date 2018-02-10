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
<link rel="stylesheet" type="text/css" href="css/oneindex.css">
<title>Insert title here</title>
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">

		$(document).ready(function(){
			if(${getStatus!=null}){
				if(${getStatus=="yes"}){
					alert("恭喜您，晋升为管理员！");
				}else{
					alert("验证失败，请检查您的邀请码！");
				}
			}
		});			
	
	$(function(){
		$("#validata").click(function(){
			var type=${user.type};
			if(type==1){
				alert("您已经是管理员，请勿重复申请！");
				return false;
			}
			var code=$("#code").val();
			if(code=="" || code==null){
				alert("邀请码不能为空！");
				return false;				
			}
		});
	});
</script>
<style type="text/css">
a{text-decoration: none;color:black;}
a:hover{color:blue;text-decoration:underline;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/heads.jsp"></jsp:include>
	<div style="margin:auto;margin-top:30px;padding:30px;;width:70%;text-align:center;background-color:white;">
		<form action="goGetAdmin" method="get">
			请输入邀请码:<input type="text" name="code" id="code"><input type="submit" value="验证" id="validata">
			<p>若无邀请码请联系<i style="color:blue;">博主:<a href="##" style="text-decoration:underline;">ShulicTian</a></i></p>
			<a href="##" onclick="location.href='index';return false" style="border:1px gray solid;display:block;width:50px;height:25px;line-height:25px;margin:auto;">
				返回
			</a>
		</form>
	</div>
</body>
</html>