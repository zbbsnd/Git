package com.itheima.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.dao.UserDaoImpl;
import com.itheima.mybatis.pojo.User;

public class secondDemo {
	@Test
	public void test() throws Exception{
		String resource="sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		//都是在这里调用实体类的方法;
		UserDao userDao=new UserDaoImpl(sqlSessionFactory);
		User user = userDao.findUserById(25);
		System.out.println(user);
		
	}
	
	
	
}
