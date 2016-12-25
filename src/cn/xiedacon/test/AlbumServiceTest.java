package cn.xiedacon.test;


import java.lang.reflect.Field;
import java.util.ArrayDeque;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AlbumServiceTest extends AbstractAnnotationConfigDispatcherServletInitializer{
	public static void main(String[] args) {
		int i=0;
		ArrayDeque deque = new ArrayDeque();
		try{
			for(;i<Integer.MAX_VALUE;i++){
				if(!deque.offer(i)){
					continue;
				}
			}
		}catch(Error e){
		}finally {
			System.out.println(i);
		}
		
		
		Class clazz = ArrayDeque.class;
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			Class<?> type = field.getType();
			field.setAccessible(true);
		}
			
		
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		System.out.println("createRootApplicationContext");
		return super.createRootApplicationContext();
	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		System.out.println("createServletApplicationContext");
		return super.createServletApplicationContext();
	}
	
	
}
