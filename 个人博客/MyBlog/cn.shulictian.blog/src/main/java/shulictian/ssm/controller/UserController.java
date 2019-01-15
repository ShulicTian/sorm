package shulictian.ssm.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import shulictian.ssm.po.User;
import shulictian.ssm.service.UserService;
import shulictian.ssm.util.ValidUserUtils;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 */
@Controller
@RequestMapping(value = "/bigCow/user")
public class UserController extends BaseController {

	@Autowired
	private UserService us = null;

	/**
	 * @Desc 进行注册入库
	 */
	@RequestMapping("/reg")
	public String register(User user, HttpServletRequest request) {

		String result = ValidUserUtils.validToken(request);
		request.getSession().removeAttribute("token");
		if (result.equals("regsuccess")) {
			us.saveUser(user);
		}
		return result;
	}

	/**
	 * @Desc 进行登入
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {
		String data = us.getUser(user);
		if (data != null) {
			request.getSession().setAttribute("user", JSONObject.parseObject(us.getUser(user), User.class));
		} else {

			return "redirect:index.jsp?msg='no'";
		}

		return "redirect:/bigCow/page/";
	}

	/**
	 * 对注册的数据格式进行验证
	 * 
	 * @param bindingResult
	 *            用来获取数据格式的错误信息
	 * 
	 * 			@RequestMapping("/getUserNames") public void getUserNames(@Valid
	 *            User user, BindingResult bindingResult, HttpServletResponse
	 *            response) { try {
	 *            response.setContentType("text/html;charset=utf-8");
	 *            response.getWriter().print(ValidUserUtils.validForm(user,
	 *            bindingResult, us.getAllUserName())); } catch (IOException e) {
	 *            e.printStackTrace(); } }
	 */

	/**
	 * @Desc 注销用户
	 * @param session
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/bigCow/page/";
	}

	/**
	 * @Desc 更新State
	 */
	@RequestMapping("/upUserState")
	public void upUserState(Map<String, Object> map) {
		us.updateState(map);
	}

	/**
	 * @Desc 根据id查找UserCustom
	 */
	@RequestMapping("/getUserCus/{id}")
	public String getUserCusById(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("userCus", us.findUser(id));
		return "homePage";
	}

	/**
	 * @Desc 更新昵称
	 */
	@RequestMapping("/upNickName")
	public String upUser(@RequestParam("nickName") String nickName, HttpServletRequest request,
			Map<String, Object> map) {
		User user = ((User) request.getSession().getAttribute("user"));
		map.put("nickName", nickName);
		map.put("id", user.getId());
		map.put("userName", user.getUserName());
		map.put("passWord", user.getPassWord());
		us.upUser(map);
		if (nickName != null || nickName != "") {
			user.setNickName(nickName);
		}
		request.getSession().setAttribute("user", user);
		return "upMsg";
	}

	/**
	 * @Desc 更改密码
	 */
	@RequestMapping("/upPwd")
	public void upPwd(@RequestParam String oldPwd, @RequestParam String newPwd, HttpServletRequest request,
			Map<String, Object> map) {
		User user = ((User) request.getSession().getAttribute("user"));
		map.put("newPwd", newPwd);
		map.put("id", user.getId());
		map.put("userName", user.getUserName());
		map.put("passWord", user.getPassWord());
		if (user.getPassWord().equals(oldPwd)) {
			us.upUser(map);
		}
		request.getSession().setAttribute("user", user);
	}

}
