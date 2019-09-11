package cn.im.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.im.domain.User;

@Mapper
public interface UserMapper {
	
	List<User> getUser();
}
