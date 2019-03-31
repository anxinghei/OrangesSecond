package Checked;

import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import imagesMethods.ImagesSaving;

public class _04_Expanding {

	public static String Qingshi(String string) throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// ∂¡»°Õº∆¨
		Mat src = Imgcodecs.imread(string);
		// ∏Ø ¥
		Mat dst = new Mat();
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size((2 * 2) + 1, (2 * 2) + 1));
		Imgproc.erode(src, dst, kernel);
		// ±£¥ÊÕº∆¨
		String url=ImagesSaving.ImagesNameToSaving(string, 4);
		Imgcodecs.imwrite(url, dst);

		return url;
	}
}
