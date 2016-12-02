package cn.xiedacon.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T parse(String json, Class<T> c) {
		try {
			return objectMapper.readValue(json, c);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static <T> List<T> parseList(String json, Class<T> c) {
		try {
			return objectMapper.readValue(json, TypeFactory.collectionType(List.class, c));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> parseList(File jsonFile, Class<T> c) {
		return JsonUtils.parseCollection(jsonFile, List.class, c);
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static <T> List<T> parseCollection(File jsonFile, Class<? extends Collection> collectionType,
			Class<T> elementType) {
		try {
			return objectMapper.readValue(jsonFile, TypeFactory.collectionType(collectionType, elementType));
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
