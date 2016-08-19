package cn.xiedacon.util;

import java.lang.reflect.Field;

public class BuilderUtils {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T buildFrom(Class c, Object obj) {
		T result = null;
		try {
			result = (T) c.newInstance();

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
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
