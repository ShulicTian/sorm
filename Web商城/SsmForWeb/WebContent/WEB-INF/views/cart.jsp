<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="web.ssm.po.item.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
	购物车:<br/>
	<form action="buyall" method="post">
	<%
		int a=0;
		List<Item> list=(List<Item>)request.getAttribute("items");
		for(int k=0;k<list.size();k++){
			Item i = list.get(k);
			a++;
			int num=1;
			for(int j=a;j<list.size();j++){
			if(i.getId()==list.get(j).getId()){
				num++;
				list.remove(j);
				a--;
			}
		}
		double much=i.getMuch()*num;
	%>
		<input type="checkbox" name="id" value="<%=i.getId()%>"><a href="item?id=<%=i.getId()%>&name=<%=i.getName()%>&much=<%=i.getMuch()%>&photo=<%=i.getPhoto()%>"><%=i.getName()+" x"+num+"  ￥"+much %></a></input><br/>
		<input type="hidden" name="number" value="<%=num%>" />
		<% } %>
		<input type="submit" value="立即购买" />
	</form>
</body>
</html>