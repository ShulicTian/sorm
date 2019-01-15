package shulictian.ssm.util;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ShulicTian
 * @Date 2018/3/11
 */
public class RedisUtils {

	/**
	 * @RedisDataType String 根据key获取value
	 * @param key
	 * @return
	 */
	public static String getRedisData(JedisPool jedisPool, String key) {
		Jedis jedis = jedisPool.getResource();
		String data = jedis.get(key);
		jedis.close();
		return data;
	}

	/**
	 * @RedisDataType String 缓存 key,value
	 * @return
	 */
	public static void setRedisData(JedisPool jedisPool, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		jedis.close();
	}

	/**
	 * @RedisDataType String 缓存 key,value 并设置失效时间
	 * @return
	 */
	public static void setexRedisData(JedisPool jedisPool, String key, int outTime, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.setex(key, outTime, value);
		jedis.close();
	}

	public static String hgetRedisData(JedisPool jedisPool, String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String data = jedis.hget(key, field);
		jedis.close();
		return data;
	}

	public static String hgetAllRedisData(JedisPool jedisPool, String key) {
		Jedis jedis = jedisPool.getResource();
		String data = JSONObject.toJSONString(jedis.hgetAll(key));
		jedis.close();
		return data;
	}

	public static void hsetRedisData(JedisPool jedisPool, String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.hset(key, field, value);
		jedis.close();
	}

	public static void hmsetRedisData(JedisPool jedisPool, String key, Map<String, String> map) {
		Jedis jedis = jedisPool.getResource();
		jedis.hmset(key, map);
		jedis.close();
	}

	public static void removeKeys(JedisPool jedisPool, String[] keys) {
		Jedis jedis = jedisPool.getResource();
		jedis.del(keys);
		jedis.close();
	}

	public static void removeField(JedisPool jedisPool, String key, String[] fields) {
		Jedis jedis = jedisPool.getResource();
		jedis.hdel(key, fields);
		jedis.close();
	}

	public static Set<String> findKeys(JedisPool jedisPool, String pattern) {
		Set<String> set = null;
		Jedis jedis = jedisPool.getResource();
		set = jedis.keys(pattern);
		jedis.close();
		return set;
	}

}
