package cn.im.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.im.domain.User;
import cn.im.service.IUserService;
import io.swagger.annotations.ApiOperation;

/**
 * RESTful风格 API
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/rest/user")
public class RestfulController {
	
	/**
	 * RESTful风格 GET（SELECT）：从服务器取出资源（一项或多项）。 
	 * POST（CREATE）：在服务器新建一个资源。
	 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
	 * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
	 * DELETE（DELETE）：从服务器删除资源。
	 * 
	 */

	@Autowired
	IUserService userService;

	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@GetMapping(value = "/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@ApiOperation(value = "添加用户", notes = "添加用户")
	@PostMapping(value = "/users")
	@ResponseStatus(HttpStatus.CREATED)
	public Object addUser1(@RequestBody User user) {
		return userService.addUser(user);
	}

	@ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
	@GetMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Object getUser1(@PathVariable("id") String id){
		return userService.getUserById(id);
	}

	@ApiOperation(value = "删除用户", notes = "根据id删除用户")
	@DeleteMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") String id) {
		userService.delUser(id);
	}

	@ApiOperation(value = "更新用户", notes = "更新用户")
	@PatchMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User updateUser1(@PathVariable("id") String id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@RequestMapping("/test")
	public String index() {
		return "RESTful API test!";
	}
}
