<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ taglib uri="/struts-tags" prefix="s" %>--%>
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
</head>
<body>
	<div style="width:100%;height:100px;"></div>
	<div style="width:200px;margin:auto;">
		<div id="img" style="width:200px;height:200px;border:1px solid;"><img style="width:198px;height:198px;" src="img/user/${user.id}.jpg" /></div>
		<div class="layui-input-inline" style="margin-top:15px;text-align:center;">
			<form action="bigCow/other/upHeadImg" method="POST" enctype="multipart/form-data">
				<input class="layui-btn layui-btn-primary" type="submit" value="保存" />
				<div class="layui-btn layui-btn-primary" style="width:100px;">
					<span style="position:absolute;">文件上传</span>
					<input style="width:100px;opacity:0;height:40px;" type="file" id="headImg" name="file" onchange="preview(this)" multiple="multiple" accept="image/x-png, image/jpg, image/jpeg, image/gif"/>
				</div>
			</form>
		</div>
	</div>
	<div style="width:300px;margin:auto;"><span style="color:gray;">建议上传正方小头像，大小不超过1M!</span></div>
<script>
	 var msg = "您可以上传png, jpg, 或者gif格式的图片";
	 var filter = {
	 "jpeg": "/9j/4",
	 "gif": "R0lGOD",
	 "png": "iVBORw"
	 };
	 var container = document.getElementById("img");
	 function preview(file) {
	 container.innerHTML = "";
	 if (window.FileReader) {
		 for (var index=0, f; f = file.files[index]; index++) {
		 
		 var filereader = new FileReader();
		 filereader.onload = function (event) {
			  var srcpath = event.target.result;
			  if (!validateImg(srcpath)) {
			  } else {
			  showPreviewImage(srcpath);
			  }
		 };
		 filereader.readAsDataURL(f);
		 }
	} else {
		if (!/\.jpg$|\.png$|\.gif$/i.test(file.value)) {
		} else {
			 showPreviewImage(file.value);
			 }
		 }
	 }
	 
	 function validateImg(data) {
	 var pos = data.indexOf(",") + 1;
	 for (var e in filter) {
	 if (data.indexOf(filter[e]) === pos) {
	 	return e;
		 }
	 }
	 return null;
	 }
	 
	 function showPreviewImage(src) {
		 var img = document.createElement('img');
		 img.src = src;
		 img.style = "width:198px;height:198px;"
		 container.appendChild(img);
	 }
</script>
</body>
</html>