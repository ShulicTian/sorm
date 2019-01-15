package shulictian.ssm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.AdminMapper;
import shulictian.ssm.po.TopicStateEnum;
import shulictian.ssm.po.User;
import shulictian.ssm.service.AdminService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 * @Desc AdminService
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper am = null;

	@Autowired
	private JedisPool jedisPool = null;

	/**
	 * @Desc 升级为管理员
	 * @UpDate -2017/12/20
	 */
	@Override
	public String goGetAdmin(String code, User user) {
		String c = am.goGetAdmin(code);
		if (c != null && c != "") {
			am.updateCodeStatus(c);
			am.updateUserType(user.getId());
			user.setType(1);
			return "yes";
		}
		return "no";
	}

	/**
	 * @Desc 获取所有待审核的文章
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String getAuditAdmin() {
		String data = RedisUtils.getRedisData(jedisPool, "adminAudit");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(am.getAdTopByState(TopicStateEnum.AUTHSTR.getCode()),
				SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "adminAudit", 120000, data);

		return data;
	}

	/**
	 * @Desc 获取所有彻底删除的文章
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String getDeleteAdmin() {
		String data = RedisUtils.getRedisData(jedisPool, "adminManage");
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(am.getAdTopByState(TopicStateEnum.DELETE.getCode()),
				SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "adminManage", 120000, data);
		return data;
	}

	/**
	 * @Desc 更新文章的状态
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public void upTopStateByState(Map<String, Object> map) {
		am.upTopStateByState(map);
		String[] keys = { "user" + map.get("userId"), "topic" + map.get("topId"), "adminAudit", "adminManage",
				"findAllTopHot", "findAllTopNew", "indexTopics" };
		String[] fields = { "state0", "state1", "state2", "state3", "state4", "state5" };
		RedisUtils.removeKeys(jedisPool, keys);
		RedisUtils.removeField(jedisPool, "user" + map.get("userId"), fields);
	}

	/**
	 * @Desc 根据用户类型获取用户
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String getUserByType(int type) {
		String data = RedisUtils.getRedisData(jedisPool, "usertype" + type);
		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(am.getUserByType(type), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "usertype" + type, 120000, data);
		return data;
	}

	/**
	 * @Desc 更新用户类型
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public void upUserTypeById(Map<String, Object> map) {
		am.upUserTypeById(map);
		String[] keys = { "usertype0", "usertype1", "usertype3" };
		RedisUtils.removeKeys(jedisPool, keys);
	}

}
