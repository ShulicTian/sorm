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
		function del(id,a){
			receiveDao.del(id,function(result){
				
				if(result){
					alert("删除成功");
					var tr = a.parentNode.parentNode;
					tr.parentNode.removeChild(tr);
				}else{
					alert("删除失败");		
				}
				
			});
			
		}
		function read(id,a){
			receiveDao.read(id,function(result){
				
				if(result){
					alert("已标为已读！");
					
				}else{
					alert("标为已读失败！");		
				}
				
			});
			
		}

</script>
</head>
<body>
<s:include value="top.jsp"></s:include>
	<table width="80%" border="1">
		<tr>
			<th>标题</th><th>内容</th><th>状态</th><th>操作</th>
		</tr>
		<s:iterator value="list" var="li">
				<tr>
					<td><s:property value="#li.message.title"/></td>
					<td><s:property value="#li.message.content"/></td>
					<s:if test="#li.readStata==0">
						<td>未读</td>
					</s:if>
					<s:else>
						<td>已读</td>
					</s:else>
					<td>
						<a href="javascript:void(0)" onclick="del(<s:property value="#li.id" />,this)" >||删除||</a>
						<a href="getmsgbox.jsp" onclick="read(<s:property value="#li.id" />,this)">||设为已读||</a>
					</td>
				</tr>
		</s:iterator>
	</table>
</body>
</html>