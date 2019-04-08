package Checked;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesName;
import imagesMethods.ImagesSaving;
import javafx.scene.image.Image;

public class _03_CannyEdges {

	public static String Edges(String string) throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// ±ßÔµ¼ì²â£¬µÃµ½ºÚµ×°×±ßµÄÍ¼Æ¬
		Mat src = Imgcodecs.imread(string);
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		Mat dst = new Mat();
		int size = 20;
		Imgproc.Canny(gray, dst, size, size * 8);
		// not ·´É« £¬and ±£Áô¸ÌéÙ
		Mat dst_and = new Mat();
		Core.bitwise_not(dst,dst);
		Core.bitwise_and(src, src, dst_and, dst);

		// ±£´æÍ¼Æ¬
		String url=ImagesSaving.ImagesNameToSaving(string, 3);
		Imgcodecs.imwrite(url, dst_and);

		return url;
	}

}
