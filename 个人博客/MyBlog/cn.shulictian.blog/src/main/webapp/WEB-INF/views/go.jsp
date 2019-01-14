<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="shulictian.ssm.po.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/go.css">
<title>Insert title here</title>
</head>
<body>
	<div id="topCtx">
		<div>
			<jsp:include page="head.jsp"></jsp:include>
		</div>
		<div style="width: 100%;">
			<div style="width: 5%; height: 100px; float: left;"></div>
			<div id="rContent">
				<div>
					<div id="userMsgs" style="background-color: #393D49;">个人资料</div>
					<div id="photo">
						<img id="headImg" :src="'img/user/'+userid+'.jpg'"
							style="width: 148px; height: 148px;" />
					</div>
					<div id="name">
						<a id="userName" v-bind:href="'bigCow/user/getUserCus/'+userid">{{nickName}}</a>
					</div>
					<div style="width: 100%; text-align: center;">
						<div id="att" class="layui-btn layui-btn-primary layui-btn-xs"
							@click="att(userid)">
							<i class="layui-icon"></i>{{attVal}}
						</div>
						<div class="layui-btn layui-btn-primary layui-btn-xs">
							<i class="layui-icon"></i>订阅
						</div>
						<hr style="margin-bottom: 0px;" />
						<div id="userMsg">
							<div style="padding: 5px; margin-left: 20px; float: left;">
								<span>原创：{{original}} 篇</span><br /> <span>译文：{{translation}}
									篇</span>
							</div>
							<div style="padding: 5px 5px; float: right;">
								<span>转载：{{reprint}} 篇</span><br /> <span>评论：{{comments}}
									条</span>
							</div>
						</div>
						<hr>
					</div>
				</div>

				<div>
					<div id="gitTit">GitHub分享站</div>
					<hr style="margin: 0px;" />
					<div id="gitText">
						<h6 style="margin-top: 10px;">主页网址：</h6>
						<p>
							<a :href="git" target="_blank">{{git}}</a>
						</p>
						<hr />
						<div v-for="pro in pros">
							<h6>项目名称：{{pro.name}}</h6>
							<h6>项目简介：</h6>
							<p>{{pro.descs}}</p>
							<h6>
								项目路径：<a :href="git+pro.address" target="_blank">{{pro.address}}</a>
							</h6>
							<hr />
						</div>
					</div>
				</div>
			</div>

			<div id="text">
				<div id="paper">
					<h3 style="padding: 30px; padding-bottom: 0px">${top.title}</h3>
					<div id="tit2" style="padding-left: 40px;">
						<div class="layui-btn layui-btn-primary layui-btn-disabled"
							style="display: inline;">{{getGenreName()}}</div>
						<div style="display: inline;">
							&nbsp;<sub>{{sendTime | formatDate}}</sub>
						</div>
						<div
							style="display: inline; float: right; padding: 10px 10px 0 0;">
							<i id="btn" disabled="disabled" class="easyui-linkbutton"
								iconCls="icon-man"></i><sub>${top.readNum}</sub>
						</div>
					</div>
					<hr />
					<div id="ctx">${top.text}</div>
					<hr />
				</div>
				<div id="comments"
					style="background-color: white; width: 96%; margin-left: 22px; float: left; margin-bottom: 15px;">
					<div id="contents">
						<div v-if="isHave()"
							style="width: 96%; margin-left: 22px; float: left; margin-top: 5px; height: 50px;">还没有人评论哦，快来抢占沙发吧！</div>
						<div v-for="cont in conts" class="cmts">
							<hr style="margin: 0px;" />
							<div style="margin-top: 15px; padding: 0px 30px;">
								<div id="otherPhoto"
									style="width: 35px; height: 35px; float: left;">
									<img :src="'img/user/'+cont.user.id+'.jpg'"
										style="width: 35px; height: 35px;" />
								</div>
								<div
									style="float: left; padding-left: 10px; margin-top: 13px; color: gray;">{{cont.user.nickName}}</div>
								<div style="float: right;">
									<c:if test="${user!=null}">
										<a href="javascript:void(0)" @click="del(cont.id)"
											v-if="cont.user.id==${user.id}">删除</a>
										<a v-else>回复</a>
									</c:if>
								</div>
							</div>
							<div
								style="padding: 5px; clear: both; padding-left: 75px; width: 80%; padding-top: 15px; font-size: 14px;">{{cont.content}}</div>
							<div style="float: right; padding: 5px;">{{cont.sendTime |
								formatDate}}</div>
						</div>
					</div>
					<div id="comPage" style="text-align: center;"></div>
				</div>
				<div id="comment"
					style="width: 96%; margin-left: 22px; float: left;">
					<form action="" method="post">
						<input type="hidden" name="topId" value="${top.id}" />
						<textarea rows="3" cols="3" name="content" class="layui-textarea"
							placeholder="评论。。。"></textarea>
						<input type="submit" @click="sendCom" value="发布评论"
							class="layui-btn layui-btn-primary" /> <input type="reset"
							value="清空" class="layui-btn layui-btn-primary" />
					</form>
				</div>
			</div>

			<div id="lContent">
				<div class="site-demo-laydate">
					<div class="layui-inline" id="dateView"></div>
				</div>
				<div class="layui-carousel" id="ad2"
					style="margin: auto; margin-top: 5px;">
					<div carousel-item="">
						<div>
							<img src="img/advertising/index/3.jpg" />
						</div>
						<div>
							<img src="img/advertising/index/4.jpg" />
						</div>
						<div>
							<img src="img/topic/71.jpg" />
						</div>
					</div>
				</div>
				<div id="topList">
					<h6>博主最新佳作</h6>
					<div class="topTit" style="">
						<div v-if="jud(newTop)">暂时没有帖子</div>
						<p v-for="t1 in newTop">
							<a v-bind:href="'bigCow/topic/get/'+t1.id">{{t1.title}}</a>
						</p>
					</div>
					<h6>博主最热佳作</h6>
					<div class="topTit"
						style="width: 100%; text-align: left; padding: 5px;">
						<div v-if="jud(hotTop)">暂时没有帖子</div>
						<p v-for="t2 in hotTop">
							<a v-bind:href="'bigCow/topic/get/'+t2.id">{{t2.title}}</a>
						</p>
					</div>
					<h6>Vue.js</h6>
					<div class="topTit"
						style="width: 100%; text-align: left; padding: 5px;">
						<div v-if="jud(newTypeTop)">暂时没有帖子</div>
						<p v-for="t3 in newTypeTop">
							<a v-bind:href="'bigCow/topic/get/'+t3.id">{{t3.title}}</a>
						</p>
					</div>
					<h6>相关推荐</h6>
					<div class="topTit"
						style="width: 100%; text-align: left; padding: 5px;">
						<div v-if="jud(relatedTop)">暂时没有帖子</div>
						<p v-for="t4 in relatedTop">
							<a v-bind:href="'bigCow/topic/get/'+t4.id">{{t4.title}}</a>
						</p>
					</div>
					<h6>其他热门</h6>
					<div class="topTit"
						style="width: 100%; text-align: left; padding: 5px;">
						<div v-if="jud(allTopHot)">暂时没有帖子</div>
						<p v-for="t5 in allTopHot">
							<a v-bind:href="'bigCow/topic/get/'+t5.id">{{t5.title}}</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
$(document).ready(function(){
	$("#photo").mouseenter(function(){
		$(this).css({"border":"1px solid gray"})
	})
	$("#photo").mouseout(function(){
		$(this).css({"border":"none"})
	})
	$("#photo").click(function(){
		var href = $("#userName").attr("href")
		window.open(href)
	})
})
</script>
	<script type="text/javascript">
layui.use(['laydate','laypage','layer','element'] , function(){
	  var laydate = layui.laydate,
	  layer = layui.layer,
	  laypage = layui.laypage,
	  element = layui.element;
	  
	  laydate.render({
	    elem: '#dateView'
	    ,position: 'static'
	    ,showBottom: false
	    ,trigger: 'click'
	  });
	  
	  laypage.render({
		    elem: 'comPage'
		    ,count: app.count
		    ,limit: 5
		    ,theme: '#1E9FFF'
		    ,jump: function(obj, first){
		    	 $.post('bigCow/comment/commentPage',{'nowPage':obj.curr,"topId":${top.id}},function(data){
						app.conts=JSON.parse(data.data.comments.pageJSON)
					},'json')
		     }
		  });
	  
});

layui.use(['carousel'], function(){
	  var carousel = layui.carousel
	  carousel.render({
		    elem: '#ad2'
		    ,width: '97%'
		    ,height: '270px'
		    ,interval: 4000
		  });
	  
	});

</script>
	<script type="text/javascript">

var app=new Vue({
	el: '#topCtx',
	data: {
		original: ${top.userCus}.zeroNum,
		reprint: ${top.userCus}.oneNum,
		translation: ${top.userCus}.towNum,
		comments: ${top.userCus}.commentNum,
		allTopHot: [],
		relatedTop: [],
		newTypeTop: [],
		hotTop: [],
		newTop: [],
		userid: ${top.userCus}.id,
		nickName: ${top.userCus}.nickName,
		nowtype: ${top.type},
		nowGenre: ${top.genre},
		sendTime: ${top.sendTime},
		attVal:'关注',
		conts: [],
		count: '',
		pros: [],
		git: 'https://www.github.com/'
	},
	methods: {
		getUserTops: function(id,type){
			var temp=this;
			$.post("bigCow/topic/getAuthorNew",{'id':id},function(data){
				temp.newTop=JSON.parse(data.data.topics)
			},'json')
			
			$.post("bigCow/topic/getAuthorHot",{'id':id},function(data){
				temp.hotTop=JSON.parse(data.data.topics)
			},'json')
			
			$.post("bigCow/topic/getNewByType",{'type':type},function(data){
				temp.newTypeTop=JSON.parse(data.data.topics)
			},'json')
			
			/* $.post("relatedTop",{'type':type-1},function(data){
				temp.relatedTop=data
			},'json') */
			
			$.post("bigCow/topic/getGlobalHot",function(data){
				temp.allTopHot=JSON.parse(data.data.topics)
			},'json')
			
			var topId=${top.id}
			$.post('bigCow/comment/getComments',{'topId':topId},function(data){
				temp.count=data.data.comments.allNum
				temp.conts=JSON.parse(data.data.comments.pageJSON)
			},'json')
		},
		jud: function(top){
			if(top.length<=0){
				return true
			}
			return false
		},
		getGenreName: function(){
			var a = this.nowGenre
			if(a==0){
				return "原创"
			}else if(a==1){
				return "转载"
			}else if(a==2){
				return "译文"
			}else{
				return ""
			}
		},
		att:function(userId){
			if(${user!=null} && ${user!=""}){
				var temp=this
				if(this.attVal=="关注"){
					$("#att i").css({"display":"none"})
					$("#att").css({"color":"gray"})
					temp.attVal="取消关注"
					$.post("bigCow/other/attention",{"userId":userId})
				}else if(this.attVal=="取消关注"){
					$("#att i").css({"display":"inline"})
					$("#att").css({"color":"black"})
					temp.attVal="关注"
					$.post("bigCow/other/cancelAtt",{"userId":userId})
				}
			}else{
				$(location).attr('href', 'bigCow/page/toGlobalLogin');
			}
		},
		Judatt:function(userId){
			var temp=this
			$.post("bigCow/other/judAtt",{"userId":userId},function(data){
				if(data=="yes"){
					$("#att i").css({"display":"none"})
					$("#att").css({"color":"gray"})
					temp.attVal="取消关注"
				}else if(data=="no"){
					temp.attVal="关注"
				}
			})
		},
		isHave:function(){
			if(this.conts.length<=0){
				return true
			}else{
			return false
			}
		},
		sendCom:function(){
			if(${user==null} || ${user==""}){
				alert("登入之后才能评论哦！")
				$("form").attr("action","bigCow/page/toGlobalLogin")
			}else{
				$("form").attr("action","bigCow/comment/sendComment")
			}
		},del:function(id){
			$.post("delComments",{"id":id})
		},
		getPros:function(){
			var temp=this
			var userId=this.userid
			$.post("bigCow/other/getPro",{"userId":userId},function(data){
				temp.pros=data
			},"json")
		},
		getGit:function(){
			var temp=this
			var userId=this.userid
			$.post("bigCow/other/getGit",{"userId":userId},function(data){
				temp.git+=data
			})
		}
	},
	created: function(){
		this.getPros()
		this.getGit()
		this.getUserTops(this.userid,this.nowtype)
		if(${user!=null} && ${user!=""}){
			this.Judatt(this.userid)
		}
	},
	filters: {
		formatDate: function(date){
			date = new Date(date)
			var m=(date.getMinutes())<9?'0'+(date.getMinutes()):(date.getMinutes())
			return date=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+m
		}
	}
	
})

</script>
</body>
</html>