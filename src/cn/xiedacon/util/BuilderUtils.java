package cn.xiedacon.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class BuilderUtils {

	public static <T> T buildFrom(Class<T> c, Object obj) {
		T result = null;
		try {
			result = c.newInstance();
			way1(c, result, obj);
			//way2(result, obj);
		} catch (InstantiationException | IllegalAccessException  e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public static <T> List<T> buildListFrom(Class<T> c, Object obj) {
		List<T> result = null;
		try {
			result = new ArrayList<T>();
			// way1(c, result, obj);
			way2(result, obj);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	private static <T> void way1(Class<T> c, T result, Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields1 = c.getDeclaredFields();
		Field[] fields2 = obj.getClass().getDeclaredFields();

		for (int i = 0; i < fields1.length; i++) {
			Field field1 = fields1[i];
			for (int j = 0; j < fields2.length; j++) {
				Field field2 = fields2[j];
				if (field1.getName().equals(field2.getName())) {
					field1.setAccessible(true);
					field2.setAccessible(true);
					field1.set(result, field2.get(obj));
					break;
				}
			}
		}
	}

	private static <T> void way2(T result, Object obj) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(result, obj);
	}
}
