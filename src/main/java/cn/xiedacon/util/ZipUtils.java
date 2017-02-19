package cn.xiedacon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

public class ZipUtils {

	public static void upZip(File zipFile, String path) {
		try (ZipInputStream in = new ZipInputStream(new FileInputStream(zipFile));) {
			ZipEntry entry = in.getNextEntry();
			while (entry != null) {
				String name = entry.getName();
				try (FileOutputStream out = new FileOutputStream(new File(path + "/" + name));) {
					IOUtils.copy(in, out);
				}
				entry = in.getNextEntry();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
