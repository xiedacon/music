package cn.xiedacon.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.io.FileCleaningTracker;

public class Base64UploadUtils {

    private static FileItemFactory factory = null;
    private static Properties properties = null;

    @SuppressWarnings("deprecation")
    public static Map<String, Base64FileItem> parseRequest(HttpServletRequest req) {
	try {
	    List<FileItem> fileList = getFileUpload(req).parseRequest(req);
	    Map<String, Base64FileItem> map = new HashMap<>();
	    for (FileItem i : fileList) {
		map.put(i.getFieldName(), new Base64FileItem(i));
	    }
	    return map;
	} catch (FileUploadException e) {
	    throw new RuntimeException(e);
	}
    }

    private static FileUpload getFileUpload(HttpServletRequest req) {
	FileUpload upload = new FileUpload(getFactory(req));
	upload.setFileSizeMax(Integer.valueOf(properties.getProperty("fileSizeMax")));
	upload.setHeaderEncoding(properties.getProperty("encoding"));
	upload.setSizeMax(Integer.valueOf(properties.getProperty("sizeMax")));
	return upload;
    }

    private static synchronized FileItemFactory getFactory(HttpServletRequest req) {
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
		    // 临时文件清除器
		    FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
			    .getFileCleaningTracker(req.getServletContext());
		    factory.setFileCleaningTracker(fileCleaningTracker);
		    Base64UploadUtils.factory = factory;
	}
	return factory;
    }

    private static String getRealPath(HttpServletRequest req, String url) {
	return req.getServletContext().getRealPath(url);
    }
}
