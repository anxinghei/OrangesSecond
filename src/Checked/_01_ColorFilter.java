package Checked;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesName;
import imagesMethods.ImagesSaving;

public class _01_ColorFilter {

	public static String Shaixuan(String string) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// 得到原图片
//		String string="D:\\Config\\23456\\0.jpg";
		Mat src = Imgcodecs.imread(string);
		// 颜色空间转换
		Mat dst = new Mat();
		Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2YCrCb);
		// 提取YCrCb空间的Cr向量
		Mat dst_cr;
		List<Mat> mv = new ArrayList<Mat>();
		Core.split(dst, mv);
		dst_cr = mv.get(1);
		// 阈值分割
		Imgproc.threshold(dst_cr, dst, 0, 255, Imgproc.THRESH_OTSU);
		// 按位与，保留柑橘
		Mat dst_and = new Mat();
		Core.bitwise_and(src, src, dst_and, dst);

		// 保存图片
		String url=ImagesSaving.ImagesNameToSaving(string, 1);
		Imgcodecs.imwrite(url, dst_and);

		return url;
	}
}
