package cn.xiedacon.util;

import java.io.UnsupportedEncodingException;

/**
 * <h1>字符集工具类</h1>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class CharsetUtils {
	
	public static String change(String value, String fromCharset, String toCharset) {
		try {
			return new String(value.getBytes(fromCharset), toCharset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
