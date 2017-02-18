package cn.xiedacon.util;

import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class AdminManager {

	private static Map<String, Cookie> map = new WeakHashMap<>();
	private static String flagName = "adminFlag";

	public static void sign(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(flagName, UUIDUtils.uuid(name));
		cookie.setMaxAge(60 * 30);
		map.put(cookie.getValue(), cookie);
		response.addCookie(cookie);
	}

	public static void signout(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (flagName.equals(cookie.getName())) {
				map.remove(cookie.getValue());
				return;
			}
		}
	}

	public static Boolean isSigned(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (flagName.equals(cookie.getName())) {
				return map.containsKey(cookie.getValue());
			}
		}
		return false;
	}
}
