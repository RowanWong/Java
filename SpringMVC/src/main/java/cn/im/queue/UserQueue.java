package cn.im.queue;

import java.util.ArrayList;

import cn.im.domain.User;

public class UserQueue {
	private static ArrayList<User> userQueue = new ArrayList<User>();
	
	
	public static synchronized void addUser(User user){
		userQueue.add(user);
	}
	
	public static synchronized User getUser(String userid){
		for (User user : userQueue) {
			if(userid.equals(user.getUserId())){
				return user;
			}
		}
		return new User();
	}
}
