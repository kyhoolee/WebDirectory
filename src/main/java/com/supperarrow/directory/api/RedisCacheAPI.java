package com.supperarrow.directory.api;

import com.supperarrow.directory.util.RedisPool;

import redis.clients.jedis.Jedis;

public class RedisCacheAPI {
	
	public static final String video_source_cache = "vsc:hash";
	public static final String video_source_time = "vst:hash";
	
	public static final long expired = 1000 * 15;
	
	public static String getCache(String source) {
		String result = null;
		Jedis jedis = RedisPool.getJedis();
		
		result = jedis.hget(video_source_cache, source);
		
		jedis.close();
		
		return result;
	}
	
	
	public static void setCache(String source, String link) {
		Jedis jedis = RedisPool.getJedis();
		
		jedis.hset(video_source_cache, source, link);
		
		jedis.close();
	}
	
	public static void setTime(String source, long time) {
		Jedis jedis = RedisPool.getJedis();
		
		jedis.hset(video_source_time, source, String.valueOf(time));
		
		jedis.close();
	}
	
	
	public static long getTime(String source) {
		long time = 0;
		Jedis jedis = RedisPool.getJedis();
		
		String data = jedis.hget(video_source_time, source);
		if(data != null) {
			try {
				time = Long.parseLong(data);
			} catch (Exception e) {
				
			}
		}
		
		jedis.close();
		
		return time;
	}
	
	public static boolean checkTime(String source) {
		long time = getTime(source);
		if(time == 0) {
			return false;
		} else {
			long current = System.currentTimeMillis();
			if(Math.abs(current - time) < expired) {
				return false;
			}
		}
		
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

}
