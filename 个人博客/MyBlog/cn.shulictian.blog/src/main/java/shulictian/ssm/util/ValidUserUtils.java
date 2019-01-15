package shulictian.ssm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import shulictian.ssm.po.User;

public class ValidUserUtils {

	/**
	 * 防止重新提交表单的验证
	 */
	public static String validToken(HttpServletRequest request) {

		if (request.getParameter("token") == null) {
			System.out.println("request==null");
			return "redirect:/bigCow/page/toGlobalLogin";
		} else if (request.getSession().getAttribute("token") == null) {
			System.out.println("session==null");
			return "redirect:/bigCow/page/toGlobalLogin";
		} else if (!request.getParameter("token").equals(request.getSession().getAttribute("token"))) {
			System.out.println("request!=session");
			return "redirect:/bigCow/page/toGlobalLogin";
		}
		return "regsuccess";
	}

	/**
	 * 防止重新提交表单的验证
	 */
	public static String validTopicToken(HttpServletRequest request) {

		if (request.getParameter("topicToken") == null) {
			System.out.println("request==null");
			return "redirect:/bigCow/page/toAdd";
		} else if (request.getSession().getAttribute("topicToken") == null) {
			System.out.println("session==null");
			return "redirect:/bigCow/page/toAdd";
		} else if (!request.getParameter("topicToken").equals(request.getSession().getAttribute("topicToken"))) {
			System.out.println("request!=session");
			return "redirect:/bigCow/page/toAdd";
		}
		return "addsuccess";
	}

	/**
	 * 数据格式验证的方法
	 */
	public static String validForm(User user, BindingResult bindingResult, List<String> usernames) {
		List<String> message2 = new ArrayList<String>();
		String message = "";
		String msg = "<br><font color='red'>用户名和密码需要在6~16位之间，且只能由數字、字母、下划线组成</font>";

		if (!bindingResult.getAllErrors().isEmpty()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				String str = "<font color='red'>" + error.getDefaultMessage() + "</font>&nbsp";
				message2.add(str);
			}
		}

		boolean temp = false;
		if (user.getUserName() != null) {
			String name = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";
			Pattern valiname = Pattern.compile(name);
			temp = valiname.matcher(user.getUserName()).matches();
			if (!temp) {
				message2.add(msg);
			}
		}

		temp = false;
		if (user.getPassWord() != null) {
			String pass = "^[a-zA-Z][a-zA-Z0-9_]{5,15}$";
			Pattern valipass = Pattern.compile(pass);
			temp = valipass.matcher(user.getPassWord()).matches();
			if (!temp) {
				if (!message2.contains(msg)) {
					message2.add(msg);
				}
			}
		}

		if (usernames.contains(user.getUserName())) {
			message = "<font color='red'>用户名已存在！</font>";
		} else {
			if (!message2.isEmpty()) {
				for (String str : message2) {
					message += str;
				}
			}
		}
		return message;
	}

}
