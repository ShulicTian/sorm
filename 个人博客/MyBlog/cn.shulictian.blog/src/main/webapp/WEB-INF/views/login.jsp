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
<jsp:include page="resources.jsp"/>
<script type="text/javascript">
		$(function(){
			var a=<%=request.getParameter("msg")%>;
			if(a=="no"){
				var b="用户名或密码错误！";
				$("#fonttest").html(b);
			}
		});
		
</script>
<style type="text/css">
#logForm{padding:10px;}
#logForm input{margin:10px;}
</style>
</head>
<body>
	<div id="logForm">
		<form action="bigCow/user/login" method="post">
			<div><label style="margin-bottom:0px;">用户名</label><br><input type="text" name="userName" placeholder="请输入您的用户名" style="height:40px;" /></div>
			<div><label style="margin-bottom:0px;">密码</label><br><input type="password" name="passWord" placeholder="请输入您的密码" style="height:40px;color:#EEE8AA;" /></div>
		<input type="submit" class="btn-normal-organ btn-login" value="登入" style="width:280px;background-color:#EEE8AA;color:#b63b4d;"/>
		<!-- style="margin:30px 0px 0px 125px;width:100px;height:30px;" -->
		</form>
	</div>
</body>
</html>
