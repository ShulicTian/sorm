package shulictian.ssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.UserTypeEnum;
import shulictian.ssm.service.AdminService;
import shulictian.ssm.service.MenuService;
import shulictian.ssm.service.PagingService;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.util.PageUtil;

/**
 * @Date 2018/3/6 22:56
 * @author ShulicTian
 * @Desc 分页处理
 */
@Controller
@RequestMapping(value = "/bigCow/paging")
public class PagingController extends BaseController {

	@Autowired
	private PagingService ps = null;

	@Autowired
	private TopicService ts = null;

	@Autowired
	private MenuService ms = null;

	@Autowired
	private AdminService as = null;

	/**
	 * @Desc 主页
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/firstPage")
	public void firstPage(@RequestParam("nowPage") int nowPage) {
		printJSON("topics", PageUtil.getPage(nowPage, ps.firstPage(nowPage, 10), 10).getPageJSON());
	}

	/**
	 * @Desc 获取用户草稿箱帖子
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/draft")
	public void draft(@RequestParam int nowPage, @RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("state", TopicStateEnum.DRAFT.getCode());
		printJSON("topics", PageUtil.getPage(nowPage, ts.getTopicByState(map), 7).getPageJSON());
	}

	/**
	 * @Desc 获取用户垃圾箱文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/dustbin")
	public void dustbin(@RequestParam int nowPage, @RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("state", TopicStateEnum.REMOVE.getCode());
		printJSON("topics", PageUtil.getPage(nowPage, ts.getTopicByState(map), 7).getPageJSON());
	}

	/**
	 * @Desc 根据关键字搜索文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/find")
	public void find(@RequestParam int nowPage, @RequestParam("sele") String sele) {
		printJSON("topics", PageUtil.getPage(nowPage, ts.soTop(sele), 10).getPageJSON());
	}

	/**
	 * @Desc 根据文章类型获取文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/topicType")
	public void topicType(@RequestParam int nowPage, @RequestParam("type") int type) {
		printJSON("topics", PageUtil.getPage(nowPage, ts.getType(type), 10).getPageJSON());
	}

	/**
	 * @Desc 获取用户自发文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/userTopic")
	public void userTopic(@RequestParam int nowPage, @RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("state", TopicStateEnum.PASS.getCode());
		printJSON("topics", PageUtil.getPage(nowPage, ts.getTopicByState(map), 10).getPageJSON());
	}

	/**
	 * @Desc 根据genre查找用户文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/genreTopic")
	public void genreTopic(@RequestParam int nowPage, @RequestParam("userId") int userId,
			@RequestParam("genre") int genre, @RequestParam("num") int num, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("genre", genre);
		printJSON("topics", PageUtil.getPage(nowPage, ts.findTopByGenre(map), num).getPageJSON());
	}

	/**
	 * @Desc 个人主页查询文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/homeFind")
	public void homeFind(@RequestParam("nowPage") int nowPage, @RequestParam("userId") int userId,
			@RequestParam("ctx") String ctx, Map<String, Object> map) {
		map.put("userId", userId);
		map.put("ctx", ctx);
		printJSON("topics", PageUtil.getPage(nowPage, ts.homeFind(map), 10).getPageJSON());
	}

	/**
	 * @Desc 审核文章
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/audit")
	public void auditPage(@RequestParam("nowPage") int nowPage) {
		printJSON("topics", PageUtil.getPage(nowPage, as.getAuditAdmin(), 7).getPageJSON());
	}

	/**
	 * @Desc 文章管理
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/manage")
	public void manage(@RequestParam("nowPage") int nowPage) {
		printJSON("topics", PageUtil.getPage(nowPage, as.getDeleteAdmin(), 7).getPageJSON());
	}

	/**
	 * @Desc 用户管理
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/userManage")
	public void userManage(@RequestParam("nowPage") int nowPage) {
		printJSON("users",
				PageUtil.getPage(nowPage, as.getUserByType(UserTypeEnum.COMMON.getCode()), 10).getPageJSON());
	}

	/**
	 * @Desc 封号
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/punish")
	public void punish(@RequestParam int nowPage) {
		printJSON("users",
				PageUtil.getPage(nowPage, as.getUserByType(UserTypeEnum.PUNISH.getCode()), 10).getPageJSON());
	}

	/**
	 * @Desc 粉丝
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/fans")
	public void fans(@RequestParam int nowPage, @RequestParam("userId") int userId) {
		printJSON("users", PageUtil.getPage(nowPage, ms.findFans(userId), 10).getPageJSON());
	}

	/**
	 * @Desc 大神
	 * @param nowPage
	 */
	@ResponseBody
	@RequestMapping("/atts")
	public void atts(@RequestParam int nowPage, @RequestParam("userId") int userId) {
		printJSON("users", PageUtil.getPage(nowPage, ms.findAtt(userId), 10).getPageJSON());
	}

}
