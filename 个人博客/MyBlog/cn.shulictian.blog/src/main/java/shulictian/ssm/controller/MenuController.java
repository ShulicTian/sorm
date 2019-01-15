package shulictian.ssm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import shulictian.ssm.po.User;
import shulictian.ssm.po.UserMessage;
import shulictian.ssm.po.UserProMsg;
import shulictian.ssm.service.MenuService;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.util.GetIps;
import shulictian.ssm.util.ImgUtil;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 *
 */
@Controller
@RequestMapping(value = "/bigCow/other")
public class MenuController extends BaseController {

	@Resource
	private TopicService ts = null;
	@Resource
	private MenuService ms = null;

	/**
	 * @Desc 判断关注
	 */
	@ResponseBody
	@RequestMapping("judAtt")
	public String judAtt(@RequestParam("userId") int userId, Map<String, Object> map, HttpServletRequest request) {

		map.put("userId", userId);
		map.put("fansId", ((User) request.getSession().getAttribute("user")).getId());

		return ms.judAtt(map);
	}

	/**
	 * @Desc 关注
	 * 
	 * @return
	 */
	@RequestMapping("/attention")
	public void attention(@RequestParam("userId") int userId, Map<String, Object> map, HttpServletRequest request) {
		map.put("userId", userId);
		map.put("fansId", ((User) request.getSession().getAttribute("user")).getId());
		ms.attention(map);
	}

	/**
	 * @Desc 取消关注
	 * 
	 * @return
	 */
	@RequestMapping("/cancelAtt")
	public void cancelAttention(@RequestParam("userId") int userId, Map<String, Object> map,
			HttpServletRequest request) {
		map.put("userId", userId);
		map.put("fansId", ((User) request.getSession().getAttribute("user")).getId());
		ms.cancelAtt(map);
	}

	/**
	 * @Desc 更新用户项目信息
	 */
	@RequestMapping("/upPro")
	public String upPro(UserProMsg pm, Map<String, Object> map, HttpServletRequest request) {
		pm.setUserId(((User) request.getSession().getAttribute("user")).getId());
		ms.upPro(pm);
		map.put("pros", JSONObject.toJSONString(ms.getPro(((User) request.getSession().getAttribute("user")).getId())));
		map.put("gitHome", ms.getGit(((User) request.getSession().getAttribute("user")).getId()));
		return "shareGit";
	}

	/**
	 * @Desc 添加用户项目信息
	 */
	@RequestMapping("/addPro")
	public String addPro(UserProMsg pm, Map<String, Object> map, HttpServletRequest request) {
		pm.setUserId(((User) request.getSession().getAttribute("user")).getId());
		ms.addPro(pm);
		map.put("pros", JSONObject.toJSONString(ms.getPro(((User) request.getSession().getAttribute("user")).getId())));
		map.put("gitHome", ms.getGit(((User) request.getSession().getAttribute("user")).getId()));
		return "shareGit";
	}

	/**
	 * @Desc 更新用户Git主页
	 */
	@RequestMapping("/upGit")
	public String upGit(Map<String, Object> map, @RequestParam String gitHome, HttpServletRequest request) {
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		map.put("gitHome", gitHome);
		ms.upGit(map);
		map.put("pros", JSONObject.toJSONString(ms.getPro(((User) request.getSession().getAttribute("user")).getId())));
		return "shareGit";
	}

	/**
	 * @Desc 获取用户项目信息
	 */
	@ResponseBody
	@RequestMapping("/getPro")
	public String getPro(@RequestParam("userId") Integer userId) {
		return JSONObject.toJSONString(ms.getPro(userId));
	}

	/**
	 * @Desc 获取Git主页
	 */
	@ResponseBody
	@RequestMapping("/getGit")
	public String getGit(@RequestParam("userId") Integer userId) {
		return ms.getGit(userId);
	}

	/**
	 * @Desc 头像上传
	 */
	@RequestMapping("/upHeadImg")
	public String upImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		ImgUtil.saveHeadImg(file, request, ((User) request.getSession().getAttribute("user")).getId());
		return "HeadImg";
	}

	/**
	 * @Desc 获取个性签名
	 */
	@ResponseBody
	@RequestMapping("/getSdf")
	public String getSdf(@RequestParam("userId") Integer userId) {

		return ms.getSdf(userId);
	}

	/**
	 * @Desc 修改个性签名
	 */
	@RequestMapping("/upSdf")
	public void upSdf(@RequestParam("userId") Integer userId, @RequestParam("descs") String descs,
			Map<String, Object> map) {
		map.put("descs", descs);
		map.put("userId", userId);
		ms.upSdf(map);
	}

	/**
	 * @Desc 修改资料
	 */
	@RequestMapping("/upMsg")
	public ModelAndView upMsg(UserMessage usermsg, HttpServletRequest request) {

		usermsg.setUserId(((User) request.getSession().getAttribute("user")).getId());
		ms.upMsg(usermsg);
		ModelAndView mav = new ModelAndView("upMsg");
		mav.addObject("msgs", usermsg);
		mav.addObject("ips", GetIps.getIps());

		return mav;
	}

}
