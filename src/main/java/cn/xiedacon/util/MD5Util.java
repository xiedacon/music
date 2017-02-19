package cn.xiedacon.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * <h1>MD5工具类</h1>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class MD5Util {

	public static String encode(String value) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(md5.digest(value.getBytes("utf-8"))).substring(0, 22);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
