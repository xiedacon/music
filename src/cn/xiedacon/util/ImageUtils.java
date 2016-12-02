package cn.xiedacon.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static File resize(File imageFile, Double xRatio, Double yRatio, Double widthRatio, Double heightRatio) {
		try {
			BufferedImage in = ImageIO.read(imageFile);
			Integer width = in.getWidth();
			Integer height = in.getHeight();

			Double width1 = width.doubleValue() * widthRatio;
			Double height1 = height.doubleValue() * heightRatio;
			Double x1 = height.doubleValue() * xRatio * (-1);
			Double y1 = height.doubleValue() * yRatio * (-1);

			BufferedImage out = new BufferedImage(width1.intValue(), height1.intValue(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = out.createGraphics();
			g.drawImage(in, x1.intValue(), y1.intValue(), width.intValue(), height.intValue(), null);
			g.dispose();
			Integer index = imageFile.getPath().lastIndexOf(".");
			String type = imageFile.getPath().substring(index + 1);
			ImageIO.write(out, type, imageFile);
			return imageFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
