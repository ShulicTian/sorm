package shulictian.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.TopicMapper;
import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.service.TopicService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 * @Desc TopicService
 *
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicMapper tm = null;

	@Autowired
	private JedisPool jedisPool = null;

	/**
	 * @Desc 添加文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public int saveTopic(Topic topic) {
		topic.setSendTime(new Date());
		topic.setState(TopicStateEnum.AUTHSTR.getCode());
		tm.saveTopic(topic);
		tm.saveState(topic.getId());
		String[] keys = { "type" + topic.getType() };
		String[] fields = { "genre" + topic.getGenre() };
		RedisUtils.removeKeys(jedisPool, keys);
		RedisUtils.removeField(jedisPool, "user" + topic.getId(), fields);
		return topic.getId();
	}

	/**
	 * @Desc 添加文章到草稿箱
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public void saveDraft(Topic topic) {
		tm.saveTopic(topic);
		tm.saveState(topic.getId());
		String[] fields = { "state" + topic.getState() };
		RedisUtils.removeField(jedisPool, "user" + topic.getUser().getId(), fields);
	}

	/**
	 * @Desc 根据Id获取文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public String getTopic(int id) {
		String data = RedisUtils.hgetAllRedisData(jedisPool, "topic" + id);
		if (data != null && !data.equals("{}")) {
			return data;
		}
		TopicCustom tc = tm.getUserTopicById(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", tc.getId().toString());
		map.put("title", tc.getTitle().toString());
		map.put("text", tc.getText());
		map.put("sendTime", tc.getSendTime().getTime() + "");
		map.put("readNum", tc.getReadNum().toString());
		map.put("type", tc.getType() + "");
		map.put("genre", tc.getGenre() + "");
		map.put("userCus", JSONObject.toJSONString(tc.getUserCus()));
		RedisUtils.hmsetRedisData(jedisPool, "topic" + id, map);
		return JSONObject.toJSONString(tc);
	}

	/**
	 * @Desc 根据文章状态获取文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public String getTopicByState(Map<String, Object> map) {
		String data = RedisUtils.hgetRedisData(jedisPool, "user" + map.get("userId"), "state" + map.get("state"));
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.getTopByState(map), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.hsetRedisData(jedisPool, "user" + map.get("userId"), "state" + map.get("state"), data);
		return data;
	}

	/**
	 * @Desc 获取所有文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public String getTops() {
		String data = RedisUtils.getRedisData(jedisPool, "indexTopics");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("end", 30);
		data = JSONArray.toJSONString(tm.getTopicsByAudit(map), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "indexTopics", 1800000, data);
		return data;
	}

	/*
	 * @Override public int getAllCount() { int count = tm.getAllCount(); if (count
	 * > 100) { return 100; } return count; }
	 */

	/**
	 * @Desc 根据state更新文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public void upTopStateByState(Map<String, Object> map) {
		tm.upTopStateByState(map);
		String[] keys = { "user" + map.get("userId"), "topic" + map.get("topId"), "adminAudit", "adminManage",
				"findAllTopHot", "findAllTopNew", "indexTopics" };
		String[] fields = { "state0", "state1", "state2", "state3", "state4", "state5" };
		RedisUtils.removeKeys(jedisPool, keys);
		RedisUtils.removeField(jedisPool, "user" + map.get("userId"), fields);
	}

	/**
	 * @Desc 搜索文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public String soTop(String sele) {
		String data = RedisUtils.getRedisData(jedisPool, sele);
		sele = "%" + sele + "%";
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.soTop(sele), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, sele, 600000, data);
		return data;
	}

	/**
	 * @Desc 根据文章类型获取文章
	 * @UpDate 加入redis缓存-2018/03/10
	 */
	@Override
	public String getType(int type) {
		String data = RedisUtils.getRedisData(jedisPool, "type" + type);
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.getType(type), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "type" + type, 600000, data);
		return data;
	}

	/**
	 * @Desc 获取用户最新发布的文章
	 * @UpDate -2018/01/05
	 */
	@Override
	public List<Topic> getUserTopNew(int id) {
		return tm.findNewTop(id);
	}

	/**
	 * @Desc 获取用户最热门的文章
	 * @UpDate -2018/01/05
	 */
	@Override
	public List<Topic> getUserTopHot(int id) {
		return tm.findHotTop(id);
	}

	/**
	 * @Desc 获取用户当前类型的文章
	 * @UpDate -2018/01/05
	 */
	@Override
	public List<Topic> getTypeNew(int type) {
		return tm.findNewTypeTop(type);
	}

	/**
	 * @Desc 获取用户文章类型相关的文章
	 * @UpDate -2018/01/05
	 */
	@Override
	public List<Topic> getRelatedTop(int type) {
		return tm.findRelatedTop(type);
	}

	/**
	 * @Desc 获取所有文章中最热门的文章
	 * @UpDate 加入redis缓存-2018/01/05
	 */
	@Override
	public String getAllTopHot() {
		String data = RedisUtils.getRedisData(jedisPool, "findAllTopHot");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.findAllTopHot(), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "findAllTopHot", 1800000, data);
		return data;
	}

	/**
	 * @Desc 获取所有文章中最新发布的文章
	 * @UpDate 加入redis缓存-2018/01/05
	 */
	@Override
	public String findAllTopNew() {
		String data = RedisUtils.getRedisData(jedisPool, "findAllTopNew");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.findAllTopNew(), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "findAllTopNew", 600000, data);
		return data;
	}

	/**
	 * @Desc 根据genre查询用户的帖子
	 * @UpDate 加入redis缓存-2018/01/05
	 */
	@Override
	public String findTopByGenre(Map<String, Object> map) {
		String data = RedisUtils.hgetRedisData(jedisPool, "user" + map.get("userId"), "genre" + map.get("genre"));
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.findTopByGenre(map), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.hsetRedisData(jedisPool, "user" + map.get("userId"), "genre" + map.get("genre"), data);
		return data;
	}

	/**
	 * @Desc 个人主页搜索文章
	 * @UpDate 加入redis缓存-2018/01/05
	 */
	@Override
	public String homeFind(Map<String, Object> map) {
		String data = RedisUtils.getRedisData(jedisPool, map.get("ctx").toString());
		map.put("ctx", "%" + map.get("ctx") + "%");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(tm.homeFind(map), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, map.get("ctx").toString(), 600000, data);
		return data;
	}

}
