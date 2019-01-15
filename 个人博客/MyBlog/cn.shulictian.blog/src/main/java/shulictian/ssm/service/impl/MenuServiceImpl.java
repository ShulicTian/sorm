package shulictian.ssm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import redis.clients.jedis.JedisPool;
import shulictian.ssm.mapper.UserMapper;
import shulictian.ssm.po.UserMessage;
import shulictian.ssm.po.UserProMsg;
import shulictian.ssm.service.MenuService;
import shulictian.ssm.util.RedisUtils;

/**
 * @author ShulicTian
 * @Date 2017/12/20
 * @Desc MenuService
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private UserMapper um = null;

	@Autowired
	private JedisPool jedisPool = null;

	/**
	 * @Desc 关注用户
	 * @UpDate -2017/12/20
	 */
	@Override
	public void attention(Map<String, Object> map) {
		map.put("createTime", new Date());
		um.attention(map);
	}

	/**
	 * @Desc 取消关注
	 * @UpDate -2017/12/20
	 */
	@Override
	public void cancelAtt(Map<String, Object> map) {
		um.cancelAtt(map);
	}

	/**
	 * @Desc 获取粉丝
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String findFans(int id) {

		String data = RedisUtils.getRedisData(jedisPool, "fans" + id);

		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(um.findFans(id), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "fans" + id, 120000, data);
		return data;
	}

	/**
	 * @Desc 获取用户已关注
	 * @UpDate 加入redis缓存-2018/3/10
	 */
	@Override
	public String findAtt(int id) {
		String data = RedisUtils.getRedisData(jedisPool, "atts" + id);

		if (data != null && !data.equals("[]")) {
			return data;
		}
		data = JSONArray.toJSONString(um.findAtt(id), SerializerFeature.DisableCircularReferenceDetect);
		RedisUtils.setexRedisData(jedisPool, "atts" + id, 120000, data);
		return data;
	}

	/**
	 * @Desc 更新用户资料
	 * @UpDate -2018/01/13
	 */
	@Override
	public void upMsg(UserMessage userMsg) {

		um.updateUsermsg(userMsg);
	}

	/**
	 * @Desc 获取用户资料
	 * @UpDate -2018/01/13
	 */
	@Override
	public UserMessage getUsermsg(int id) {

		return um.findUsermsg(id);
	}

	/**
	 * @Desc 获取用户的Git分享项目信息
	 * @UpDate -2018/01/13
	 */
	@Override
	public List<UserProMsg> getPro(int id) {

		return um.findPro(id);
	}

	/**
	 * @Desc 添加用户的Git分享项目信息
	 * @UpDate -2018/01/13
	 */
	@Override
	public void addPro(UserProMsg pm) {
		um.addPro(pm);
	}

	/**
	 * @Desc 更新用户的Git分享项目信息
	 * @UpDate -2018/01/13
	 */
	@Override
	public void upPro(UserProMsg pm) {
		um.upPro(pm);
	}

	/**
	 * @Desc 获取用户的Git主页地址
	 * @UpDate -2018/01/13
	 */
	@Override
	public String getGit(int id) {
		return um.findGit(id);
	}

	/**
	 * @Desc 更新用户的Git主页地址
	 * @UpDate -2018/01/13
	 */
	@Override
	public void upGit(Map<String, Object> map) {
		um.upGit(map);
	}

	/**
	 * @Desc 获取用户的个性签名
	 * @UpDate -2018/01/13
	 */
	@Override
	public String getSdf(int userId) {

		return um.getSdf(userId);
	}

	/**
	 * @Desc 修改用户的个性签名
	 * @UpDate -2018/01/13
	 */
	@Override
	public void upSdf(Map<String, Object> map) {

		um.upSdf(map);
	}

	/**
	 * @Desc 判断用户是否已关注
	 * @UpDate -2018/01/13
	 */
	@Override
	public String judAtt(Map<String, Object> map) {
		if (um.judAtt(map) > 0) {
			return "yes";
		}
		return "no";
	}

}
