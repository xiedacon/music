package cn.xiedacon.util.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * <h1>文件下载工具类</h1>
 * <h3>功能：</h3>
 * <ul>
 * <li>将File或FileInputStream中的内容写入response内</li>
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
public class DownLoadUtlils {

	public static void write(HttpServletResponse response, File file, String fileName, String contentType) {
		addHeaders(response, createHeaders(contentType, fileName));

		try (FileInputStream in = new FileInputStream(file)) {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void write(HttpServletResponse response, FileInputStream in, String fileName, String contentType) {
		addHeaders(response, createHeaders(contentType, fileName));

		try {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	private static Map<String, String> createHeaders(String contentType, String fileName) {
		fileName = URLEncoder.encode(fileName);

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", contentType);
		headers.put("Content-Disposition", "attachment;filename=" + fileName);
		return headers;
	}

	private static void addHeaders(HttpServletResponse response, Map<String, String> heads) {
		for (Map.Entry<String, String> head : heads.entrySet()) {
			response.addHeader(head.getKey(), head.getValue());
		}
	}
}
