package cn.xiedacon.util.file;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	public static void copy(File from, File to) {
		try {
			org.apache.commons.io.FileUtils.copyFile(from, to);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
