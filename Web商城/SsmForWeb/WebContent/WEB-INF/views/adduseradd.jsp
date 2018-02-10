<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="web.ssm.po.user.*" %>
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
</style>
</head>
<body>

		<form action="adduseradd" method="post">
			Name:<br/><input type="text" name="name" value=""/><br/><br/>
			PhoneNumber:<br/><input type="text" name="phonenumber" value=""/><br/><br/>
			Ip:<br/><input type="text" name="ip" value=""/><br/><br/>
			<input type="submit" value="保存" />
		</form>
		<form action="useradd" method="post">
			<input type="submit" value="取消" >
		</form>
	
</body>
</html>