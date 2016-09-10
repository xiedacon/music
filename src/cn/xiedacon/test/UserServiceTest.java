package cn.xiedacon.test;

import java.util.Date;

import org.junit.Test;

import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.test.base.BaseTest;
import cn.xiedacon.test.base.Priority;
import cn.xiedacon.util.UUIDUtils;

public class UserServiceTest extends BaseTest {

	private User user;

	@Test
	@Priority(Priority.Level.L1)
	public void testInsertUser() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		user = this.getFactory().get(User.class);
		user.setId(UUIDUtils.uuid("test"));
		user.setPhone("1111" + new Date().getTime());
		user.setGithubId("test" + new Date().getTime());
		userService.insertUser(user);
	}

	@Test
	public void testSelectById() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);

		User bean = userService.selectById(user.getId());

		System.out.println(bean);
	}

	@Test
	public void testSelectByPhone() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);

		User bean = userService.selectByPhone(user.getPhone());

		System.out.println(bean);
	}

	@Test
	public void testUpdateUsername() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		user.setName("test");

		userService.updateUsername(user);
	}

	@Test
	public void testSelectByGitHubId() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);

		User bean = userService.selectByGitHubId(user.getGithubId());

		System.out.println(bean);
	}

	@Test
	public void testUpdatePassword() {
		UserService userService = this.getApplicationContext().getBean(UserService.class);
		user.setPassword("testtest");

		userService.updatePassword(user);
	}

}
