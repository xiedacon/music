package cn.xiedacon.util.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.io.IOUtils;

//import sun.misc.BASE64Decoder;

public class Base64FileItem implements FileItem {

	private static final long serialVersionUID = 8319514483825183494L;
	private FileItem fileItem;

	public Base64FileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	@Override
	public FileItemHeaders getHeaders() {
		return fileItem.getHeaders();
	}

	@Override
	public void setHeaders(FileItemHeaders arg0) {
		fileItem.setHeaders(arg0);
	}

	@Override
	public InputStream getInputStream() {
		try {
			return fileItem.getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getContentType() {
		return fileItem.getContentType();
	}

	@Override
	public String getName() {
		return fileItem.getName();
	}

	@Override
	public boolean isInMemory() {
		return fileItem.isInMemory();
	}

	@Override
	public long getSize() {
		return fileItem.getSize();
	}

	@Override
	public byte[] get() {
		return fileItem.get();
	}

	@Override
	public String getString(String charset) {
		try {
			return fileItem.getString(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getString() {
		try {
			return fileItem.getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(File file) {
		try (FileOutputStream out = new FileOutputStream(file)) {
			if (data == null) {
				parseBase64();
			}
			out.write(data);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Integer getInteger() {
		return Integer.valueOf(fileItem.getString());
	}

	public Double getDouble() {
		return Double.valueOf(fileItem.getString());
	}

	public Boolean getBoolean() {
		return Boolean.valueOf(fileItem.getString());
	}

	public Date getDate(String date) {
		try {
			return new SimpleDateFormat(date).parse(fileItem.getString());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public File getFile(String path) {
		File file = new File(path);
		try (FileOutputStream out = new FileOutputStream(file)) {
			if (data == null) {
				parseBase64();
			}
			out.write(data);
			return file;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getType() {
		if (type == null) {
			parseBase64();
		}
		return type;
	}

	private String type = null;
	private byte[] data = null;

	private void parseBase64() {
		//BASE64Decoder decoder = new BASE64Decoder();
		Decoder decoder = Base64.getDecoder();
		try (InputStream in = fileItem.getInputStream()) {
			String base64 = IOUtils.toString(in);
			String[] strs = base64.split(";base64,");
			type = strs[0].substring(5);

			//
			type = type.split("/")[1];

			//data = decoder.decodeBuffer(strs[1]);
			data = decoder.decode(strs[1]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete() {
		fileItem.delete();
	}

	@Override
	public String getFieldName() {
		return fileItem.getFieldName();
	}

	@Override
	public void setFieldName(String arg0) {
		fileItem.setFieldName(arg0);
	}

	@Override
	public boolean isFormField() {
		return fileItem.isFormField();
	}

	@Override
	public void setFormField(boolean arg0) {
		fileItem.setFormField(arg0);
	}

	@Override
	public OutputStream getOutputStream() {
		try {
			return fileItem.getOutputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
