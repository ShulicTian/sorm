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
<script type="text/javascript">
	$(document).ready(function(){
		var t=${user.type};
		if(t==0){
			$.get("admin/noAdmin.jsp",function(data){
				$("#adminPage").html(data);
			});
		}
		if(t==1){
			$.get("admin/oneAdmin.jsp",function(data){
				$("#adminPage").html(data);				
			});
		}
		if(t==2){
			$.get("admin/bigAdmin.jsp",function(data){
				$("#adminPage").html(data);				
			});
		}
		
	});
</script>
<style type="text/css">
#admin{background-color: white;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/heads.jsp"></jsp:include>
	<div id="adminPage" style="width:60%;height:1000px;background-color:white;margin:0 auto;"></div>
</body>
</html>