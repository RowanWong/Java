package cn.im.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis配置类	
 * @author Administrator
 *
 */
//@Configuration
public class RedisConfig {
	
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(){
		return null;
		
	}
}
