package Checked;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Test {

	public static void main(String[] args) throws Exception {

//		String string=_01_ColorFilter.Shaixuan("D:\\Config\\34567\\0.jpg");
//		String string=_02_MedianBlur.Blur("D:\\Config\\34567\\1.jpg");
//		String string=_03_CannyEdges.Edges("D:\\Config\\34567\\2.jpg");
//		String string=_04_Expanding.Qingshi("D:\\Config\\34567\\3.jpg");
//		String string=_05_KaiYunSuan.Kai("D:\\Config\\34567\\4.jpg");
//		String string=_06_Gray.HuiDu("D:\\Config\\34567\\5.jpg");
//		String string = _07_Binary.Binary("D:\\Config\\34567\\6.jpg");
		String string = _08_ConvexHull.ConvexHull("D:\\Config\\34567\\7.jpg");
		System.out.println(string);
		
//		name2();

	}

	public static void name2() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src=Imgcodecs.imread("D:\\Config\\34567\\7.jpg");
		Mat hierarchy = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(src, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE,new Point(0, 0));
		
		// Find the convex hull
		List<MatOfInt> hull = new ArrayList<MatOfInt>();
		for (int i = 0; i < contours.size(); i++) {
			hull.add(new MatOfInt());
		}
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.convexHull(contours.get(i), hull.get(i));
		}

		// Convert MatOfInt to MatOfPoint for drawing convex hull
		// Loop over all contours
		List<Point[]> hullpoints = new ArrayList<Point[]>();
		for (int i = 0; i < hull.size(); i++) {
			Point[] points = new Point[hull.get(i).rows()];

			// Loop over all points that need to be hulled in current contour
			for (int j = 0; j < hull.get(i).rows(); j++) {
				int index = (int) hull.get(i).get(j, 0)[0];
				points[j] = new Point(contours.get(i).get(index, 0)[0], contours.get(i).get(index, 0)[1]);
			}

			hullpoints.add(points);
		}

		// Convert Point arrays into MatOfPoint
		List<MatOfPoint> hullmop = new ArrayList<MatOfPoint>();
		for (int i = 0; i < hullpoints.size(); i++) {
			MatOfPoint mop = new MatOfPoint();
			mop.fromArray(hullpoints.get(i));
			hullmop.add(mop);
		}

		// Draw contours + hull results
		Mat overlay = new Mat(src.size(), CvType.CV_8UC3);
		Scalar color = new Scalar(0, 255, 0); // Green
		for (int i = 0; i < contours.size(); i++) {
			Imgproc.drawContours(overlay, contours, i, color);
			Imgproc.drawContours(overlay, hullmop, i, color);
		}
		
		Imgcodecs.imwrite("D:\\Config\\34567\\888.jpg", src);
	}
}
