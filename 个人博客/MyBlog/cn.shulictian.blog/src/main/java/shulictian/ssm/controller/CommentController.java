package shulictian.ssm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shulictian.ssm.po.User;
import shulictian.ssm.service.CommentService;
import shulictian.ssm.util.PageUtil;

/**
 * @author ShulicTian
 * @Date 2018/02/02
 *
 */
@Controller
@RequestMapping(value = "/bigCow/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService cs = null;

	/**
	 * @Desc 发送评论
	 */
	@RequestMapping("/sendComment")
	public String addCom(@RequestParam("topId") int topId, @RequestParam("content") String content,
			HttpServletRequest request, Map<String, Object> map) {
		map.put("content", content);
		map.put("topId", topId);
		map.put("userId", ((User) request.getSession().getAttribute("user")).getId());
		cs.sendComment(map);
		return "redirect:/bigCow/topic/get/" + topId;
	}

	/**
	 * @Desc 获取评论
	 */
	@ResponseBody
	@RequestMapping("/getComments")
	public void getCom(@RequestParam("topId") int topId) {
		printJSON("comments", PageUtil.getPage(1, cs.getComment(topId), 5));
	}

	/**
	 * @Desc 评论分页
	 */
	@ResponseBody
	@RequestMapping("/commentPage")
	public void commentPage(@RequestParam("nowPage") int nowPage, @RequestParam("topId") int topId) {
		printJSON("comments", PageUtil.getPage(nowPage, cs.getComment(topId), 5));
	}

	/**
	 * @Desc 删除评论
	 */
	@ResponseBody
	@RequestMapping("/delComments")
	public void delCom(@RequestParam("id") int id, Map<String, Object> map) {

		map.put("id", id);
		cs.remComment(map);

	}

}
