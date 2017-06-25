package com.itheima.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.itheima.mybatis.pojo.User;

public class FirstDemoTest {
	@Test
	public void before() throws Exception{		
		String resource = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		//创建Sql Session Factory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);//Shift + ALT + L 
		//Sql Session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", 25);		
		System.out.println(user);
	}
	@Test
	public void  first() throws Exception{
		String resource = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		//创建Sql Session Factory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);//Shift + ALT + L 
		//Sql Session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Object> list = sqlSession.selectList("test.findUserByUsername", "操");
		System.out.println(list);
	}
	@Test
	public void demo01() throws Exception{		
		String resource = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		//创建Sql Session Factory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);//Shift + ALT + L 
		//Sql Session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("findUserById", 25);		
		System.out.println(user);
	}
	@Test
	public void  demo02() throws Exception{
		String resource = "sqlMapConfig.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		//创建Sql Session Factory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);//Shift + ALT + L 
		//Sql Session
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> list = sqlSession.selectList("findUserByUsername", "操");
		for (User user : list) {
			System.out.println(user);
		}
	
	}
	
}
