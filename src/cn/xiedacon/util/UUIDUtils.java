package cn.xiedacon.util;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

	public static String uuid(String value) {
		StringBuilder result = new StringBuilder(value);
		result.append(new Date().getTime());
		return uuid_base(result);
	}

	public static String uuid(Integer value) {
		return uuid(value.longValue());
	}

	public static String uuid(long value) {
		StringBuilder result = new StringBuilder(String.valueOf(value));
		return uuid_base(result);
	}

	public static String randomUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	private static String uuid_base(StringBuilder result) {
		result.append(Thread.currentThread().getId());
		result.append(UUID.randomUUID());
		return result.toString().replace("-", "").substring(0, 32);
	}
}
