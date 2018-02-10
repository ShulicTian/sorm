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
.lab{width:120px;}
</style>
</head>
<body>
	<div style="padding:30px;" id="content">
		<form class="layui-form" action="upGit" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label lab">Git个人主页</label>
				<div class="layui-input-inline" style="width:150px;">
					<input type="text" value="www.github.com/" disabled="disabled" class="layui-input"/>
				</div>
				<div class="layui-input-inline" style="width:100px;">
					<input type="text" name="gitHome" value="${gitHome}" class="layui-input"/>
				</div>
				<div style="width:100px;" class="layui-input-inline">
					<input type="submit" value="保存" class="layui-btn layui-btn-normal"/>
				</div>
			</div>
		</form>
		<hr class="layui-bg-gray" style="margin:0px;margin-top:15px;">
		<div style="width:200px;margin:15px 0;" id="addPro">
			<div class="layui-btn layui-btn-normal" lay-skin="switch" lay-filter="switchTest">添加新项目</div>
		</div>
			<div class="layui-collapse" lay-accordion="" lay-filter="pros">
				<div class="layui-colla-item add" style="display:none;">
				    <h2 class="layui-colla-title" style="margin:0px;">添加新项目</h2>
				    <div class="layui-colla-content">
					    <form class="layui-form pros" action="addPro" method="post" style="margin-top:10px;">
					    	<div class="layui-form-item">
								<label class="layui-form-label lab">项目名称</label>
								<div class="layui-input-inline" style="width:200px;">
									<input name="name" type="text" class="layui-input"/>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label lab">项目简介</label>
								<div class="layui-input-inline" style="width:300px;">
									<textarea name="descs" class="layui-textarea"></textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label lab">项目路径</label>
								<div class="layui-input-inline" style="width:350px;">
									<input name="address" type="text" class="layui-input"/>
								</div>
							</div>
							<div style="width:200px;margin-left:405px;">
								<input type="submit" value="保存" class="layui-btn layui-btn-normal"/>
							</div>
						</form>
				    </div>
				</div>
			  	<div class="layui-colla-item" id="pro" v-for="pro in pros">
				    <h2 class="layui-colla-title" style="margin:0px;">{{pro.name}} 项目</h2>
				    <div class="layui-colla-content ctx">
					    <form class="layui-form pros" action="upPro" method="post" style="margin-top:10px;">
					    	<div class="layui-form-item">
								<label class="layui-form-label lab">项目名称</label>
								<div class="layui-input-inline" style="width:200px;">
									<input type="text" v-bind:value="pro.name" class="layui-input"/>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label lab">项目简介</label>
								<div class="layui-input-inline" style="width:300px;">
									<textarea type="text" v-bind:value="pro.descs" class="layui-textarea"></textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label lab">项目路径</label>
								<div class="layui-input-inline" style="width:350px;">
									<input type="text" v-bind:value="pro.address" class="layui-input"/>
								</div>
							</div>
							<div style="width:100px;margin-left:405px;" class="layui-input-inline">
								<input type="submit" value="保存" class="layui-btn layui-btn-normal"/>
							</div>
						</form>
				    </div>
				</div>
			</div>
	</div>
<script type="text/javascript">
</script>
<script type="text/javascript">
layui.use(['form', 'element','layedit','layer' ], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,element = layui.element;
	  
	  $("#addPro").on("click",function(){
			if($(".pros").length>=10){
		  		layer.msg(('亲，最多只能添加10个项目喔！！！'), {
		  	      offset: '600px'
		  	    });
			}else{
				if($(".add").length>1){
					layer.msg(('请填写完并保存之后在添加喔！'), {
				  	      offset: '100px'
				  	    });
					return;
				}
				$(".ctx").attr("class","layui-colla-content ctx")
				var el=$(".add").clone(true)
				el[0].childNodes[2].attributes.class.value+=" layui-show"
				el[0].style=""
				$("#pro").before(el)
			}
		});
	  
	});
</script>
<script type="text/javascript">
	new Vue({
		el:'#content',
		data:{
			pros: ${pros}
		},
		created:function(){
		},
		methods:{
			sub:function(){
				console.log($(this))
			}
		}
	})
</script>
</body>
</html>