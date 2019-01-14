package shulictian.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.UserMapper;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserCustom;
import shulictian.ssm.service.UserService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 * @Desc UserService
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um = null;

	@Autowired
	private JedisPool jedisPool = null;

	/**
	 * @Desc 账号注册
	 * @UpDate -2017/12/20
	 */
	@Override
	public void saveUser(User user) {
		user.setRegTime(new Date());
		um.saveUser(user);
		um.createState(user.getId());
		um.createAdd(user.getId());
		um.createUsermsg(user.getId());
	}

	/**
	 * @Desc 账号登入
	 * @UpDate 加入redis缓存-2018/03/11
	 */
	@Override
	public String getUser(User user) {

		String data = RedisUtils.hgetAllRedisData(jedisPool, "user" + user.getUserName() + user.getPassWord());
		if (data != null && !data.equals("{}")) {
			return data;
		}
		user = um.getUser(user);
		if (user != null) {
			Map<String, String> map = new HashMap<String, String>();

			map.put("id", user.getId().toString());
			map.put("userName", user.getUserName());
			map.put("passWord", user.getPassWord());
			map.put("nickName", user.getNickName());
			map.put("type", user.getType() + "");

			RedisUtils.hmsetRedisData(jedisPool, "user" + user.getUserName() + user.getPassWord(), map);
			return JSONObject.toJSONString(map);
		}
		return null;
	}

	/**
	 * @Desc 更新用户
	 * @UpDate 加入redis缓存-2018/03/11
	 */
	@Override
	public void upUser(Map<String, Object> map) {
		List<Integer> list = um.findTopicIds(Integer.parseInt(map.get("id").toString()));
		List<String> keys = new ArrayList<String>();
		um.upUser(map);
		String[] keyArr = {};
		for (int id : list) {
			keys.add("topic" + id);
		}
		keys.add("user" + map.get("id"));
		keys.add("user" + map.get("userName") + map.get("passWord"));
		keys.add("fans" + map.get("id"));
		keys.add("atts" + map.get("id"));
		keys.add("usertype" + 0);
		keys.add("usertype" + 1);
		keys.add("usertype" + 3);
		RedisUtils.removeKeys(jedisPool, keys.toArray(keyArr));
	}

	/**
	 * @Desc 更新用户State
	 * @UpDate -2018/01/05
	 */
	@Override
	public void updateState(Map<String, Object> map) {
		um.updateState(map);
	}

	/**
	 * @Desc 根据id查找UserCustom
	 * @UpDate 加入redis缓存-2018/01/5
	 */
	@Override
	public UserCustom findUser(int id) {

		return um.findUser(id);
	}

	/**
	 * 查找所有用户名
	 * 
	 * @Override public List<String> getAllUserName() {
	 * 
	 *           return um.getAllUserName(); }
	 */

}
