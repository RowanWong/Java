package cn.im.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.mysql.jdbc.StringUtils;

import cn.im.domain.User;

/**
 * 用户缓存
 * @author Administrator
 *
 */
public class UserCache {
	
	private static final ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
	
	/**
	 * 根据id从缓存中查询用户对象
	 * @param id
	 * @return
	 */
	public static User getUserById(String id){
			return userMap.get(id);
	}
	
	/**
	 * 获取所有用户对象
	 * @return
	 */
	public static List<User> getUsers(){
		if(userMap.values().size() > 0){
			return new ArrayList<User>(userMap.values());
		}else{
			return null;
		}
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public static boolean addUser(User user){
		boolean flag = false;
		if(!StringUtils.isNullOrEmpty(user.getId()) && !userMap.containsKey(user.getId())){
			userMap.put(user.getId(), user);
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 根据用户id更新队列中用户信息
	 * @param id
	 * @param user
	 * @return 
	 */
	public static boolean updateUser(String id,User user){
		boolean flag = false;
		if(userMap.containsKey(id)){
			userMap.put(id, user);
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据id删除缓存中的用户对象
	 * @param id
	 * @return
	 */
	public static boolean delUserById(String id){
		User user = userMap.remove(id);
		return user==null? false:true;
	}
	
}
