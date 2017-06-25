package com.itheima.mybatis.mapper;

import java.util.List;

import com.itheima.mybatis.pojo.User;

public interface UserMapper {
	
	public User findUserById(Integer id);
	
	public List<User> findUserByUsername(String Username);
}
