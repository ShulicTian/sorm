package shulictian.ssm.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.CommentsMapper;
import shulictian.ssm.service.CommentService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2018/02/02
 * @Desc CommentsService
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsMapper cm = null;

	@Autowired
	JedisPool jedisPool = null;

	/**
	 * @Desc 发送评论
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public void sendComment(Map<String, Object> map) {
		map.put("sendTime", new Date());
		cm.saveComment(map);
		String[] keys = { "comments" + map.get("topId") };
		RedisUtils.removeKeys(jedisPool, keys);
	}

	/**
	 * @Desc 删除评论
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public void remComment(Map<String, Object> map) {
		cm.delComment(map);
		String[] keys = { "comments" + map.get("id") };
		RedisUtils.removeKeys(jedisPool, keys);
	}

	/**
	 * @Desc 获取评论
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String getComment(int topId) {

		String data = RedisUtils.getRedisData(jedisPool, "comments" + topId);
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(cm.getComments(topId), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "comments" + topId, 600000, data);
		return data;
	}

}
