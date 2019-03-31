package Checked;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesName;
import imagesMethods.ImagesSaving;

public class _02_MedianBlur {

	public static String Blur(String string) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// ÖÐÖµÂË²¨
		Mat src = Imgcodecs.imread(string);
		Mat dst = new Mat();
		Imgproc.medianBlur(src, dst, 5);

		// ±£´æÍ¼Æ¬
		String url=ImagesSaving.ImagesNameToSaving(string, 2);
		Imgcodecs.imwrite(url, dst);

		return url;
	}

}
