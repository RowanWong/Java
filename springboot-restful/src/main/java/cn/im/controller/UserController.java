package cn.im.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.im.domain.User;
import cn.im.service.IUserService;
import cn.im.tool.ErrCodeEnum;
import cn.im.tool.ResultHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 结合项目实际需要，类RESTful风格： 
 * 1.请求方法调整：只使用GET、POST
 * 2.返回报文调整：使用josn格式，{"errcode":40003,"errmsg":"xxx"}来代替状态码 
 * 3.在URI标识中，增加了动词
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	IUserService userService;

	@ApiOperation(value = "获取用户信息", notes = "根据id获取用户信息")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK) // 标识接口正常返回时的HTTP状态码
	public Object getUser(@PathVariable("id") String id) {
		return userService.getUserById(id);
	}

	@ApiOperation(value = "获取用户列表", notes = "获取所有用户列表")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUserList();
	}

	@ApiOperation(value = "添加用户", notes = "添加用户")
	@ApiImplicitParam(name="user" , value="用户实体user",dataType="User",required=true)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object addUser(@RequestBody User user) {
		return user != null ? userService.addUser(user) : null;
	}

	@ApiOperation(value = "更新用户", notes = "更新用户")
	@RequestMapping(value = "/update", method = RequestMethod.POST ,consumes="application/json")
	public Object updateUser(@RequestParam("id") String id, @RequestBody User user) {
		userService.updateUser(id, user);
		return ResultHelper.errCode(ErrCodeEnum.SUCCESS_200);

	}

	@ApiOperation(value = "删除用户", notes = "删除用户")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object delUser(@RequestParam(value = "id", required = true) String id,@RequestBody Map<String,String> map) {
		System.out.println(map.keySet().toString());
		return userService.delUser(id)?ResultHelper.errCode(ErrCodeEnum.SUCCESS_200):ResultHelper.errCode(ErrCodeEnum.NOT_FOUND_40001);

	}

}
