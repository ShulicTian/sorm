package web.ssm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.ssm.po.user.User;
import web.ssm.po.user.Useraddress;
import web.ssm.service.UserService;

@Controller
@Scope(value="prototype")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService us;
	
	@RequestMapping("/reg")
	public ModelAndView register(@RequestParam(value="name",required=true) String name,@RequestParam(value="password",required=true) String password) {
		
		us.insertUser(name, password, new Date());
		
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value="name",required=true) String name,@RequestParam(value="password",required=true) String password,HttpServletRequest request) {
		User user = us.getUser(name, password);
		ModelAndView mav = null;
		if(user!=null) {
			mav = new ModelAndView("index");
 			mav.addObject("user",user);
 			request.getSession().setAttribute("user", user);
		}else {
			mav = new ModelAndView("login");
			mav.addObject("msg","用户名或密码错误！");
		}
		
		return mav;
	}
	
	@RequestMapping("/exit")
	public ModelAndView exit(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		ModelAndView mav = null;
			mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = null;
			mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/userindex")
	public ModelAndView userIndex(HttpServletRequest request) {
		ModelAndView mav = null;
			mav = new ModelAndView("userindex");
		return mav;
	}
	
	@RequestMapping("/usermsg")
	public ModelAndView userMessage(HttpServletRequest request) {
		request.getSession().setAttribute("clickmsg", "off");
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = null;
		mav = new ModelAndView("usermsg");
		mav.addObject("user",user);
		mav.addObject("usermsg", us.getMsg(user));
		return mav;
	}
	
	@RequestMapping("/changemsg")
	public ModelAndView changeMessage(HttpServletRequest request) {
		request.getSession().setAttribute("clickmsg", "no");
		User user = (User) request.getSession().getAttribute("user");
		ModelAndView mav = null;
		mav = new ModelAndView("usermsg");
		mav.addObject("user",user);
		mav.addObject("usermsg", us.getMsg(user));
		return mav;
	}
	
	@RequestMapping("/setusermsg")
	public ModelAndView setUserMessage(HttpServletRequest request,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="age",required=false) int age,
			@RequestParam(value="idCard",required=false) String idCard,
			@RequestParam(value="youslftext",required=false) String youslftext,
			@RequestParam(value="day",required=false) String day) {
		
		User user = (User) request.getSession().getAttribute("user");
		us.setMsg(user, name, age, day, idCard, youslftext);
		request.getSession().setAttribute("clickmsg", "off");
		ModelAndView mav = null;
		mav = new ModelAndView("usermsg");
		mav.addObject("user",user);
		mav.addObject("usermsg", us.getMsg(user));
		System.out.println(name);
		return mav;
	}
	
	@RequestMapping("/useradd")
	public ModelAndView userAddress(HttpServletRequest request) {
		request.getSession().setAttribute("clickadd", "off");
		User user=(User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("useradd", us.getAdd(user));
		ModelAndView mav = null;
		mav = new ModelAndView("useradd");
		return mav;
	}
	
	@RequestMapping("/changeadd")
	public ModelAndView changeAddress(HttpServletRequest request,@RequestParam(value="id",required=true) int id) {
		request.getSession().setAttribute("clickadd", "no");
		List<Useraddress> add = (List<Useraddress>) request.getSession().getAttribute("useradd");
		Useraddress useradd=add.get(id);
		ModelAndView mav = null;
		mav = new ModelAndView("useradd");
		mav.addObject("oneuseradd", useradd);
		return mav;
	}
	
	@RequestMapping("/setuseradd")
	public ModelAndView setUserAddress(HttpServletRequest request,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="phonenumber",required=false) String phonenumber,
			@RequestParam(value="ip",required=false) String ip
			) {
		User user = (User) request.getSession().getAttribute("user");
		us.setAdd(user, name, phonenumber,ip);
		request.getSession().setAttribute("clickadd", "off");
		ModelAndView mav = null;
		mav = new ModelAndView("useradd");
		return mav;
	}
	
	@RequestMapping("/addpage")
	public ModelAndView userAdd(HttpServletRequest request) {
		ModelAndView mav = null;
		mav = new ModelAndView("adduseradd");
		return mav;
	}
	
	@RequestMapping("/adduseradd")
	public ModelAndView addUseradd(HttpServletRequest request,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="phonenumber",required=false) String phonenumber,
			@RequestParam(value="ip",required=false) String ip
			) {
		User user = (User) request.getSession().getAttribute("user");
		us.adduseradd(user, name, phonenumber,ip);
		request.getSession().setAttribute("useradd", us.getAdd(user));
		ModelAndView mav = null;
		mav = new ModelAndView("useradd");
		return mav;
	}
	
	@RequestMapping("/sell")
	public ModelAndView sell() {
		
		ModelAndView mav = null;
		mav = new ModelAndView("sell");
		return mav;
	}
	
}
