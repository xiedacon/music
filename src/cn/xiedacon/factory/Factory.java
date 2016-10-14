package cn.xiedacon.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import cn.xiedacon.util.Constant;

@Component
public class Factory {

	@SuppressWarnings("rawtypes")
	private Map<Class, Map<String, Object>> configMaps = new HashMap<>(); // 配置信息
	@SuppressWarnings("rawtypes")
	private Map<Class, Object> cacheMap = new HashMap<>(); // 缓存

	// 以后弄到xml配置中
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Factory() {
		Map<String, Object> configMap = new HashMap<>();

		configMap.put("icon", Constant.USER_ICON_DEFAULT);
		configMap.put("dynamicNum", Constant.NUM_DEFAULT);
		configMap.put("attentionNum", Constant.NUM_DEFAULT);
		configMap.put("fansNum", Constant.NUM_DEFAULT);
		configMap.put("createSongMenuNum", Constant.NUM_DEFAULT);
		configMap.put("collectSongMenuNum", Constant.NUM_DEFAULT);
		configMap.put("level", Constant.NUM_DEFAULT);
		configMap.put("experience", Constant.NUM_DEFAULT);
		configMap.put("visible", Constant.VISIBLE_DEFAULT);
		configMaps.put(cn.xiedacon.model.User.class, configMap);

		configMap = new HashMap<>();

		configMap.put("icon", Constant.SONGMENU_IOCN_DEFAULT);
		configMap.put("songNum", Constant.NUM_DEFAULT);
		configMap.put("isPublic", Constant.SONGMENU_ISPUBLIC_DEFAULT);
		configMap.put("collectionNum", Constant.NUM_DEFAULT);
		configMap.put("shareNum", Constant.NUM_DEFAULT);
		configMap.put("commentNum", Constant.NUM_DEFAULT);
		configMap.put("playNum", Constant.NUM_DEFAULT);
		configMap.put("visible", Constant.VISIBLE_DEFAULT);
		configMaps.put(cn.xiedacon.model.SongMenu.class, configMap);

		configMap = new HashMap<>();
		
		configMap.put("agreeNum", Constant.NUM_DEFAULT);
		configMap.put("isChecked", Constant.COMMENT_ISCHECKED_DEFAULT);
		
		configMaps.put(cn.xiedacon.model.Comment.class, configMap);
		
		configMap = new HashMap<>();
		
		configMap.put("collectionNum", Constant.NUM_DEFAULT);
		configMap.put("visible", Constant.VISIBLE_DEFAULT);
		
		configMaps.put(cn.xiedacon.model.Singer.class, configMap);
		
		configMap = new HashMap<>();
		
		configMap.put("collectionNum", Constant.NUM_DEFAULT);
		configMap.put("playNum", Constant.NUM_DEFAULT);
		configMap.put("commentNum", Constant.NUM_DEFAULT);
		configMap.put("visible", Constant.VISIBLE_DEFAULT);
		
		configMaps.put(cn.xiedacon.model.Song.class, configMap);
		
		Iterator<Class> iterator = configMaps.keySet().iterator();

		while (iterator.hasNext()) {
			Class c = iterator.next();
			Object object = get(c);
			cacheMap.put(c, object);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> c) {
		T object = (T) cacheMap.get(c);

		if (object != null) {
			T instance = getInstance(c);
			return cloneOject(object, instance);
		}

		Map<String, Object> configMap = configMaps.get(c);

		if (configMap == null) {
			return getInstance(c);
		}

		T instance = getInstance(c);
		
		for (Entry<String, Object> entry : configMap.entrySet()) {
			String fieldName = entry.getKey();
			Object defaultValue = entry.getValue();
			setValue(c, instance, fieldName, defaultValue);
		}

		return instance;
	}

	private <T> T cloneOject(T from, T to) {
		try {
			Field[] fields1 = from.getClass().getDeclaredFields();
			Field[] fields2 = to.getClass().getDeclaredFields();

			for (int i = 0; i < fields1.length; i++) {
				Field field1 = fields1[i];
				for (int j = 0; j < fields2.length; j++) {
					Field field2 = fields2[j];
					if (field1.getName().equals(field2.getName())) {
						field1.setAccessible(true);
						field2.setAccessible(true);
						field1.set(to, field2.get(from));
						break;
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return to;
	}

	private <T> T getInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private <T> void setValue(Class<T> c, Object instance, String name, Object value) {
		try {
			Field field = c.getDeclaredField(name);
			field.setAccessible(true);
			field.set(instance, value);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}
}
