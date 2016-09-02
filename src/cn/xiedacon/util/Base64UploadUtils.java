package cn.xiedacon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import sun.misc.BASE64Decoder;

public class Base64UploadUtils {

	private static FileItemFactory factory = null;
	private static ThreadLocal<Map<String, FileItem>> fileLists = new ThreadLocal<>();
	private static Properties properties = null;

	@SuppressWarnings("deprecation")
	public static void parseRequest(HttpServletRequest req) {
		try {
			List<FileItem> fileList = getFileUpload(req).parseRequest(req);
			Map<String, FileItem> map = new HashMap<>();
			for (FileItem i : fileList) {
				map.put(i.getFieldName(), i);
			}
			fileLists.set(map);
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		}
	}

	public static FileItem get(String name) {
		return fileLists.get().get(name);
	}

	public static String getString(String name) {
		return getString(name, "UTF-8");
	}

	public static String getString(String name, String charset) {
		try {
			FileItem value = get(name);
			if (value == null) {
				return null;
			}
			return value.getString(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static Integer getInteger(String name) {
		String value = getString(name);
		if (getString(name) == null) {
			return null;
		}
		return Integer.valueOf(value);
	}

	public static Double getDouble(String name) {
		String value = getString(name);
		if (getString(name) == null) {
			return null;
		}
		return Double.valueOf(value);
	}

	public static File getFile(String name, String pathname) {
		FileItem fileItem = get(name);
		File file = new File(pathname);
		FileOutputStream out = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(fileItem.getInputStream());
			out = new FileOutputStream(file);
			out.write(bytes);
			return file;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String getContentType(String name) {
		return get(name).getContentType();
	}

	public static String getFileName(String name) {
		return get(name).getName();
	}

	private static FileUpload getFileUpload(HttpServletRequest req) {
		FileUpload upload = new FileUpload(getFactory(req));
		upload.setFileSizeMax(Integer.valueOf(properties.getProperty("fileSizeMax")));
		upload.setHeaderEncoding(properties.getProperty("encoding"));
		upload.setSizeMax(Integer.valueOf(properties.getProperty("sizeMax")));
		return upload;
	}

	private static FileItemFactory getFactory(HttpServletRequest req) {
		if (factory == null) {
			synchronized (Base64UploadUtils.class) {
				if (factory == null) {
					properties = new Properties();
					try {
						properties.load(new FileInputStream(getRealPath(req, "WEB-INF/classes/upload.properties")));
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(Integer.valueOf(properties.getProperty("sizeThreshold")));
					factory.setRepository(new File(getRealPath(req, properties.getProperty("repository"))));
					Base64UploadUtils.factory = factory;
				}
			}
		}
		return factory;
	}

	private static String getRealPath(HttpServletRequest req, String url) {
		return req.getServletContext().getRealPath(url);
	}
}
