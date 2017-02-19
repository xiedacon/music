package cn.xiedacon.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 * <h1>Http工具类</h1>
 * <h3>功能：</h3>
 * <ul>
 * <li>发起get、post等请求</li>
 * </ul>
 * <h3>依赖：</h3>
 * <ul>
 * <li>common-io</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */

public class HttpUtils {

	private static String POST = "POST";
	private static String GET = "GET";
	private static String defaultAccept = "application/json";
	private static String charset = "ISO-8859-1";

	public static String post(String url) {
		return HttpUtils.request(url, POST);
	}

	public static String get(String url) {
		return HttpUtils.request(url, GET);
	}

	public static String request(String url, String requestMethod) {
		Map<String, String> properties = new HashMap<>();
		properties.put("Accept", defaultAccept);

		return HttpUtils.request(url(url), requestMethod, properties);
	}

	public static String request(URL url, String requestMethod, Map<String, String> properties) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			beforeConnect(connection, requestMethod, properties);
			String result = connect(connection);
			afterConnect(connection, result);

			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void beforeConnect(HttpURLConnection connection, String requestMethod,
			Map<String, String> properties) throws ProtocolException {
		connection.setRequestMethod(requestMethod.toUpperCase());

		for (Map.Entry<String, String> property : properties.entrySet()) {
			connection.setRequestProperty(property.getKey(), property.getValue());
		}
	}

	private static void afterConnect(HttpURLConnection connection, String result) {
	}

	private static String connect(HttpURLConnection connection) throws UnsupportedEncodingException, IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
			return IOUtils.toString(br);
		}
	}

	private static URL url(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
