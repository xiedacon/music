package cn.xiedacon.util.file;

import java.io.File;
import java.io.IOException;

/**
 * <h1>文件操作工具类</h1>
 * <h3>依赖：</h3>
 * <ul>
 * <li>common-io</li>
 * </ul>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 */
public class FileUtils {

	public static void copy(File from, File to) {
		try {
			org.apache.commons.io.FileUtils.copyFile(from, to);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
