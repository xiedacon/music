package cn.xiedacon.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xiedacon.factory.Factory;

public class BaseTest {

	private ApplicationContext applicationContext;
	private Factory factory;
	@Before
	public void before(){
		applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		factory = new Factory();
	}
	
	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public Factory getFactory(){
		return factory;
	}
}
