package cn.xiedacon.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {

	public static String encode(String value){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64 = new BASE64Encoder();
			
			return base64.encode(md5.digest(value.getBytes("utf-8"))).substring(0, 22);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}