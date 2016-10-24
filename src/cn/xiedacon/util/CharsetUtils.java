package cn.xiedacon.util;

import java.io.UnsupportedEncodingException;

public class CharsetUtils {

	public static String change(String value, String fromCharset, String toCharset) {
		try {
			return new String(value.getBytes(fromCharset), toCharset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
