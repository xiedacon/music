package cn.xiedacon.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class HttpUtils {

	public static String post(String url) {
		return HttpUtils.request(url, "POST");
	}

	public static String get(String url) {
		return HttpUtils.request(url, "GET");
	}

	public static String request(String url, String method) {
		try {
			return HttpUtils.request(new URL(url), method);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public static String request(URL url, String method) {
		method = method.toUpperCase();

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setRequestProperty("Accept", "application/json");

			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), "ISO-8859-1"))) {
				return IOUtils.toString(br);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
