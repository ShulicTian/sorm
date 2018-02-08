package shulictian.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import shulictian.ssm.po.ProMsg;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.service.UserService;
import shulictian.ssm.util.PageUtil;
import shulictian.ssm.util.Token;
import shulictian.ssm.util.ValidUserUtils;

@Controller
public class UserHandler {
	
	@Autowired
	UserService us = null;
	
	/**
	 * 进入登入注册页面
	 */
	@RequestMapping("/logreg")
	public String tologreg(HttpServletRequest request) {
		return "logreg";
	}
	
	/**
	 * 跳转注册页面
	 */
	@RequestMapping("/toreg")
	public String toReg(HttpServletRequest request) {
		request.getSession().setAttribute("token", Token.getToken().makeToken());
		return "reg";
	}
	
	/**
	 * 跳转登入页面
	 */
	@RequestMapping("/tolog")
	public String toLog() {
		return "login";
	}
	
	/**
	 * 进行注册入库
	 * @param user 填写的用户表单信息
	 */
	@RequestMapping("/reg")
	public String register(User user,HttpServletRequest request) {
		
		String result=ValidUserUtils.validToken(request);
		request.getSession().removeAttribute("token");
		if(result.equals("regsuccess")) {
			us.saveUser(user);
		}
		
		return result;
	}
	
	/**
	 * 进行登入
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request) {
		
		if(us.getUser(user.getUserName(), user.getPassWord())!=null) {
			request.getSession().setAttribute("user", us.getUser(user.getUserName(), user.getPassWord()));
		}else {
			
			return "redirect:index.jsp?msg='no'";
		}
		
		return "redirect:/";
	}
	
	/**
	 * 对注册的数据格式进行验证
	 * @param bindingResult 用来获取数据格式的错误信息
	 */
	@RequestMapping("/getUserNames")
	public void getUserNames(@Valid User user,BindingResult bindingResult,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(ValidUserUtils.validForm(user, bindingResult, us.getAllUserName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注销用户
	 * @param session
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * 更新State
	 */
	@RequestMapping("/upUserState")
	public void upUserState(Map<String,Object> map) {
		us.updateState(map);
	}
	
	/**
	 * 根据id查找UserCustom
	 */
	@RequestMapping("/getUserCus/{id}")
	public String getUserCusById(@PathVariable("id") Integer id,Map<String,Object> map) {
		map.put("userCus", us.findUser(id));
		return "homePage";
	}
	
	List<User> ucList = new ArrayList<>();
	
	/**
	 * 根据id查找粉丝
	 */
	@RequestMapping("/getFans")
	public String getFans(Map<String,Object> map,HttpServletRequest request) {
		ucList=null;
		ucList=us.findFans(((User)request.getSession().getAttribute("user")).getId());
		map.put("pages", PageUtil.getUserPage(1, ucList));
		return "fansAndidol";
	}
	
	/**
	 * 根据id查找已关注
	 */
	@RequestMapping("/getAtt")
	public String getAtt(Map<String,Object> map,HttpServletRequest request) {
		ucList=null;
		ucList=us.findAtt(((User)request.getSession().getAttribute("user")).getId());
		map.put("pages", PageUtil.getUserPage(1, ucList));
		return "fansAndidol";
	}
	/**
	 * User分页
	 */
	@ResponseBody
	@RequestMapping("/userPage")
	public String page(@RequestParam int nowPage) {
		return PageUtil.getUserPage(nowPage, ucList).getPageTops();
	}
	
	/**
	 * 更新user
	 */
	@RequestMapping("/upUser")
	public void upUser(User user,HttpServletRequest request) {
		user.setId( ((User)request.getSession().getAttribute("user")).getId() );
		us.upUser(user);
		if(user.getNickName()!=null||user.getNickName()!="") {
			((User)request.getSession().getAttribute("user")).setNickName(user.getNickName());
		}
	}
	
	/**
	 * 更改密码
	 */
	@RequestMapping("/upPwd")
	public void upPwd(@RequestParam String oldPwd,@RequestParam String newPwd,HttpServletRequest request) {
		if(((User)request.getSession().getAttribute("user")).getPassWord().equals(oldPwd)) {
			us.upPwd(((User)request.getSession().getAttribute("user")).getId(), newPwd);
		}
	}
	
	/**
	 * 更新用户项目信息
	 */
	@RequestMapping("/upPro")
	public String upPro(ProMsg pm,Map<String,Object> map,HttpServletRequest request) {
		pm.setUserId(((User)request.getSession().getAttribute("user")).getId());
		us.upPro(pm);
		map.put("pros", JSONObject.toJSONString(us.getPro(((User)request.getSession().getAttribute("user")).getId())));
		map.put("gitHome", us.getGit(((User)request.getSession().getAttribute("user")).getId()));
		return "shareGit";
	}
	
	/**
	 * 添加用户项目信息
	 */
	@RequestMapping("/addPro")
	public String addPro(ProMsg pm,Map<String,Object> map,HttpServletRequest request) {
		pm.setUserId(((User)request.getSession().getAttribute("user")).getId());
		us.addPro(pm);
		map.put("pros", JSONObject.toJSONString(us.getPro(((User)request.getSession().getAttribute("user")).getId())));
		map.put("gitHome", us.getGit(((User)request.getSession().getAttribute("user")).getId()));
		return "shareGit";
	}
	
	/**
	 * 更新用户Git主页
	 */
	@RequestMapping("/upGit")
	public String upGit(Map<String,Object> map,@RequestParam String gitHome,HttpServletRequest request) {
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		map.put("gitHome", gitHome);
		us.upGit(map);
		map.put("pros", JSONObject.toJSONString(us.getPro(((User)request.getSession().getAttribute("user")).getId())));
		return "shareGit";
	}
	
	
/*	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){  
		webDataBinder.addValidators(new ValidatorReg());  
	}*/
	
}
