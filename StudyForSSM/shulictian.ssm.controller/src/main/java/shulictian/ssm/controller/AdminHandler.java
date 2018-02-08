package shulictian.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.User;
import shulictian.ssm.service.AdminService;
import shulictian.ssm.util.PageUtil;

@Controller
public class AdminHandler {
	
	@Resource
	AdminService as=null;

	/**
	 * 管理员入口
	 * @param session
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	/**
	 * 申请管理员
	 */
	@RequestMapping("/toApply")
	public String toApply() {
		return "applyAdmin";
	}
	
	/**
	 * 验证邀请码
	 * @param code
	 */
	@RequestMapping("/goGetAdmin")
	public String goGetAdmin(@RequestParam String code,HttpServletRequest request,Map<String,Object> map) {
		User user=(User) request.getSession().getAttribute("user");
		map.put("getStatus", as.goGetAdmin(code, user));
		return "applyAdmin";
	}
	
	
	List<Topic> tList = new ArrayList<>();
	/**
	 * 审核帖子
	 */
	@RequestMapping("/toAudit")
	public String getAuditAdmin(Map<String,Object> map) {
		tList=null;
		tList=as.getAuditAdmin();
		map.put("pages", PageUtil.getAdminPage(1,tList));
		return "auditTop";
	}
	@ResponseBody
	@RequestMapping("/auditPage")
	public String auditPage(@RequestParam Integer nowPage) {
		return PageUtil.getAdminPage(nowPage, tList).getPageTops();
	}
/*	@RequestMapping("/pageAudit")
	public String getAuditAdminPage(@RequestParam int nowPage,@RequestParam int types,Map<String,Object> map) {
		if(types==0) {
			map.put("pages", PageUtil.getAdminPage(nowPage,as.getAuditAdmin()));
		}else if(types==1) {
			map.put("pages", PageUtil.getAdminPage(nowPage,as.getDeleteAdmin()));
		}
		return "auditTop";
	}*/
	
	@RequestMapping("/pass")
	public void pass(@RequestParam int id,Map<String,Object> map) {
		map.put("topId", id);
		as.pass(map);
	}
	
	@RequestMapping("/nopass")
	public void nopass(@RequestParam int id,Map<String,Object> map) {
		map.put("topId", id);
		as.noPass(map);
	}
	
	/**
	 * 管理帖子
	 */
	@RequestMapping("/toManageTop")
	public String toManageTop(Map<String,Object> map) {
		tList=null;
		tList=as.getDeleteAdmin();
		map.put("pages", PageUtil.getAdminPage(1,tList));
		map.put("state", 4);
		return "auditTop";
	}
	@ResponseBody
	@RequestMapping("/managePage")
	public String managePage(@RequestParam Integer nowPage) {
		return PageUtil.getAdminPage(nowPage, tList).getPageTops();
	}
	
	@RequestMapping("/recoverAdmin")
	public String recoverAdmin(@RequestParam int id,Map<String,Object> map) {
		map.put("topId", id);
		as.recoverAdmin(map);
		return "auditTop";
	}
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam int id,Map<String,Object> map) {
		map.put("topId", id);
		as.deleteAdmin(map);
		return "auditTop";
	}
	
	/**
	 * 用户管理
	 */
	@RequestMapping("/getUserAdmin")
	public String getUserAdmin(Map<String,Object> map) {
		map.put("pages", PageUtil.getUserPage(1,as.getUserAdmin()));
		return "userManage";
	}
	@ResponseBody
	@RequestMapping("/pageUsers")
	public String pageUser(@RequestParam int nowPage,Map<String,Object> map) {
		return PageUtil.getUserPage(nowPage,as.getUserAdmin()).getPageTops();
	}
	
	/**
	 * 举报处理
	 */
	@RequestMapping("/getReportAdmin")
	public String getReportAdmin() {
		return "getReportAdmin";
	}
	
	/**
	 * 管理员管理
	 */
	@RequestMapping("/goSuperAdmin")
	public String goSuperAdmin(Map<String,Object> map) {
		map.put("pages", PageUtil.getUserPage(1,as.goSuperAdmin()));
		return "userManage";
	}
	@RequestMapping("/revocation")
	public String revocation(@RequestParam int id,Map<String,Object> map) {
		as.revocation(id);
		return "userManage";
	}
	
	/**
	 * 封号处理
	 * @return
	 */
	@RequestMapping("/punish")
	public String punish(@RequestParam int id,Map<String,Object> map) {
		as.punish(id);
		return "userManage";
	}
	
	/**
	 * 封号管理
	 */
	@RequestMapping("/getPunish")
	public String getPunish(Map<String,Object> map) {
		map.put("pages", PageUtil.getUserPage(1,as.getPunish()));
		return "userManage";
	}
	@ResponseBody
	@RequestMapping("/pagePunish")
	public String pagePunish(@RequestParam int nowPage,Map<String,Object> map) {
		return PageUtil.getUserPage(nowPage,as.getPunish()).getPageTops();
	}
	
	/**
	 * 解封
	 * @return
	 */
	@RequestMapping("/unPunish")
	public String unPunish(@RequestParam int id,Map<String,Object> map) {
		as.unPunish(id);
		return "userManage";
	}
	
	/*@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}*/

}
