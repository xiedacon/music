package cn.xiedacon.util.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class DownLoadUtlils {

	@SuppressWarnings("deprecation")
	public static void write(HttpServletResponse response, File file, String fileName, String contentType) {
		fileName = URLEncoder.encode(fileName);

		Map<String, String> heads = new HashMap<>();
		heads.put("Content-Type", contentType);
		heads.put("Content-Disposition", "attachment;filename=" + fileName);
		heads.put("Content-Length", "" + file.length());
		addHeads(response, heads);

		try (FileInputStream in = new FileInputStream(file)) {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public static void write(HttpServletResponse response, FileInputStream in, String fileName, String contentType) {
		fileName = URLEncoder.encode(fileName);

		Map<String, String> heads = new HashMap<>();
		heads.put("Content-Type", contentType);
		heads.put("Content-Disposition", "attachment;filename=" + fileName);
		addHeads(response, heads);
		
		try {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void addHeads(HttpServletResponse response, Map<String, String> heads) {
		for (Map.Entry<String, String> head : heads.entrySet()) {
			response.addHeader(head.getKey(), head.getValue());
		}
	}
}
