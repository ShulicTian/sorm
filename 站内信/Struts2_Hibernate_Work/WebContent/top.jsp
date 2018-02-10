<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
</head>
<body>
	<s:if test="#session.user==null">
		<a href="login.jsp">登陆</a>
		<a href="reg.jsp">注册</a>
	</s:if>
	<s:else>
		欢迎！<s:property value="#session.user.uname"/>
		<a href="msg_add.jsp">写信</a>
	<a href="message!listMsg">发件箱</a>
	<a href="getmessage!getHaveMsg" >收件箱</a>
	<a href="getmessage!getDelStataMsg">垃圾箱</a>
	<hr color="red">
	</s:else><br/>

</body>
</html>