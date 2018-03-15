package shulictian.ssm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicGenreEnum;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.util.ImgUtil;
import shulictian.ssm.util.PageUtil;
import shulictian.ssm.util.ValidUserUtils;

/**
 * @Date 2017/12/18
 * @author ShulicTian
 * @Desc 操作文章
 */
@Controller
@RequestMapping(value = "/bigCow/topic")
public class TopicController extends BaseController {

	@Autowired
	TopicService ts = null;

	/**
	 * @Desc 首页获取文章
	 */
	@ResponseBody
	@RequestMapping("/getForIndex")
	public void getForIndex(Map<String, Object> map) {
		printJSON("topics", ts.getTops());
	}

	/**
	 * @Desc 获取今日最新发布的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getNew")
	public void getNew() {
		printJSON("topics", ts.findAllTopNew());
	}

	/**
	 * @Desc 获取博主最新发布的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getAuthorNew")
	public void getAuthorNew(@RequestParam("id") int id) {
		printJSON("topics",
				JSONArray.toJSONString(ts.getUserTopNew(id), SerializerFeature.DisableCircularReferenceDetect));
	}

	/**
	 * @Desc 获取博主最热门的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getAuthorHot")
	public void getAuthorHot(@RequestParam("id") int id) {
		printJSON("topics",
				JSONArray.toJSONString(ts.getUserTopHot(id), SerializerFeature.DisableCircularReferenceDetect));
	}

	/**
	 * @Desc 获取当前文章type的最新5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getNewByType")
	public void getNewByType(@RequestParam("type") int type) {
		printJSON("topics",
				JSONArray.toJSONString(ts.getTypeNew(type), SerializerFeature.DisableCircularReferenceDetect));
	}

	/**
	 * @Desc 获取当前文章相关的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getRelated")
	public void getRelated(@RequestParam("type") int type) {
		printJSON("topics",
				JSONArray.toJSONString(ts.getRelatedTop(type), SerializerFeature.DisableCircularReferenceDetect));
	}

	/**
	 * @Desc 获取所有文章热度前五的5篇文章
	 */
	@ResponseBody
	@RequestMapping("/getGlobalHot")
	public void getGlobalHot() {
		printJSON("topics", ts.getAllTopHot());
	}

	/**
	 * @Desc 恢复垃圾箱文章
	 */
	@RequestMapping("/recover")
	public void recover(@RequestParam("topId") int topId, Map<String, Object> map, HttpServletRequest request) {
		map.put("state", TopicStateEnum.PASS.getCode());
		map.put("topId", topId);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		ts.upTopStateByState(map);
	}

	/**
	 * @Desc 删除文章到垃圾箱
	 */
	@RequestMapping("/remove")
	public void remove(@RequestParam("topId") int topId, Map<String, Object> map, HttpServletRequest request) {
		map.put("state", TopicStateEnum.REMOVE.getCode());
		map.put("topId", topId);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		ts.upTopStateByState(map);
	}

	/**
	 * @Desc 彻底删除文章
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam("topId") int topId, Map<String, Object> map, HttpServletRequest request) {
		map.put("state", TopicStateEnum.DELETE.getCode());
		map.put("topId", topId);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		ts.upTopStateByState(map);
	}

	/**
	 * @Desc 添加帖子
	 */
	@RequestMapping("/add")
	public String saveTopic(Topic topic, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String result = ValidUserUtils.validTopicToken(request);
		request.getSession().removeAttribute("topicToken");
		if (result.equals("addsuccess")) {
			topic.setUser((User) request.getSession().getAttribute("user"));
			int id = ts.saveTopic(topic);
			ImgUtil.saveImg(file, request, id);
		}
		return result;
	}

	/**
	 * @Desc 添加草稿
	 */
	@RequestMapping("/addDraft")
	public String addDraft(Topic topic, HttpServletRequest request) {
		topic.setUser((User) request.getSession().getAttribute("user"));
		topic.setState(TopicStateEnum.DRAFT.getCode());
		ts.saveDraft(topic);
		return "addsuccess";
	}

	/**
	 * @Desc 根据id查找帖子
	 */
	@RequestMapping("/get/{id}")
	public String getTopic(@PathVariable("id") int id, Map<String, Object> map) {
		map.put("top", JSONObject.parseObject(ts.getTopic(id)));
		return "go";
	}

	/**
	 * @Desc 根据用户id获取其审核通过的帖子
	 */
	@ResponseBody
	@RequestMapping("/getByUserId")
	public void getByUserId(@RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("state", TopicStateEnum.PASS.getCode());
		printJSON("topics", PageUtil.getPage(1, ts.getTopicByState(map), 10));
	}

	/**
	 * @Desc 根据用户id获取其原创
	 */
	@ResponseBody
	@RequestMapping("/getOriginalByUserId")
	public void getOriginalByUserId(@RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("genre", TopicGenreEnum.ORIGINAL.getCode());
		printJSON("topics", PageUtil.getPage(1, ts.findTopByGenre(map), 10));
	}

	/**
	 * @Desc 根据用户id获取其转发
	 */
	@ResponseBody
	@RequestMapping("/getTransmitByUserId")
	public void getTransmitByUserId(@RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("genre", TopicGenreEnum.TRANSMIT.getCode());
		printJSON("topics", PageUtil.getPage(1, ts.findTopByGenre(map), 10));
	}

	/**
	 * @Desc 根据用户id获取其翻译
	 */
	@ResponseBody
	@RequestMapping("/getTranslateByUserId")
	public void getTranslateByUserId(@RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("genre", TopicGenreEnum.TRANSLATION.getCode());
		printJSON("topics", PageUtil.getPage(1, ts.findTopByGenre(map), 10));
	}

	/**
	 * @Desc 获取当前用户正在审核的帖子
	 * 
	 * 		@RequestMapping("/getAudit") public String getAudit(Map<String,Object>
	 *       map,HttpServletRequest request) { map.put("userId",
	 *       ((User)request.getSession().getAttribute("user")).getId());
	 *       map.put("pages", PageUtil.getAdminPage(1,ts.getAudit(map)));
	 * 
	 *       return "oneIndex"; }
	 */

	/**
	 * @Desc 获取当前用户审核没通过的帖子
	 * 
	 * 		@RequestMapping("/getUnPass") public String unPass(Map<String,Object>
	 *       map,HttpServletRequest request) { map.put("userId",
	 *       ((User)request.getSession().getAttribute("user")).getId());
	 *       map.put("pages", PageUtil.getAdminPage(1,ts.getUnPass(map)));
	 * 
	 *       return "oneIndex"; }
	 */

	/**
	 * @Desc 根据type查找文章
	 */
	@ResponseBody
	@RequestMapping("/getByType")
	public void getType(@RequestParam("type") int type) {
		printJSON("topics", PageUtil.getPage(1, ts.getType(type), 10));
	}

	/**
	 * @Desc 个人主页查询文章
	 */
	@ResponseBody
	@RequestMapping("/findFromHome")
	public void homeFind(@RequestParam("userId") Integer userId, @RequestParam("ctx") String ctx,
			Map<String, Object> map) {
		map.put("userId", userId);
		map.put("ctx", ctx);
		printJSON("topics", PageUtil.getPage(1, ts.homeFind(map), 10));
	}

	/**
	 * @Desc 搜索帖子
	 */
	@RequestMapping("/soTop")
	public String soTop(@RequestParam("sele") String sele, Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, ts.soTop(sele), 10));
		map.put("sele", sele);
		return "soPage";
	}

}
