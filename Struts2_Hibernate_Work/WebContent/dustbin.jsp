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

<script type='text/javascript' src='dwr/engine.js'></script>  
<script type='text/javascript' src='dwr/util.js'></script>  
<script type='text/javascript' src='dwr/interface/receiveDao.js'></script>

<script type="text/javascript">
		function regain(id,b){
			
			receiveDao.regain(id,function(result){
				
				if(result){
					alert("恢复成功");
					var tr = b.parentNode.parentNode;
					tr.parentNode.removeChild(tr);
				}else{
					alert("恢复失败");		
				}
				
			});
		}
			
			function remove(id,b){
				
				receiveDao.remove(id,function(result){
					
					if(result){
						alert("删除成功");
						var tr = b.parentNode.parentNode;
						tr.parentNode.removeChild(tr);
					}else{
						alert("删除失败");		
					}
					
				});
		}	

</script>
<style type="text/css">
th{
	border:1px solid;
	text-align:center;
	}
td{
	border:1px solid;
	text-align:center;
	}
a{
	text-decoration: none;
	color: blue;
}
</style>
</head>
<body>
<s:include value="top.jsp"></s:include>
	<table width="80%" style="border:1px solid;" cellspacing="0">
		<caption><b>垃圾箱</b></caption>
		<tr>
			<th><small>来源</small></th><th><small>标题</small></th><th><small>内容</small></th><th><small>操作</small></th>
		</tr>
		
		<s:iterator value="listmsg" var="li">
			<tr>
				<s:if test="#li.del==1">
					<td>发件箱</td>
				</s:if>
				<s:else>
					<td>收件箱</td>
				</s:else>
				<td>
				
					<b><s:property value="#li.title"/></b>
				
				</td>
				
				<td>
				
					<s:property value="#li.content"/>
				
				</td>
				
				<td>
				
					<a href="javascript:void(0)" onclick="regain(<s:property value="#li.id"/> ,this )">||恢复||</a>
					<a href="javascript:void(0)" onclick="remove(<s:property value="#li.id"/> ,this)">||彻底删除||</a>
					
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>