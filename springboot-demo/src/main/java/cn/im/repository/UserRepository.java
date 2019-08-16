package cn.im.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;

import cn.im.domain.User;
import cn.im.system.UserCache;

@Repository
public class UserRepository {
	
	/**
	 * 根据id获取用户信息
	 * @param id 用户id
	 * @return 用户对象
	 */
	public User getUserById(String id){
		return UserCache.getUserById(id);
	}
	
	
		/**
		 * 获取用户列表
		 * @return
		 */
		public List<User> getUserList(){
			return UserCache.getUsers();
		};
		
		/**
		 * 添加用户
		 * @param user
		 * @return
		 */
		public User addUser(User user){
			//genereate user.id
			//user.setId(id);
			return UserCache.addUser(user)? user:null;
		};
			
		/**
		 * 更新用户信息
		 * @param id
		 * @param user
		 * @return
		 */
		public User updateUser(String id,User user){
			return UserCache.updateUser(id, user)? user:null;
		};
		
		/**
		 * 删除用户
		 * @param user
		 * @return
		 */
		public boolean delUser(String id){
			return UserCache.delUserById(id);
		};
}
