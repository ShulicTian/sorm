<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="shulictian.ssm.po.*"%>
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
.lab{width:100px;}
</style>
</head>
<body>
	<div style="padding:30px;" id="content">
		<div class="layui-tab" style="margin-top:0px;">
		  <ul class="layui-tab-title" style="width:200px;margin:auto;">
		    <li class="layui-this">原密码修改</li>
		    <li>手机号修改</li>
		  </ul>
		  <div class="layui-tab-content" style="width:440px;margin:auto;margin-top:30px;">
		    <div class="layui-tab-item layui-show">
		      	<form class="layui-form" action="upPwd" method="post">
					<div class="layui-form-item">
						<label class="layui-form-label lab">旧密码</label>
						<div class="layui-input-block" style="width:200px;">
							<input name="oldPwd" style="display:none;" placeholder="输入旧密码" type="password" class="layui-input" autocompelete="off"/>
							<input name="oldPwd" placeholder="输入旧密码" type="text" class="layui-input" autocompelete="off"/>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label lab">新密码</label>
						<div class="layui-input-block" style="width:200px;">
							<input name="newPwd" autocompelete="off" placeholder="输入新密码" type="password" class="layui-input"/>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label lab">再次输入</label>
						<div class="layui-input-block" style="width:200px;">
							<input placeholder="再次输入密码" autocompelete="off" type="password" class="layui-input"/>
						</div>
					</div>
					<div style="width:100px;margin:auto;margin-top:30px;">
						<input type="submit" value="确认修改" class="layui-btn layui-btn-normal"/>
					</div>
				</form>
		    </div>
		    <div class="layui-tab-item">
		    	<form class="layui-form" action="">
					<div class="layui-form-item">
						<label class="layui-form-label lab">手机号码</label>
						<div class="layui-input-block" style="width:200px;">
							<input name="nickName" autocomplete="off" type="text" class="layui-input"/>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label lab">验证码</label>
						<div class="layui-input-block" style="width:100px;">
							<input name="nickName" autocomplete="off" type="text" class="layui-input"/>
						</div>
					</div>
					<div style="width:100px;margin:auto;margin-top:30px;">
						<input type="submit" value="确认修改" class="layui-btn layui-btn-normal"/>
					</div>
				</form>
		    </div>
		  </div>
		</div>
	</div>
<script type="text/javascript">
layui.use('element', function(){
	  var $ = layui.jquery
	  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
	  
	});
</script>
<script type="text/javascript">
	new Vue({
		el:'#content',
		data:{
			
		},
		methods:{
		},
		created:function(){
		}
	})
</script>
</body>
</html>