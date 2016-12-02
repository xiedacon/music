package cn.xiedacon.util;

import java.util.HashMap;
import java.util.Map;

public class MessageUtils {

	/**
	 * 创建默认的成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createSuccess() {
		return createSuccess(null, null);
	}

	/**
	 * 创建携带数据的成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createSuccess(Object data) {
		return createSuccess(Constant.SUCCESS_RETURNNAME, data);
	}

	/**
	 * 创建成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createSuccess(String name, Object value) {
		return createMessage(Constant.SUCCESS, null, name, value);
	}

	/**
	 * 创建默认的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createInfo() {
		return createInfo(null, null, null);
	}

	/**
	 * 创建携带message信息的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createInfo(String message) {
		return createInfo(message, null, null);
	}

	/**
	 * 创建携带数据的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createInfo(String name, Object value) {
		return createInfo(null, name, value);
	}

	/**
	 * 创建警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createInfo(String message, String name, Object value) {
		return createMessage(Constant.INFO, message, name, value);
	}

	/**
	 * 创建默认的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createError() {
		return createError(null, null, null);
	}

	/**
	 * 创建携带message信息的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createError(String message) {
		return createError(message, null, null);
	}

	/**
	 * 创建携带数据的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createError(String name, Object value) {
		return createError(null, name, value);
	}

	/**
	 * 创建失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> createError(String message, String name, Object value) {
		Map<String, Object> error = new HashMap<>();
		if (name != null && value != null) {
			error.put("name", name);
			error.put("value", value);
		}
		return createMessage(Constant.ERROR, message, "error", error);
	}

	private static Map<String, Object> createMessage(int code, String message, String name, Object value) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		if (message != null) {
			result.put("message", message);
		}
		if (name != null && value != null) {
			result.put(name, value);
		}
		return result;
	}
}
