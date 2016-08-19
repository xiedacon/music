package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.vo.SimpleUserVo;

public class UserServiceTest extends BaseTest{

	@Test
	public void testSelectForIndex(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		
		List<SimpleUserVo> list = userService.selectForIndex();
		
		System.out.println(list);
	}
	
	@Test
	public void testInsertUser(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		User user = this.getFactory().get(User.class);
		user.setId(UUIDUtils.uuid("test"));
		
		userService.insertUser(user);
	}
	
	@Test
	public void testSelectByPhone(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		
		User bean = userService.selectByPhone("1111");
		
		System.out.println(bean);
	}
	
	@Test
	public void testSelectById(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		
		User bean = userService.selectById("test147072979234011df0190f47d143");
		
		System.out.println(bean);
	}
	
	@Test
	public void testUpdateUsername(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		User user = new User();
		user.setId("test14715941147101f342ffb30c3842");
		user.setName("test");
		
		userService.updateUsername(user);
	}
	
	@Test
	public void testSelectByGitHubId(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		
		String githubId = "test";
		
		User bean = userService.selectByGitHubId(githubId);
		
		System.out.println(bean);
	}
	
	@Test
	public void testUpdatePassword(){
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		
		User user = new User();
		user.setId("test147072979234011df0190f47d143");
		user.setPassword("testtest");
		
		userService.updatePassword(user);
	}
	
}
