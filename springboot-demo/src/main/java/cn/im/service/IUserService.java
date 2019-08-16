package cn.im.service;

import java.util.List;

import cn.im.domain.User;

public interface IUserService {

	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(String id);
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public List<User> getUserList();
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user);
		
	/**
	 * 更新用户信息
	 * @param id
	 * @param user
	 * @return
	 */
	public User updateUser(String id,User user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delUser(String id);
}
