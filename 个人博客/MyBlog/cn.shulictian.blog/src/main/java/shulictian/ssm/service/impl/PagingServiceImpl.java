package shulictian.ssm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.TopicMapper;
import shulictian.ssm.service.PagingService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2018/03/02
 * @Desc PagingService
 */
@Service
public class PagingServiceImpl implements PagingService {

	@Autowired
	private JedisPool jedisPool = null;

	@Autowired
	private TopicMapper tm = null;
	/**
	 * @Desc 首页文章分页
	 * @UpDate -2018/03/13
	 */
	@Override
	public String firstPage(int nowPage, int pages) {
		Map<String, Object> map = new HashMap<String, Object>();
		String data = RedisUtils.getRedisData(jedisPool, "indexTopics");
		if (nowPage < 4) {
			map.put("start", 0);
			map.put("end", 30);
			if (data == null || data.equals("[]")) {
				data = JSONArray.toJSONString(tm.getTopicsByAudit(map),
						SerializerFeature.DisableCircularReferenceDetect);
				RedisUtils.setexRedisData(jedisPool, "indexTopics", 1800000, data);
			}
			return data;
		}
		map.put("start", nowPage * pages - pages);
		map.put("end", nowPage * pages);
		data = JSONArray.toJSONString(tm.getTopicsByAudit(map), SerializerFeature.DisableCircularReferenceDetect);
		return data;
	}

}
