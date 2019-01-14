package shulictian.ssm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserTypeEnum;
import shulictian.ssm.service.AdminService;
import shulictian.ssm.service.MenuService;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.service.UserService;
import shulictian.ssm.util.GetIps;
import shulictian.ssm.util.PageUtil;
import shulictian.ssm.util.Token;

/**
 * @Date 2018/3/6 18:06
 * @author ShulicTian
 * @Desc 页面切换
 */
@Controller
@RequestMapping(value = "/bigCow/page")
public class GoPageController extends BaseController {

	@Autowired
	private TopicService ts = null;

	@Autowired
	private UserService us = null;

	@Autowired
	private AdminService as = null;

	@Autowired
	private MenuService ms = null;

	/**
	 * @Desc 首页
	 */
	@RequestMapping("/")
	public String toIndex(Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, ts.getTops(), 10));
		return "index";
	}

	/**
	 * @Desc 登入注册 Page
	 */
	@RequestMapping("/toGlobalLogin")
	public String toLoginPage(HttpServletRequest request) {
		return "logreg";
	}

	/**
	 * @Desc 注册 Page
	 */
	@RequestMapping("/toRegister")
	public String toRegPage(HttpServletRequest request) {

		request.getSession().setAttribute("token", Token.getToken().makeToken());

		return "reg";
	}

	/**
	 * @Desc 登入 Page
	 */
	@RequestMapping("/toLogin")
	public String toLoginPage() {
		return "login";
	}

	/**
	 * @Desc 个人主页
	 */
	@RequestMapping("/homePage")
	public String toOneIndex(HttpServletRequest request, Map<String, Object> map) {

		map.put("userCus", us.findUser(((User) request.getSession().getAttribute("user")).getId()));

		return "myHome";
	}

	/**
	 * @Desc 博客管理
	 */
	@RequestMapping("/toBlog")
	public String toBlog(Map<String, Object> map, HttpServletRequest request) {
		return "blogManage";
	}

	/**
	 * @Desc 上传头像 Page
	 */
	@RequestMapping("/toUploadImg")
	public String toUploadImgPage() {
		return "HeadImg";
	}

	/**
	 * @Desc 修改资料 Page
	 */
	@RequestMapping("/toUpMsg")
	public ModelAndView toUpMsgPage(Map<String, Object> map, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("upMsg");
		mav.addObject("ips", GetIps.getIps());
		mav.addObject("msgs", ms.getUsermsg(((User) request.getSession().getAttribute("user")).getId()));

		return mav;
	}

	/**
	 * @Desc 修改密码 Page
	 */
	@RequestMapping("/toUpPwd")
	public String toUpPwdPage() {
		return "upPwd";
	}

	/**
	 * @Desc 设置Git分享 Page
	 */
	@RequestMapping("/toSetGit")
	public String toSetGitPage(Map<String, Object> map, HttpServletRequest request) {

		map.put("pros", JSONObject.toJSONString(ms.getPro(((User) request.getSession().getAttribute("user")).getId())));
		map.put("gitHome", ms.getGit(((User) request.getSession().getAttribute("user")).getId()));

		return "shareGit";
	}

	/**
	 * @Desc 管理我的原创 Page
	 */
	@RequestMapping("/toSetOriginal")
	public String toSetOriginal(Map<String, Object> map, HttpServletRequest request) {

		map.put("genre", 0);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("pages", PageUtil.getPage(1, ts.findTopByGenre(map), 7));

		return "setTopic";
	}

	/**
	 * @Desc 管理我的转发 Page
	 */
	@RequestMapping("/toSetTransmit")
	public String toSetTransmit(Map<String, Object> map, HttpServletRequest request) {

		map.put("genre", 1);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("pages", PageUtil.getPage(1, ts.findTopByGenre(map), 7));

		return "setTopic";
	}

	/**
	 * @Desc 管理我的译文 Page
	 */
	@RequestMapping("/toSetTranslate")
	public String toSetTranslate(Map<String, Object> map, HttpServletRequest request) {

		map.put("genre", 2);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("pages", PageUtil.getPage(1, ts.findTopByGenre(map), 7));

		return "setTopic";
	}

	/**
	 * @Desc 草稿箱 Page
	 */
	@RequestMapping("/toDraft")
	public String toDraft(Map<String, Object> map, HttpServletRequest request) {

		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("state", TopicStateEnum.DRAFT.getCode());
		map.put("pages", PageUtil.getPage(1, ts.getTopicByState(map), 7));

		return "handlerTopic";
	}

	/**
	 * @Desc 垃圾箱 Page
	 */
	@RequestMapping("/toDustbin")
	public String toDustbin(Map<String, Object> map, HttpServletRequest request) {

		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("state", TopicStateEnum.REMOVE.getCode());
		map.put("pages", PageUtil.getPage(1, ts.getTopicByState(map), 7));

		return "handlerTopic";
	}

	/**
	 * @Desc 写文章 Page
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request) {
		request.getSession().setAttribute("topicToken", Token.getToken().makeToken());
		return "addTopic";
	}

	/**
	 * @Desc 管理员入口
	 */
	@RequestMapping("/toAdmin")
	public String admin() {
		return "admin";
	}

	/**
	 * @Desc 申请管理员
	 */
	@RequestMapping("/toApplyAdmin")
	public String toApplyAdmin() {
		return "applyAdmin";
	}

	/**
	 * @Desc 审核文章
	 */
	@RequestMapping("/toAudit")
	public String toAudit(Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, as.getAuditAdmin(), 7));
		return "auditTop";
	}

	/**
	 * @Desc 管理文章
	 */
	@RequestMapping("/toTopicManage")
	public String toManage(Map<String, Object> map) {

		map.put("pages", PageUtil.getPage(1, as.getDeleteAdmin(), 7));
		map.put("state", TopicStateEnum.DELETE.getCode());

		return "auditTop";
	}

	/**
	 * @Desc 用户管理
	 */
	@RequestMapping("/toUserManage")
	public String getUserAdmin(Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, as.getUserByType(UserTypeEnum.COMMON.getCode()), 10));
		map.put("type", 0);
		return "userManage";
	}

	/**
	 * @Desc 举报处理
	 */
	@RequestMapping("/toReportManage")
	public String getReportAdmin() {
		return "getReportAdmin";
	}

	/**
	 * @Desc 管理员管理
	 */
	@RequestMapping("/toAdminManage")
	public String toAdminManage(Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, as.getUserByType(UserTypeEnum.ADMIN.getCode()), 10));
		map.put("type", 1);
		return "userManage";
	}

	/**
	 * @Desc 封号管理
	 */
	@RequestMapping("/toPunishManage")
	public String getPunish(Map<String, Object> map) {
		map.put("pages", PageUtil.getPage(1, as.getUserByType(UserTypeEnum.PUNISH.getCode()), 10));
		map.put("type", 3);
		return "userManage";
	}

	/**
	 * @Desc 根据id查找粉丝
	 */
	@RequestMapping("/getFans")
	public String getFans(Map<String, Object> map, HttpServletRequest request) {
		map.put("pages",
				PageUtil.getPage(1, ms.findFans(((User) request.getSession().getAttribute("user")).getId()), 10));
		map.put("jud", "fans");
		return "fansAndidol";
	}

	/**
	 * @Desc 根据id查找已关注
	 */
	@RequestMapping("/getAtt")
	public String getAtt(Map<String, Object> map, HttpServletRequest request) {
		map.put("pages",
				PageUtil.getPage(1, ms.findAtt(((User) request.getSession().getAttribute("user")).getId()), 10));
		map.put("jud", "atts");
		return "fansAndidol";
	}

}
