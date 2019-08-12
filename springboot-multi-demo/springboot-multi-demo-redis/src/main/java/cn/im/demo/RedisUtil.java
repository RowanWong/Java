package cn.im.demo;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis工具类
 * @author Administrator
 *
 */
public class RedisUtil {
	
	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Test
	public void test(){
		System.out.println(redisTemplate.hasKey("mykey"));
	}

	/**
	 * 指定缓存失效时间
	 * @param key
	 * @param time
	 * @return
	 */
	public boolean expire(String key,long time){
		if(time > 0){
			return redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
		return false;
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key){
		return redisTemplate.hasKey(key);
	}
}
