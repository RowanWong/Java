package cn.im.system;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.im.domain.User;

/**
 * 系统初始化加载
 * 实现方式：
 * 1.实现ApplicationRunner或CommandLineRunner接口  -- 用@component修饰
 * 2.Spring Bean初始化的InitializingBean,init-method和PostConstruct
 * @author Administrator
 *
 */
@Component
public class SystemInit implements ApplicationRunner,CommandLineRunner{


	@Override
	public void run(String... args) throws Exception {
		System.out.println("init by CommandLineRunner===");
		//user init
		UserCache.addUser(new User("001","kevin","123",17));
		UserCache.addUser(new User("002","Robin","456",21));
		UserCache.addUser(new User("003","David","789",43));
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("init by ApplicationRunner===");
		
	}

}
