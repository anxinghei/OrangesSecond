package Checked;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesSaving;

public class _05_KaiYunSuan {

	public static String Kai(String string) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// ∂¡»°Õº∆¨
		Mat src = Imgcodecs.imread(string);
		Mat dst = new Mat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size( 1,  1));
		Imgproc.morphologyEx(src, dst,Imgproc.MORPH_OPEN, kernel);
		// ±£¥ÊÕº∆¨
		String url=ImagesSaving.ImagesNameToSaving(string, 5);
		Imgcodecs.imwrite(url, dst);

		return url;
	}

}
