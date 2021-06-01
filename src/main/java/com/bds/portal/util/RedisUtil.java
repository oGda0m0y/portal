package com.bds.portal.util;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	protected static Logger logger = Logger.getLogger(RedisUtil.class);

	// Redis服务器IP
	private static String IP = Const.REDIS_HOST;

	// Redis的端口号
	private static int PORT = Const.REDIS_PORT;

	private static Jedis jedis = null;

	// 访问密码
	private static String AUTH = Const.REDIS_PWD;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1000;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 50;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 300000;

	// 超时时间
	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(false);
			config.setTestOnReturn(false);
			if (jedis == null) {
				jedisPool = new JedisPool(config, IP, PORT, TIMEOUT, AUTH);
				logger.info("IP:"+IP+",PORT:"+PORT+",AUTH:"+AUTH);
				//Thread.sleep(6000);
				//jedis = jedisPool.getResource();
				//jedis.auth(AUTH);
			}
		} catch (Exception e) {
			logger.error("First create JedisPool error : " + e);

		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 *
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		try {
			if (jedisPool != null) {
				// if (jedis == null) {
				jedis = jedisPool.getResource();
				// }
			}
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
		} finally {
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 释放jedis资源
	 *
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 设置 String
	 *
	 * @param key
	 * @param value
	 */
	public static void setString(String key, String value, int index) {
		try {
			if (jedis == null || !jedis.isConnected()) {
				jedis = getJedis();
			}
			if (value != null && !value.equals("")) {
				if (index >= 0) {
					jedis.select(index);
				}
				// jedis.setex(key, 432000, value);
				jedis.set(key, value);

			} else {
				logger.error("Set key value is null ");
			}

		} catch (Exception e) {
			jedis = getJedis();
			logger.error("Set key error : " + e);
		}
	}

	public static void setexString(String key, String value, int second) {
		try {
			if (jedis == null || !jedis.isConnected()) {
				jedis = getJedis();
			}
			if (value != null && !value.equals("")) {

				jedis.setex(key, second, value);

			} else {
				logger.error("Set key value is null ");
			}

		} catch (Exception e) {
			jedis = getJedis();
			logger.error("Set key error : " + e);
		}
	}

	/**
	 * 获取String值
	 *
	 * @param key
	 * @return value
	 */
	public static String getString(String key, int index) {
		// jedis = getJedis();
		if (jedis == null || !jedis.isConnected()) {
			jedis = getJedis();
		}
		if (jedis == null) {
			return null;
		}
		if (index >= 0) {
			try {
				jedis.select(index);
			} catch (Exception e) {
				jedis = getJedis();
				jedis.select(index);
				logger.error(e);
			}
			if (!jedis.exists(key)) {
				return null;
			} else {
				return jedis.get(key);
			}
		} else {
			return null;
		}
	}

	public static String getString(String key) {
		// jedis = getJedis();
		if (jedis == null || !jedis.isConnected()) {
			jedis = getJedis();
		}
		if (jedis == null) {
			return null;
		}
		if (!jedis.exists(key)) {
			return null;
		} else {
			return jedis.get(key);
		}
	}

	/**
	 * 关闭连接
	 */
	public static void quitConnection() {
		if (jedis != null) {
			jedis.quit();
		}
	}

	/**
	 * 删除key *
	 */
	public synchronized static void deleteKey(String key) {
		try {
			if (jedis == null || !jedis.isConnected()) {
				jedis = getJedis();
			}
			jedis.del(key);
		} catch (Exception e) {
			logger.info("", e);
		}

	}
}
