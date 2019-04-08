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

		// ��Ե��⣬�õ��ڵװױߵ�ͼƬ
		Mat src = Imgcodecs.imread(string);
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
		Mat dst = new Mat();
		int size = 20;
		Imgproc.Canny(gray, dst, size, size * 8);
		// not ��ɫ ��and ��������
		Mat dst_and = new Mat();
		Core.bitwise_not(dst,dst);
		Core.bitwise_and(src, src, dst_and, dst);

		// ����ͼƬ
		String url=ImagesSaving.ImagesNameToSaving(string, 3);
		Imgcodecs.imwrite(url, dst_and);

		return url;
	}

}
