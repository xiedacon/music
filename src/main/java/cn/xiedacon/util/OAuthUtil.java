package cn.xiedacon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.xiedacon.util.http.HttpUtils;

public class OAuthUtil {

	private static String configLocaltion = "WEB-INF/classes/oauth_github.properties";

	public static Map<String, Object> oAuthGithub() {
		return OAuthUtil.oAuthGithub("", "");
	}

	public static Map<String, Object> oAuthGithub(String code, String state) {
		Properties prop = getProperties(ResourceLoader.loadAsStream(configLocaltion));

		// 设置相关证明参数
		StringBuilder sb = new StringBuilder("https://github.com/login/oauth/access_token?");
		sb.append("client_id=" + prop.getProperty("client_id")); // 必须
		sb.append("&client_secret=" + prop.getProperty("client_secret")); // 必须
		sb.append("&code=" + code); // 必须
		sb.append("&state=" + state); // 可选

		// 向第三方服务器发送请求
		String responseBody = HttpUtils.post(sb.toString());
		Map<String, Object> params = parseJSON(responseBody);

		sb = new StringBuilder("https://api.github.com/user?");
		sb.append("access_token=" + params.get("access_token"));

		// 向第三方服务器发送请求
		responseBody = HttpUtils.get(sb.toString());

		return params = parseJSON(responseBody);
	}

	private static Properties getProperties(InputStream in) {
		Properties prop = new Properties();
		try {
			prop.load(in);
			return prop;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Map<String, Object> parseJSON(String json) {
		String str = json.substring(1, json.length() - 1);
		String[] paramStrings = str.split("\",\"");
		Map<String, Object> params = new HashMap<>();
		String key;
		String value;
		int index;
		for (String param : paramStrings) {
			index = param.indexOf(":");
			key = param.substring(0, index);
			key = key.replace("\"", "");
			value = param.substring(index + 1);
			if (value == null || "".equals(value)) {
				params.put(key, null);
				continue;
			}
			if ("{".equals(value.substring(0, 1))) {
				params.put(key, parseJSON(value));
			} else {
				params.put(key, value.replace("\"", ""));
			}
		}
		return params;
	}
}
