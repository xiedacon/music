package cn.xiedacon.util;

import java.util.HashMap;
import java.util.Map;

public class MessageUtils {

	public static Map<String, Object> createError(String errorName, String errorValue) {
		Map<String, Object> error = new HashMap<>();
		error.put("name", errorName);
		error.put("value", errorValue);
		return createError(error);
	}

	public static Map<String, Object> createError(Map<String, Object> errorMap) {
		return createMessage(Constant.ERROR, "error", errorMap);
	}

	public static Map<String, Object> createSuccess() {
		return createMessage(Constant.SUCCESS);
	}

	public static Map<String, Object> createSuccess(String name, Object value) {
		return createMessage(Constant.SUCCESS, name, value);
	}

	public static Map<String, Object> createSuccess(Map<String, Object> map) {
		return createMessage(Constant.SUCCESS, "success", map);
	}

	public static Map<String, Object> createInfo(String name, Object value) {
		return createMessage(Constant.INFO, name, value);
	}

	public static Map<String, Object> createInfo(Map<String, Object> map) {
		return createMessage(Constant.SUCCESS, "info", map);
	}

	private static Map<String, Object> createMessage(int code, String name, Object value) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		result.put(name, value);
		return result;
	}

	private static Map<String, Object> createMessage(int code) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		return result;
	}
}
