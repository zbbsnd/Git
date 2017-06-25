package com.itheima.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.itheima.mybatis.mapper.UserMapper;
import com.itheima.mybatis.pojo.QueryVo;
import com.itheima.mybatis.pojo.User;

public class querVoTest {
	@Test
	public void queryVo() throws Exception{		
		String resource = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		//创建Sql Session Factory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);//Shift + ALT + L 
		//Sql Session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo =new QueryVo();
		User user=new User();
		user.setUsername("操");
		queryVo.setUser(user);
		
		List<User> list = userMapper.queryVoByusername(queryVo);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}
}
