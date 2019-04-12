package Checked;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class _06_minRect {

	public static int minRect(String string) throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = Imgcodecs.imread(string);
		// 图片预处理，多通道 -> 单通道
		Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
		Imgproc.threshold(src, src, 100, 255, Imgproc.THRESH_BINARY_INV);
		// 轮廓查找
		List<MatOfPoint> contours = new ArrayList<>();
		Imgproc.findContours(src, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);		
		// 寻找图像凸包
		List<MatOfInt> hull = new ArrayList(contours.size());
		for (int i = 0; i < contours.size(); i++) {
			hull.add(new MatOfInt());
			Imgproc.convexHull(contours.get(i), hull.get(i), false);
		}
		// 类型转换： MatOfInt -> MatOfPoint
		List<MatOfPoint> matOfPoint = new ArrayList();
		for (int i = 0; i < contours.size(); i++) {
			matOfPoint.add(_06_minRect.convertIndexesToPoints(contours.get(i), hull.get(i)));
		}
		Mat mat = new Mat(src.rows(), src.cols(), CvType.CV_8UC1);
		List<RotatedRect> rects = new ArrayList<RotatedRect>();
		MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
		double area = 0;
		int a = 0;
		// 面积筛选，添加外接矩形
		for (int i = 0; i < matOfPoint.size()-1; i++) {
			area = Imgproc.contourArea(matOfPoint.get(i));
			if (area > 1300 ) {
				a++;
				matOfPoint2f = new MatOfPoint2f(matOfPoint.get(i).toArray());
				rects.add(Imgproc.minAreaRect(matOfPoint2f));
			}
		}
		// 画外接矩形到原图片
		String img=imagesMethods.ImagesSaving.ImagesNameToSaving(string, 0);
		Mat source=Imgcodecs.imread(img);
		for (int i = 0; i < rects.size(); i++) {
			Point points[] = new Point[4];
			rects.get(i).points(points);
			for (int j = 0; j < 4; ++j) {
				Imgproc.line(source, points[j], points[(j + 1) % 4], new Scalar(255, 255, 255),4);
			}
		}
		// 保存图片 
		String url=imagesMethods.ImagesSaving.ImagesNameToSaving(string,6);
		Imgcodecs.imwrite(url, source);
		return a;
	}

	public static MatOfPoint convertIndexesToPoints(MatOfPoint contour, MatOfInt indexes) {
		int[] arrIndex = indexes.toArray();
		Point[] arrContour = contour.toArray();
		Point[] arrPoints = new Point[arrIndex.length];

		for (int i = 0; i < arrIndex.length; i++) {
			arrPoints[i] = arrContour[arrIndex[i]];
		}

		MatOfPoint hull = new MatOfPoint();
		hull.fromArray(arrPoints);
		return hull;
	}

}
