package shulictian.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserMessage;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.service.UserService;
import shulictian.ssm.util.GetIps;
import shulictian.ssm.util.ImgUtil;
import shulictian.ssm.util.PageUtil;

@Controller
public class MenuHandler {
	
	@Resource
	private TopicService ts = null;
	@Resource
	private UserService us = null;
	
	private static List<TopicCustom> topics = new ArrayList<>();
	
	/**
	 * 首页   查找所有审核通过的帖子
	 */
	@RequestMapping("/")
	public String toIndex(Map<String,Object> map) {
		topics=ts.getTops();
		map.put("pages", PageUtil.getPage(1, topics));
		
		return "index";
	}
	
	/**
	 * 获取当页的帖子
	 */
	@ResponseBody
	@RequestMapping("/page")
	public String page(@RequestParam int nowPage) {
		
		return PageUtil.getPage(nowPage, topics).getPageTops();
	}
	
	/**
	 * 获取图片
	 */
	@RequestMapping("/getImg")
	public void getImgs(@RequestParam String url,HttpServletResponse response) {
		ImgUtil.getImg(url, response);
	}
	
	/**
	 * 个人主页
	 */
	@RequestMapping("/homePage")
	public String toOneIndex(HttpServletRequest request,Map<String,Object> map) {
		map.put("userCus", us.findUser( ((User)request.getSession().getAttribute("user")).getId() ));
		return "homePage";
	}
	
	/**
	 * 博客管理
	 */
	@RequestMapping("/toBlog")
	public String toBlog(Map<String,Object> map,HttpServletRequest request) {
		return "blogManage";
	}
	
	/**
	 * 修改资料 Page
	 */
	@RequestMapping("/toUpMsg")
	public ModelAndView toUpMsg(Map<String,Object> map,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("upMsg");
		mav.addObject("ips", GetIps.getIps());
		mav.addObject("msgs",us.getUsermsg( ((User)request.getSession().getAttribute("user")).getId() ));
		return mav;
	}
	
	/**
	 * 修改资料
	 */
	@RequestMapping("/upMsg")
	public ModelAndView upMsg(UserMessage usermsg,HttpServletRequest request) {
		usermsg.setUserId(((User)request.getSession().getAttribute("user")).getId());
		System.out.println(usermsg.getProvince());
		System.out.println(usermsg.getCity());
		us.upMsg(usermsg);
		ModelAndView mav = new ModelAndView("upMsg");
		mav.addObject("msgs",usermsg);
		mav.addObject("ips", GetIps.getIps());
		return mav;
	}
	
	/**
	 * 修改密码 Page
	 */
	@RequestMapping("/toPwd")
	public String toPwd() {
		return "upPwd";
	}
	
	/**
	 * 设置Git分享 Page
	 */
	@RequestMapping("/toGit")
	public String toGit(Map<String,Object> map,HttpServletRequest request) {
		map.put("pros", JSONObject.toJSONString(us.getPro( ((User)request.getSession().getAttribute("user")).getId() )));
		map.put("gitHome", us.getGit(((User)request.getSession().getAttribute("user")).getId()));
		return "shareGit";
	}
	
	List<TopicCustom> genreList = new ArrayList<>();
	/**
	 * 管理我的原创 Page
	 */
	@RequestMapping("/toZero")
	public String toZero(Map<String,Object> map,HttpServletRequest request) {
		genreList = null;
		map.put("genre",0);
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		genreList = ts.findTopByGenre(map);
		map.put("pages", PageUtil.getPage(1, genreList));
		return "setTopic";
	}
	@ResponseBody
	@RequestMapping("/zeroPage")
	public String zeroPage(@RequestParam Integer nowPage) {
		return PageUtil.getPage(nowPage, genreList).getPageTops();
	}
	
	/**
	 * 管理我的转发 Page
	 */
	@RequestMapping("/toOne")
	public String toOne(Map<String,Object> map,HttpServletRequest request) {
		genreList = null;
		map.put("genre",1);
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		genreList = ts.findTopByGenre(map);
		map.put("pages", PageUtil.getPage(1, genreList));
		return "setTopic";
	}
	@ResponseBody
	@RequestMapping("/onePage")
	public String onePage(@RequestParam Integer nowPage) {
		return PageUtil.getPage(nowPage, genreList).getPageTops();
	}
	
	/**
	 * 管理我的译文 Page
	 */
	@RequestMapping("/toTow")
	public String toTow(Map<String,Object> map,HttpServletRequest request) {
		genreList = null;
		map.put("genre",2);
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		genreList = ts.findTopByGenre(map);
		map.put("pages", PageUtil.getPage(1, genreList));
		return "setTopic";
	}
	@ResponseBody
	@RequestMapping("/towPage")
	public String towPage(@RequestParam Integer nowPage) {
		return PageUtil.getPage(nowPage, genreList).getPageTops();
	}
	
	List<Topic> stateList = new ArrayList<>();
	
	/**
	 * 草稿箱  Page
	 */
	@RequestMapping("/toDraft")
	public String toDraft(Map<String,Object> map,HttpServletRequest request) {
		stateList = null;
		map.put("state",TopicStateEnum.DRAFT.getCode());
		map.put("id", ((User)request.getSession().getAttribute("user")).getId());
		stateList = ts.getAudit(map);
		map.put("pages", PageUtil.getAdminPage(1, stateList));
		return "handlerTopic";
	}
	@ResponseBody
	@RequestMapping("/draftPage")
	public String draftPage(@RequestParam Integer nowPage) {
		return PageUtil.getAdminPage(nowPage, stateList).getPageTops();
	}
	
	/**
	 * 垃圾箱 Page
	 */
	@RequestMapping("/toDustbin")
	public String toDustbin(Map<String,Object> map,HttpServletRequest request) {
		stateList = null;
		map.put("state",TopicStateEnum.REMOVE.getCode());
		map.put("id", ((User)request.getSession().getAttribute("user")).getId());
		stateList = ts.getRems(map);
		map.put("pages", PageUtil.getAdminPage(1, stateList));
		return "handlerTopic";
	}
	@ResponseBody
	@RequestMapping("/dustbinPage")
	public String dustbinPage(@RequestParam Integer nowPage) {
		return PageUtil.getAdminPage(nowPage, stateList).getPageTops();
	}
	
	List<TopicCustom> solist = new ArrayList<>();
	/**
	 * 搜索帖子
	 */
	@RequestMapping("/soTop")
	public String soTop(@RequestParam String sele,Map<String,Object> map) {
		solist=ts.soTop(sele);
		map.put("pages", PageUtil.getPage(1, solist));
		return "soPage";
	}
	
	/**
	 * 获取当页的帖子
	 */
	@ResponseBody
	@RequestMapping("/soPage")
	public String soPage(@RequestParam int nowPage) {
		return PageUtil.getPage(nowPage,solist).getPageTops();
	}

}
