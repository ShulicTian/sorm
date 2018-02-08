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
		$("input").change(function(){
			var name=$("#validuser").val();
			var pass=$("#validpass").val();
			var url="getUserNames";
			var args={"userName":name,"passWord":pass};
			$.post(url,args,function(data){
				$("#message").html(data);
			});
			
		});
		
	});
	
	$(function(){
		$("#submit").click(function(){
			var len=$("#message").html().length;
			if(len!=0){
				return false;
			}
		});
	});
	
	var judge = false;
	function sub(){
		if(judge==false){
			judge=true;
			return true;
		}
		return false;
	}
</script>
<style type="text/css">
#logForm{padding:10px;}
#logForm input{margin:10px;}
</style>
</head>
<body>
	<div id="logForm">
		<form action="reg" method="post" onsubmit="return sub()">
			<input type="hidden" name="token" value="${sessionScope.token}">
			<div><label style="margin-bottom:0px;">用户名</label><br><input type="text" name="userName" placeholder="请输入您的用户名" style="height:40px;" /></div>
			<div><label style="margin-bottom:0px;">密码</label><br><input type="password" name="passWord" placeholder="请输入您的密码" style="height:40px;color:#EEE8AA;" /></div>
		<input type="submit" class="btn-normal-organ btn-login" value="注册" style="width:280px;background-color:#EEE8AA;color:#b63b4d;"/>
		<!-- style="margin:30px 0px 0px 125px;width:100px;height:30px;" -->
		</form>
	</div>
</body>
</html>
