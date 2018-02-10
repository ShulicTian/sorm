package shulictian.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.util.PageUtil;

@Controller
public class TopicHandler {
	
	@Autowired
	TopicService ts=null;
	
	/**
	 * 首页获取帖子
	 */
	@ResponseBody
	@RequestMapping("/first")
	public String firstTop() {
		return JSONArray.toJSONString(ts.getTops(),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取今日最新发布的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/newAllTop")
	public String findAllTopNew() {
		return JSONArray.toJSONString(ts.findAllTopNew(),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取博主最新发布的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/newTop")
	public String getUserTopNew(@RequestParam("id") int id) {
		return JSONArray.toJSONString(ts.getUserTopNew(id),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取博主最热门的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/hotTop")
	public String getUserTopHot(@RequestParam("id") int id) {
		return JSONArray.toJSONString(ts.getUserTopHot(id),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取当前文章type的最新5篇文章
	 */
	@ResponseBody
	@RequestMapping("/newTypeTop")
	public String getTypeNew(@RequestParam("type") int type) {
		return JSONArray.toJSONString(ts.getTypeNew(type),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取当前文章相关的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/relatedTop")
	public String getRelatedTop(@RequestParam("type") int type) {
		return JSONArray.toJSONString(ts.getRelatedTop(type),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 获取所有文章热度前五的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/allTopHot")
	public String getAllTopHot() {
		return JSONArray.toJSONString(ts.getAllTopHot(),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 恢复垃圾箱帖子
	 */
	@RequestMapping("/recoverTop")
	public void recoverTop(@RequestParam("id") int id,Map<String,Object> map) {
		map.put("topId", id);
		ts.recoverTop(map);
	}
	
	/**
	 * 删除帖子到垃圾箱
	 */
	@RequestMapping("/removeTop")
	public void remove(@RequestParam("id") int id,Map<String,Object> map) {
		map.put("topId", id);
		ts.removeTop(map);
	}
	
	/**
	 * 彻底删除帖子
	 */
	@RequestMapping("/deleteTop")
	public void delete(@RequestParam("id") int id,Map<String,Object> map) {
		map.put("topId", id);
		ts.deleteTop(map);
	}
	
	@RequestMapping("/toadd")
	public String toAdd() {
		return "addTopic";
	}
	
	/**
	 * 添加帖子
	 */
	@RequestMapping("/addTop")
	public String saveTopic(Topic topic,HttpServletRequest request,Map<String,Object> map) {
		topic.setUser((User)request.getSession().getAttribute("user"));
		ts.saveTopic(topic,map);
		return "addsuccess";
	}
	
	/**
	 * 添加草稿
	 */
	@RequestMapping("/addDraft")
	public String addDraft(Topic topic,HttpServletRequest request,Map<String,Object> map) {
		topic.setUser((User)request.getSession().getAttribute("user"));
		ts.saveDraft(topic,map);
		return "addsuccess";
	}
	
	/**
	 * 根据id查找帖子
	 */
	@RequestMapping("/getTopic/{id}")
	public String getTopic(@PathVariable("id") int id,HttpServletRequest request,Map<String,Object> map) {
		map.put("top", ts.getTopic(id));
		return "go";
	}
	
	List<TopicCustom> tcs = new ArrayList<>(); 
	
	/**
	 * 根据用户id获取其审核通过的帖子
	 */
	@ResponseBody
	@RequestMapping("/getUserTop")
	public String getUserTopic(@RequestParam int id) {
		tcs=ts.getUserTopic(id);
		return JSONArray.toJSONString(PageUtil.getPage(1, tcs),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * getUserTop分页
	 */
	@ResponseBody
	@RequestMapping("/getUserTopPage")
	public String getUserTopPage(@RequestParam int nowPage) {
		return PageUtil.getPage(nowPage,tcs).getPageTops();
	}
	
	/**
	 * 获取当前用户正在审核的帖子
	 */
	@RequestMapping("/getAudit")
	public String getAudit(Map<String,Object> map,HttpServletRequest request) {
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		map.put("state", TopicStateEnum.AUTHSTR.getCode());
		map.put("pages", PageUtil.getAdminPage(1,ts.getAudit(map)));
		
		return "oneIndex";
	}
	
	/**
	 * 获取当前用户审核没通过的帖子
	 */
	@RequestMapping("/getUnPass")
	public String unPass(Map<String,Object> map,HttpServletRequest request) {
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		map.put("state", TopicStateEnum.NOTPASS.getCode());
		map.put("pages", PageUtil.getAdminPage(1,ts.getUnPass(map)));
		
		return "oneIndex";
	}
	
	/**
	 * 获取当前用户已删除帖子
	 */
	@RequestMapping("/getRems")
	public String getRems(Map<String,Object> map,HttpServletRequest request) {
		map.put("userId", ((User)request.getSession().getAttribute("user")).getId());
		map.put("state", TopicStateEnum.DELETE.getCode());
		map.put("pages", PageUtil.getAdminPage(1,ts.getRems(map)));
		
		return "oneIndex";
	}
	
	private List<TopicCustom> types = new ArrayList<>();
	
	/**
	 * 根据type查找帖子
	 */
	@ResponseBody
	@RequestMapping("/getType")
	public String getType(@RequestParam Integer type) {
		types=ts.getType(type);
		return JSONObject.toJSONString(PageUtil.getPage(1,types),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * type分页
	 */
	@ResponseBody
	@RequestMapping("/typePage")
	public String typePage(@RequestParam Integer nowPage) {
		
		return PageUtil.getPage(nowPage,types).getPageTops();
	}

}
