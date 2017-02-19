package cn.xiedacon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class ResourceLoader {

	private static ServletContext servletContext;

	public static void setServletContext(ServletContext servletContext) {
		ResourceLoader.servletContext = servletContext;
	}

	public static String getResourceAsString(String uri) {
		InputStream in = servletContext.getResourceAsStream(uri);
		if (in == null) {
			return null;
		}
		try {
			return IOUtils.toString(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static InputStream loadAsStream(String uri) {
		try {
			return new FileInputStream(servletContext.getRealPath(uri));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static File loadAsFile(String realUri, String virtualUri) {
		virtualUri = virtualUri == null ? "" : virtualUri;
		return new File(servletContext.getRealPath(realUri) + virtualUri);
	}
	
	public static String getRealPath(String realUri) {
		return servletContext.getRealPath(realUri);
	}

	public static void writeToResponse(String uri, HttpServletResponse response) {
		writeToResponse(uri, response, "UTF-8");
	}

	public static void writeToResponse(String uri, HttpServletResponse response, String charset) {
		InputStream in = servletContext.getResourceAsStream(uri);
		if (in == null) {
			return;
		}
		try {
			response.setCharacterEncoding(charset);
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
