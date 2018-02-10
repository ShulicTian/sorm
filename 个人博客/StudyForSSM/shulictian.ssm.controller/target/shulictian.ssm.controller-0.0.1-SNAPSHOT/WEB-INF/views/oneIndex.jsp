<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030" import="shulictian.ssm.po.*,java.util.*"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="css/oneindex.css">
<title>Insert title here</title>
<script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var a = ${pages.nowPage};
		var b = ${pages.pageNum};
		if(a==1){
			$("#start").css("color","#ADADAD");
			$("#start").attr("onclick","return false");
		}
		if(a==b){
			$("#end").css("color","#ADADAD");
			$("#end").attr("onclick","return false");
		}
	});
	
	function rm(obj){
			var bool=confirm("是否删除?");
			if(bool){
				var url="removeTop";
				var userid=${user.id};
				var id=$(obj).attr("href");
				var args={"id":id,"userid":userid};
				$.get(url,args);
				$(obj).parent().parent().remove();
				location.reload();
			}
	}
	
	function cd(obj){
			var bool=confirm("是否彻底删除?");
			if(bool){
				var url="deleteTop";
				var userid=${user.id};
				var id=$(obj).attr("href");
				var args={"id":id,"userid":userid};
				$.get(url,args);
				$(obj).parent().parent().remove();
				location.reload();
			}
	}	
	
	function hf(obj){
			var bool=confirm("是否恢复?");
			if(bool){
				var url="recoverTop";
				var userid=${user.id};
				var id=$(obj).attr("href");
				var args={"id":id,"userid":userid};
				$.get(url,args);
				$(obj).parent().parent().remove();
				location.reload();
			}
	}	
</script>
<style type="text/css">
	body{margin:0px;padding:0px;overflow:hidden;}
</style>
</head>
<body>
	<div>
	<jsp:include page="/WEB-INF/views/heads.jsp"></jsp:include>
	<%if(session.getAttribute("user")!=null){%>
	<div style="width:85%;height:36px;">
		<jsp:include page="/WEB-INF/views/onehead.jsp"></jsp:include>
		<div></div>
	</div>
	
	<div id="tops" style="float:none;margin:0 auto;">
			<div id="title">
				<span style="width:60%;">&nbsp;&nbsp;标题</span>
				<span style="width:20%;text-align:center;">贴主</span>
				<span style="width:10%;text-align:center;">时间</span>
				<span style="width:10%;text-align:center;">操作</span>
			</div>
			<div id="topback">
				<c:forEach items="${pages.pageTops}" var="topone">
					<div id="top">
						<a href="getTopic/${topone.id}" id="titles">&nbsp;&nbsp;${topone.title}</a>
						<span><a href="#">${topone.user.userName}</a></span>
						<span style="width:10%;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${topone.sendTime}"/></span>
						<c:choose>
							<c:when test="${param.audit==2}">
								<span style="width:10%;">
									<a href="${topone.id}" onclick="hf(this);return false;">恢复</a>
									<a href="${topone.id}" onclick="cd(this);return false;"> 彻底删除</a>
								</span>
							</c:when>
							<c:when test="${param.audit==0||param.audit==3 }">
								<span style="width:10%;"><a href="${topone.id}" onclick="cd(this);return false;">删除</a></span>
							</c:when>
							<c:otherwise>
								<span style="width:10%;"><a href="${topone.id}" onclick="rm(this);return false;">删除</a></span>
							</c:otherwise>
						</c:choose>
						
					</div>
				</c:forEach>
			</div>
			<div id="pages">
				<div>
					<a href="pageUser?nowPage=1&audit=${param.audit}" id="fir">首页</a>
					<a href="pageUser?nowPage=${pages.nowPage-1}&audit=${param.audit}" id="start">上一页</a>
					<c:forEach begin="${pages.start}" end="${pages.end}" step="1" var="p">
						<c:choose>
							<c:when test="${pages.nowPage==p}">
								<a href="" style="color:red;font-size:18px;" onclick="return false">${p}</a>
							</c:when>
							<c:otherwise>
								<a href="pageUser?nowPage=${p}&audit=${param.audit}" id="pageIn">${p}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<a href="pageUser?nowPage=${pages.nowPage+1}&audit=${param.audit}" id="end">下一页</a>
					<a href="pageUser?nowPage=${pages.pageNum}&audit=${param.audit}" id="last">尾页</a>
				</div>
			</div>
		</div>
		
		<div id="under"></div>
		<hr style="float:left;margin:0px;width:100%;">
		<div id="under1"></div>	
	<%}%>
	</div>
</body>
</html>