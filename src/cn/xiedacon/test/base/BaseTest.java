package cn.xiedacon.test.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.test.AlbumServiceTest;
import cn.xiedacon.test.AlbumTagServiceTest;
import cn.xiedacon.test.CommentServiceTest;
import cn.xiedacon.test.SingerServiceTest;
import cn.xiedacon.test.SongListServiceTest;
import cn.xiedacon.test.SongMenuServiceTest;
import cn.xiedacon.test.SongMenuTagServiceTest;
import cn.xiedacon.test.SongServiceTest;
import cn.xiedacon.test.UserServiceTest;

public abstract class BaseTest {

	private ApplicationContext applicationContext;
	private Factory factory;

	@Before
	public void before() {
		applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		factory = applicationContext.getBean(Factory.class);
	}

	public void testAll() {
		this.before();
		Class<? extends BaseTest> c = this.getClass();
		Method[] existMethods = c.getDeclaredMethods();
		List<List<Method>> priorityMethodsList = new ArrayList<List<Method>>(4);

		for (int i = 0; i < existMethods.length; i++) {
			Method method = existMethods[i];
			method.setAccessible(true);
			Priority priority = method.getAnnotation(Priority.class);
			if (priority == null) {
				continue;
			}
			existMethods[i] = null;
			int level = priority.value().level;
			if (priorityMethodsList.get(level) == null) {
				List<Method> excuteMethodList = new ArrayList<>();
				excuteMethodList.set(0, method);
				priorityMethodsList.set(level, excuteMethodList);
			} else {
				List<Method> list = priorityMethodsList.get(level);
				list.set(list.size(), method);
			}
		}

		for (int i = 0; i < priorityMethodsList.size(); i++) {
			List<Method> priorityMethodList = priorityMethodsList.get(i);
			for (int j = 0; j < priorityMethodList.size(); j++) {
				Method method = priorityMethodList.get(j);
				invoke(c, method);
			}
		}

		for (int i = 0; i < existMethods.length; i++) {
			Method method = existMethods[i];
			if (method == null) {
				continue;
			}
			invoke(c, method);
		}
	}

	private void invoke(Class<? extends BaseTest> c, Method method) {
		try {
			method.invoke(this);
			System.out.println(c.getName() + "." + method.getName() + "方法执行成功");
			System.out.println();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// 以后写到日志文件
			System.out.println(c.getName() + "." + method.getName() + "方法抛出" + e.toString() + "异常");
			// e.printStackTrace();
			System.out.println();
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Factory getFactory() {
		return factory;
	}

	public static void main(String[] args) {
		new AlbumServiceTest().testAll();
		new AlbumTagServiceTest().testAll();
		new CommentServiceTest().testAll();
		new SingerServiceTest().testAll();
		new SongListServiceTest().testAll();
		new SongMenuServiceTest().testAll();
		new SongMenuTagServiceTest().testAll();
		new SongServiceTest().testAll();
		new UserServiceTest().testAll();
	}
}
