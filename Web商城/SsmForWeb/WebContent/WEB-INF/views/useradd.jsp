<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="web.ssm.po.user.*,java.util.*" %>
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
a{	
	text-decoration: none;
	color:#000000;
}
</style>
</head>
<body>

	<a href="addpage">添加</a>   <a href="userindex" >返回</a>
	<% List<Useraddress> useradd = (List<Useraddress>) request.getSession().getAttribute("useradd");
	int num=0;
	if(request.getSession().getAttribute("clickadd").equals("off")){ 
	   for(Useraddress add:useradd){
		   num++;
		   if(add.getId()!=null){
		   %>
			<table>
				<tr>
					<td><%=add.getName() %><br/></td>
					<td><%=add.getPhonenumber() %><br/></td>
					<td><%=add.getIp() %><br/></td>
					<td>
						<a href="changeadd?id=<%=num-1 %> " >修改</a>
					</td>
				</tr>
			</table>		   
		   <% }else{ %>
		   <%   
		   }}
		   %>
	<% }else{ %>
		<form action="setuseradd" method="post">
			Name:<br/><input type="text" name="name" value="${requestScope.oneuseradd.name}"/><br/><br/>
			PhoneNumber:<br/><input type="text" name="phonenumber" value="${requestScope.oneuseradd.phonenumber}"/><br/><br/>
			Ip:<br/><input type="text" name="ip" value="${requestScope.oneuseradd.ip}"/><br/><br/>
			<input type="submit" value="保存" />
		</form>
		<form action="useradd" method="post">
			<input type="submit" value="取消" >
		</form>
	<% }%>
	
</body>
</html>