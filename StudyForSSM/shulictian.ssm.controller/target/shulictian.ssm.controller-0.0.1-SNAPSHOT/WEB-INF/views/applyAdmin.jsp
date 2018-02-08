<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shulictian.ssm.po.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="resources.jsp"></jsp:include>
<style type="text/css">
a:hover{color:blue;text-decoration:underline;}
#code{width:200px;}
#apply{width:600px;height:350px;margin:auto;border:1px solid;text-align:center;border-radius:15px;border-color:gray;}
</style>
</head>
<body>
			<div style="width:100%;padding-bottom:200px;"></div>
			<form class="layui-form" action="goGetAdmin" method="get" id="apply">
				<div style="margin-top:140px;">
					<div class="layui-inline">
					<input class="layui-input" autocomplete="off" placeholder="请输入邀请码" type="text" name="code" id="code"/>
					</div>
					
					<div class="layui-inline">
						<input class="layui-btn layui-btn-primary" type="submit" value="验证" id="validata"/>
					</div>
					
					<div class="layui-block" style="margin-top:130px;opacity:0.7;">
						<label class="layui-input-label">若无邀请码请联系 <i style="color:blue;">博主：<a href="##" style="text-decoration:underline;">ShulicTian</a></i></label>
					</div>
				</div>
			</form>
<script type="text/javascript">
		$(document).ready(function(){
			if(${getStatus!=null}){
				if(${getStatus=="yes"}){
					alert("恭喜您，晋升为管理员！");
				}else{
					alert("验证失败，请检查您的邀请码！");
				}
			}
		});			
	$(function(){
		$("#validata").click(function(){
			var type=${user.type};
			if(type==1){
				alert("您已经是管理员，请勿重复申请！");
				return false;
			}
			var code=$("#code").val();
			if(code=="" || code==null){
				alert("邀请码不能为空！");
				return false;				
			}
		});
	});
</script>
</body>
</html>