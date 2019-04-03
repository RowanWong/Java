package cn.im.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.im.model.User;

@RestController
public class HelloController {

	Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("/")
	public String index(){
		return "hello multi spring boot. ";
	}
	
	@RequestMapping(value="/user/get/{id}",method=RequestMethod.GET)
	public String getUserInfo(@PathVariable String id){
		logger.info("id:{}",id);
		User user = new User();
		user.setName("David.");
		user.setId("0001");
		return user.toString();
	}
}
