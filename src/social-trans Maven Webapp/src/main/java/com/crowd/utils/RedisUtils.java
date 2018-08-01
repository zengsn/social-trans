package com.crowd.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接池
 * @author ZhengWeizhi
 * @time  2018 下午8:32:36  
 * typeName RedisUtils
 */
public final class RedisUtils {
	//redis服务器ip
	private static String ADDR="localhost";
	//redis端口号
	private static int PORT=6379;
	//redis访问密码
	private static String AUTH=null;
	//可用连接实例的最大数目，默认为8
	private static int MAX_ACTIVE=1024;
	//控制一个pool最多有多少个状态为idle（空闲的）的实例，默认为8
	private static int MAX_IDLE=50;
	//等待可用连接的最大时间，单位毫秒，默认为-1，表示永不超时，如果等待时间超时，则抛出JedisConnectException
	private static int WAIT=10000;
	private static int TIMEOUT=10000;
	//在borrow一个Jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW=true;
	private static JedisPool jedisPool=null;
	
	
	/**
	 * 初始化Redis连接池
	 */
	static{
		try{
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool=new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取jedis实例
	 */
	
	public synchronized static Jedis getJedis(){
		try{
			if(jedisPool != null){
				Jedis resource=jedisPool.getResource();
				return resource;
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 释放jedi是资源
	 */
	@SuppressWarnings("deprecation")
	public synchronized static void returnResource(final Jedis jedis){
		if (jedis != null){
			jedisPool.returnResource(jedis);
		}
	}
}
