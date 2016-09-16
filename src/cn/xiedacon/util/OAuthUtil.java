package cn.xiedacon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class OAuthUtil {

	public static Map<String, Object> oAuthGithub(HttpServletRequest request) {
		Properties prop = getProperties(
				request.getServletContext().getResourceAsStream("WEB-INF/classes/oauth_github.properties"));

		// 设置相关证明参数
		StringBuilder sb = new StringBuilder("https://github.com/login/oauth/access_token?");
		sb.append("client_id=" + prop.getProperty("client_id")); // 必须
		sb.append("&client_secret=" + prop.getProperty("client_secret")); // 必须
		sb.append("&code=" + request.getParameter("code")); // 必须
		sb.append("&state=" + request.getParameter("state")); // 可选

		// 向第三方服务器发送请求
		String responseBody = request(sb.toString(),"POST");
		Map<String, Object> params = parseJSON(responseBody);

		sb = new StringBuilder("https://api.github.com/user?");
		sb.append("access_token=" + params.get("access_token"));
		
		// 向第三方服务器发送请求
		responseBody = request(sb.toString(),"GET");

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

	private static String request(String str,String method) {
		BufferedReader br = null;
		try {
			URL url = new URL(str);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setRequestProperty("Accept", "application/json");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ISO-8859-1"));

			String inputLine;
			StringBuilder responseBody = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				responseBody.append(inputLine);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
			}
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
			if ("{".equals(value.indexOf(0))) {
				params.put(key, parseJSON(value));
			} else {
				params.put(key, value.replace("\"", ""));
			}
		}
		return params;
	}
}
