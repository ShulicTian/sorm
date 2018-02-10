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
<s:include value="top.jsp"></s:include>
	<form action="message!add" method="post">
		标题<input type="text" name="title"/>
		<br/>内容<textarea rows="5" cols="30" name="content"></textarea>
		<input type="hidden" name="sendId" value="<s:property value='#session.user.id' />" />
		<br/>收件人列表：
		<s:action name="user!listAllUser" namespace="/" var="a"></s:action>
		<s:iterator value="#a.list" var="u">
			<a href="javascript:void(0)" onclick="selectUser('<s:property value="#u.id"/>','<s:property value="#u.uname"/>');"  > <s:property value="#u.uname" /> </a>
		</s:iterator><br/>
		<input type="text" readonly="readonly" id="usernames"/>
		<input type="hidden" name="userids" id="userids"/>
		<input type="submit" value="Send"/>
	</form>
	
	<script type="text/javascript">
		var result="";
		var ids="";
		function selectUser(id,uname){
			result += uname+";";
			ids += id+";";
			document.getElementById("usernames").value=result;			
			document.getElementById("userids").value=ids;			
		}
	
	</script>
</body>
</html>