<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030" import="shulictian.ssm.po.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">


<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/javascripts/layer/skin/layer.css">

<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/stylesheets/nav/style.css"> --%>

<%-- <link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/stylesheets/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/stylesheets/nav/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/javascripts/datetimepicker/jquery.datetimepicker.css"> --%>

<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/stylesheets/reset.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/webUtils/bootstrap/css/bootstrap.min.css">



<script type="text/javascript" src="<%=basePath %>/webUtils/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/easyui/locale/easyui-lang-zh_CN.js"></script>

<%-- <script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/fileupload/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/fileupload/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/avalon/avalon.modern.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/custom/jscommon.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/custom/validator.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/nav/nav.js"></script> --%>

<script type="text/javascript" src="<%=basePath %>/webUtils/javascripts/easyui/jquery.edatagrid.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/vue.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/webUtils/vue-router.js"></script>



</head>
<body>
</body>
</html>