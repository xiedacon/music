package cn.xiedacon.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class MessageUtils {

	public static Integer SUCCESS = 200;
	public static Integer INFO = 302;
	public static Integer ERROR = 404;
	public static String SUCCESS_RETURNNAME = "data";

	/**
	 * 创建默认的成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> success() {
		return success(null, null);
	}

	/**
	 * 创建携带数据的成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> success(Object data) {
		return success(SUCCESS_RETURNNAME, data);
	}

	/**
	 * 创建成功返回
	 * 
	 * @return
	 */
	public static Map<String, Object> success(String name, Object value) {
		return message(SUCCESS, null, name, value);
	}

	/**
	 * 创建默认的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> info() {
		return info(null, null, null);
	}

	/**
	 * 创建携带message信息的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> info(String message) {
		return info(message, null, null);
	}

	/**
	 * 创建携带数据的警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> info(String name, Object value) {
		return info(null, name, value);
	}

	/**
	 * 创建警告返回
	 * 
	 * @return
	 */
	public static Map<String, Object> info(String message, String name, Object value) {
		return message(INFO, message, name, value);
	}

	/**
	 * 创建默认的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> error() {
		return error(null, null, null);
	}

	/**
	 * 创建携带message信息的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> error(String message) {
		return error(message, null, null);
	}

	/**
	 * 创建携带数据的失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> error(String name, Object value) {
		return error(null, name, value);
	}

	/**
	 * 创建失败返回
	 * 
	 * @return
	 */
	public static Map<String, Object> error(String message, String name, Object value) {
		Map<String, Object> error = new HashMap<>();
		if (name != null && value != null) {
			error.put("name", name);
			error.put("value", value);
		}
		return message(ERROR, message, "error", error);
	}

	private static Map<String, Object> message(int code, String message, String name, Object value) {
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
