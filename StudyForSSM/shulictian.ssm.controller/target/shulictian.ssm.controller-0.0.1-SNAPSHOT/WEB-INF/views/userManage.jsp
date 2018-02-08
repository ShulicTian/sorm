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
<link rel="stylesheet" type="text/css" href="css/index.css">
<title>Insert title here</title>
<jsp:include page="resources.jsp"></jsp:include>
</head>
<body>
	<div style="background-color:#f5f6f7;">
		<div id="globleCtx">
			<div style="height:820px;background-color:white;">
				<div style="width:100%;height:50px;border: 1px solid;margin-bottom:30px;"></div>
				<div v-for="user in users" style="padding:15px 30px;padding-bottom:0px;">
					<div id="photo" style="border: 1px solid;width:50px;height:50px;display:inline;float:left;margin-bottom:5px;"><img :src="'img/'+user.nickName+'.jpg'"/></div>
					<div id="name" style="float:left;display:inline;margin-left:15px;"><a id="userName" style="text-align:left;" v-bind:href="'getUserCus/'+user.id" target="_blank">{{user.nickName}}</a></div>
					<div id="readed" style="margin:0px;" v-if="user.type==0">
						<a href="javascript:void(0)" id="pun" @click="pun(user.id)" style="width:60px;" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:green">✘</i>封号</a>
					</div>
					<div id="readed" style="margin:0px;" v-else-if="user.type==1">
						<a href="javascript:void(0)" id="cancel" @click="cancel(user.id)" style="width:60px;" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:green">✘</i>撤职</a>
					</div>
					<div id="readed" style="margin:0px;" v-else>
						<a href="javascript:void(0)" id="unPun" @click="unPun(user.id)" style="width:60px;" class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon" style="color:green">✔</i>解封</a>
					</div>
					<hr style="margin:0px;" />
					<div style="clear:both;"></div>
				</div>
			</div>
			<div id="pages" style="margin-top:10px;">
				<div style="width:300px;height:27px;margin:0 auto;overflow:hidden;">
					<a href="javascript:void(0)" v-on:click="getPage(1)" class="easyui-linkbutton">首页</a>
					<a href="javascript:void(0)" id="start" v-on:click="getPage(nowPage-1)" class="easyui-linkbutton" style="margin:0px 3px;">上一页</a>
					<a v-for="p in pageNum" :class="{'page':setStyle(start+p-1)}" style="width:20px;display:block;text-align:center;margin-top:2px;">{{start+p-1}}</a>
					<a href="javascript:void(0)" id="end" v-on:click="getPage(nowPage+1)" class="easyui-linkbutton" style="margin:0px 3px;">下一页</a>
					<a href="javascript:void(0)" v-on:click="getPage(pagesNum)" class="easyui-linkbutton">尾页</a>
				</div>																																																																																							
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
<script type="text/javascript">
 	layui.use(['carousel', 'element'], function(){
		  var carousel = layui.carousel,
		  	  element = layui.element;
		}); 
</script>
	<script type="text/javascript">
		new Vue({
			el:'#globleCtx',
			data:{
				users: ${pages.pageTops},
				pagesNum: ${pages.pageNum},
				pageNum: ${pages.pageNum}>5?5:${pages.pageNum},
				nowPage: 1,
				start: 1,
				end: 5
			},
			methods:{
				getPage: function(nowPage){
					if(nowPage>=1 && nowPage<=this.pagesNum){
						this.nowPage=nowPage
						
						if(this.pagesNum>5){
							this.judPage(nowPage)
						}
						this.disabled()
						var temp=this
					 	$.post("pageUsers",{"nowPage":nowPage},function(data){
					 		temp.users=data
						},"json")
					}
				},
				disabled: function(){
					if(this.nowPage==1){
						$("#start").css("color","#ADADAD");
					}else{
						$("#start").css("color","#007bff");
					}
					if(this.nowPage==this.pagesNum){
						$("#end").css("color","#ADADAD");
					}else{
						$("#end").css("color","#007bff");
					}
				},
				setStyle: function(page){
					return this.nowPage==page?true:false
				},
				judPage: function(nowPage){
					if(nowPage>this.end){
						if(nowPage>=this.pagesNum-4){
							this.start=this.pagesNum-this.pagesNum%5+1
							this.pageNum=this.pagesNum%5
							this.end=this.pagesNum
						}else{
							this.pageNum=5
							this.start=this.end+1
							this.end=nowPage+4
						}
					}
					if(nowPage<this.start){
						this.pageNum=5
						if(nowPage-4<=0){
							this.start=1
							this.end=5
						}else{
							this.start=nowPage-4
							this.end=nowPage
						}
					}
				},
				pun:function(id){
					$.post("punish",{"id":id})
					$("#pun").parent().parent().remove()
				},
				unPun:function(id){
					$.post("unPunish",{"id":id})
					$("#unPun").parent().parent().remove()
				},
				cancel:function(id){
					$.post("revocation",{"id":id})
					$("#cancel").parent().parent().remove()
				}
				
			},
			created: function(){
				console.log(this.users)
				this.disabled()
			},
			filters: {
				formatText(text){
					var reg=new RegExp(/\<p\>([\w\s\.\u4e00-\u9fa5]+)\<\/p\>/g)
					text=reg.exec(text)
					text=text[1].substring(0,155)
					text+=" ..."
					return text
				}
			}
		})
	</script>
</body>
</html>