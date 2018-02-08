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
#sets div{width:55px;}
#sets2 div{width:55px;}
</style>
</head>
<body>
	<div style="padding:30px;" id="msgs">
		<form action="upUser" style="margin-top:30px;" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label lab">昵称</label>
				<div class="layui-input-block" style="width:200px;">
					<input name="nickName" autocomplete="off" type="text" value="${user.nickName}" class="layui-input"/>
				</div>
			</div>
		</form>
		<form class="layui-form" action="upMsg" method="post" lay-filter="formMsg">
			<div class="layui-form-item" pane="">
		    	<label class="layui-form-label lab">性别</label>
		    	<div class="layui-input-block">
		        	<input name="sex" value="0" title="男" checked="${msgs.sex==0?'checked':''}" type="radio">
		        	<input name="sex" value="1" title="女" checked="${msgs.sex==1?'checked':''}" type="radio">
		    	</div>
		    </div>
			<div class="layui-form-item">
				<label class="layui-form-label lab">年龄</label>
				<div class="layui-input-block" style="width:200px;">
					<input name="age" autocomplete="off" type="text" value="${msgs.age}" class="layui-input"/>
			    </div>
			    <div class="layui-input-block" id="sets">
      				<input id="ageMsg" name="ageState" lay-skin="switch" lay-filter="msgFil" lay-text="公开|保密" type="checkbox">
    			</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label lab">出生日期</label>
			      	<div class="layui-input-inline" style="margin-left:10px;">
			        	<input name="birth" class="layui-input" id="birth" placeholder="点击输入框" value="${msgs.birth }" type="text">
			      	</div>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label lab">个性签名</label>
				<div class="layui-input-block" style="width:700px;">
					<input name="descs" autocomplete="off" type="text" value="${msgs.descs }" class="layui-input"/>
				</div>
			</div>
			<hr class="layui-bg-gray" style="margin:15px 0px;margin-top:50px;">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:800px;">以下信息是否公开</label>
				<div class="layui-input-block" id="sets2">
      				<input id="priMsg" name="msgState" lay-skin="switch" lay-filter="msgFil" lay-text="公开|保密" type="checkbox">
    			</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label lab">真实姓名</label>
				<div class="layui-input-block" style="width:200px;">
					<input name="name" autocomplete="off" type="text" value="${msgs.name}" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label lab">身份证号</label>
				<div class="layui-input-block" style="width:200px;">
					<input name="idCard" autocomplete="off" type="text" value="${msgs.idCard}" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label lab">手机号码</label>
				<div class="layui-input-block" style="width:200px;">
					<input name="phone" autocomplete="off" type="text" value="${msgs.phone}" class="layui-input"/>
				</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label lab">地址</label>
			    <div class="form-group" style="margin-left:10px;display:inline;">
			      <select name="province" class="form-control" lay-ignore style="width:150px;" v-if="pro==null||pro==''">
			        <option value="">请选择省</option>
			        <option v-for="ip in ips" v-bind:value="ip.name" v-on:click="getCity(ip.name)">{{ip.name}}</option>
			      </select>
			      <select name="province" class="form-control" lay-ignore style="width:150px;" v-else v-on:change="change()">
			        <option value="" style="color:gray;" disabled="disabled"><历史></option>
			        <option v-bind:value="pro" selected="selected" v-on:click="getCity(pro)">{{pro}}</option>
			        <option value="" style="color:gray;" disabled="disabled"><更改></option>
			        <option v-for="ip in ips" v-bind:value="ip.name" v-on:click="getCity(ip.name)">{{ip.name}}</option>
			      </select>
			    </div>
			    <div class="form-group" style="margin-left:10px;display:inline;">
			      <select name="city" class="form-control" lay-ignore style="width:150px;" v-if="city==null||city==''">
			        <option value="">请选择市</option>
			        <option v-for="cityOne in citys" v-bind:value="cityOne.name" v-on:click="getCounty(cityOne.name)">{{cityOne.name}}</option>
			      </select>
			      <select name="city" class="form-control" lay-ignore style="width:150px;" v-else>
			        <option value="" style="color:gray;" disabled="disabled"><历史></option>
			        <option v-bind:value="city" id="fir" selected="selected" v-on:click="getCounty(city)">{{city}}</option>
			        <option value="" id="csele" style="display:none">请选择市</option>
			        <option value="" style="color:gray;" disabled="disabled"><更改></option>
			        <option v-for="cityOne in citys" v-bind:value="cityOne.name" v-on:click="getCounty(cityOne.name)">{{cityOne.name}}</option>
			      </select>
			    </div>
			    <div class="form-group" style="margin-left:10px;display:inline;">
			      <select name="county" class="form-control" lay-ignore style="width:150px;">
			        <option value="">请选择县/区</option>
			        <option v-for="county in countys" v-bind:selected="showCty(county)" v-bind:value="county">{{county}}</option>
			      </select>
			    </div>
			</div>
			<div style="width:200px;margin:auto;margin-top:30px;">
				<div class="layui-btn layui-btn-normal" id="subFors">保存</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	/*  v-bind:selected="county=='${msgs.county}'?'selected':''" */
		$(document).ready(function(){
			$(".provs").click(function(){
				var provs = $(this).val()
				var ips = app.ips
				var citys = app.citys
				$.each(ips,function(key,value){
					if(provs==value.name){
						citys=value.city;
					}
				})
				$(this).val(provs)
				app.citys=citys;
			})
			$("#test").change(function(){
				/* app.getCity($(this).val()); */
			})
		})
	
	</script>
<script type="text/javascript">
layui.use(['form', 'layedit', 'laydate','layer'], function(){
  var form = layui.form;
  var layer = layui.layer;
  var layedit = layui.layedit;
  var laydate = layui.laydate;
  
  laydate.render({
	    elem: '#birth',
	    btns: ['clear']
	  });
  laydate.render({
	    elem: '#age',
	    type: 'year',
	    max: '2018',
	    format: 'y',
	    btns: ['clear','confirm']
	  });
  form.on('switch(msgFil)', function(data){
	    layer.msg((this.checked ? '公开' : '保密'), {
	      offset: '6px'
	    });
	  });
  
/*   form.on('select(provin)', function(data){
	  		app.getCity(data.value);
	  		form.render(null,"formMsg");
	  }); */
  
  $("#subFors").on("click",function(){
	  $("form").submit();
  });
});
</script>
<script type="text/javascript">
var app=new Vue({
		el:'#msgs',
		data:{
			ips:JSON.parse(${ips}),
			citys:[],
			countys:[],
			pro: '${msgs.province}',
			city: '${msgs.city}',
			county: '${msgs.county}'
		},
		methods:{
			/* getCity:function(data){
				return JSON.stringify(data)
			}, */
			show:function(){
				var a = '${msgs.msgState}'
				var b = 'on'
				var c = '${msgs.ageState}'
				if(a==b){
					$("#priMsg").attr("checked","checked")
				}
				if(c==b){
					$("#ageMsg").attr("checked","checked")
				}
			},
			getCity:function(province){
				var temp = this;
				this.showIp(null)
				$.each(temp.ips,function(key,value){
					if(province==value.name){
						temp.citys=value.city;
					}
				})
			},
			getCounty:function(city){
				var temp = this;
				$.each(temp.citys,function(key,value){
					if(city==value.name){
						temp.countys=value.area;
					}
				})
			},
			showIp:function(ip){
				return ip==this.pro?'selected':''
			},
			showCity:function(ip){
				return ip==this.city?'selected':''
			},
			showCty:function(ip){
				return ip==this.county?'selected':''
			},
			change:function(){
				$("#fir").remove();
				$("#csele").css({display:"inline"})
			}
			
		},
		created:function(){
			this.getCity('${msgs.province}')
			this.getCounty('${msgs.city}')
			this.show()
		}
	})

</script>
</body>
</html>