package Checked;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesSaving;

public class _08_ConvexHull {

	public static String ConvexHull(String string) throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(string,1);
		// Ñ°ÕÒÍ¼ÏñÂÖÀª
//		src.convertTo(src, CvType.CV_8UC1);
//		Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//		Imgproc.threshold(src,src,100,255,Imgproc.THRESH_BINARY_INV);
		System.out.println(src.type());
		List<MatOfPoint> contours = new ArrayList<>();
		Point point = new Point(0, 0);
//		Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//	    Imgproc.Canny(src, src, 10, 100);
		Imgproc.findContours(src, contours, new Mat(), Imgproc.RETR_FLOODFILL, Imgproc.CHAIN_APPROX_SIMPLE);

		// Ñ°ÕÒÍ¼ÏñÍ¹°ü
		List hull = new ArrayList(contours.size());
		System.out.println(hull.size());
		for (int i = 0; i < contours.size(); i++) {
			hull.add(new MatOfInt());
			Imgproc.convexHull(contours.get(i), (MatOfInt) hull.get(i), false);
		}

		// »æÖÆÂÖÀªºÍÍ¹°ü
		Mat src_clone = src.clone();
		Scalar color = new Scalar(255, 255, 255);
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.drawContours(src_clone, contours, i, color, 1, 8, new Mat(), 0, new Point());
			Imgproc.drawContours(src_clone, hull, i, color, 1, 8, new Mat(), 0, new Point());
		}

		// ±£´æÍ¼Æ¬
		String url = ImagesSaving.ImagesNameToSaving(string, 888);
		Imgcodecs.imwrite("D:\\Config\\34567\\888.jpg", src_clone);

		return url;
	}
}
