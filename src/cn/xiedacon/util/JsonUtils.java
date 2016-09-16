package cn.xiedacon.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T parse(String json, Class<T> c) {
		try {
			return objectMapper.readValue(json, c);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String stringify(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
