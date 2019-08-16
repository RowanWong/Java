package cn.im.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.im.domain.User;
import cn.im.repository.UserRepository;
import cn.im.util.Util;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository repo;
	
	public User getUserById(String id){
		return repo.getUserById(id);
	}

	
	@Override
	public List<User> getUserList() {
		return repo.getUserList();
	}

	@Override
	public User addUser(User user) {
		return repo.addUser(user);
	}

	@Override
	public User updateUser(String id, User user) {
		User currInstance = repo.getUserById(id);
		
		//支持部分更新  注意int类型的字段
		String[] nullPropertiesName = Util.getNullPropertyNames(user);
		BeanUtils.copyProperties(user, currInstance, nullPropertiesName);
		
		return repo.updateUser(id, currInstance);
	}

	@Override
	public boolean delUser(String id) {
		return repo.delUser(id);
	}
	
	
}
