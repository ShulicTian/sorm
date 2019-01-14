package shulictian.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserTypeEnum;
import shulictian.ssm.service.AdminService;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 */
@Controller
@RequestMapping(value = "/bigCow/admin")
public class AdminController extends BaseController {

	@Autowired
	private AdminService as = null;

	/**
	 * @Desc 验证邀请码
	 * 
	 * @param code
	 */
	@RequestMapping("/validateCode")
	public String validateCode(@RequestParam String code, HttpServletRequest request, Map<String, Object> map) {
		User user = (User) request.getSession().getAttribute("user");
		map.put("getStatus", as.goGetAdmin(code, user));
		return "applyAdmin";
	}

	List<Topic> tList = new ArrayList<>();

	/**
	 * @Desc 通过审核
	 * 
	 */
	@RequestMapping("/pass")
	public void pass(@RequestParam("topId") int topId, @RequestParam("userId") int userId, Map<String, Object> map) {
		System.out.println("TopId " + topId + " UserId " + userId);
		map.put("state", TopicStateEnum.PASS.getCode());
		map.put("topId", topId);
		map.put("userId", userId);
		as.upTopStateByState(map);
	}

	/**
	 * @Desc 不通过审核
	 * 
	 */
	@RequestMapping("/nopass")
	public void nopass(@RequestParam("topId") int topId, @RequestParam("userId") int userId, Map<String, Object> map) {
		map.put("state", TopicStateEnum.NOTPASS.getCode());
		map.put("topId", topId);
		map.put("userId", userId);
		as.upTopStateByState(map);
	}

	/**
	 * @Desc 恢复用户彻底删除的文章
	 * 
	 */
	@RequestMapping("/recoverAdmin")
	public String recoverAdmin(@RequestParam("topId") int topId, @RequestParam("userId") int userId,
			Map<String, Object> map) {
		map.put("state", TopicStateEnum.REMOVE.getCode());
		map.put("topId", topId);
		map.put("userId", userId);
		as.upTopStateByState(map);
		return "auditTop";
	}

	/**
	 * @Desc 管理员删除文章
	 * 
	 */
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam("topId") int topId, @RequestParam("userId") int userId,
			Map<String, Object> map) {
		map.put("state", TopicStateEnum.DELETE.getCode());
		map.put("topId", topId);
		map.put("userId", userId);
		as.upTopStateByState(map);
		return "auditTop";
	}

	/**
	 * @Desc 撤销管理员
	 * 
	 */
	@RequestMapping("/revocation")
	public String revocation(@RequestParam("id") int id, Map<String, Object> map) {
		map.put("type", UserTypeEnum.COMMON.getCode());
		map.put("id", id);
		as.upUserTypeById(map);
		return "userManage";
	}

	/**
	 * @Desc 封号
	 * 
	 */
	@RequestMapping("/punish")
	public String punish(@RequestParam("id") int id, Map<String, Object> map) {
		map.put("type", UserTypeEnum.PUNISH.getCode());
		map.put("id", id);
		as.upUserTypeById(map);
		return "userManage";
	}

	/**
	 * @Desc 解封
	 * 
	 */
	@RequestMapping("/unPunish")
	public String unPunish(@RequestParam("id") int id, Map<String, Object> map) {
		map.put("type", UserTypeEnum.COMMON.getCode());
		map.put("id", id);
		as.upUserTypeById(map);
		return "userManage";
	}

}
