package Checked;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Laplacian {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src = Imgcodecs.imread("F:\\2011031213205880528.jpg");

        Mat dst = src.clone();
        Imgproc.GaussianBlur(src, dst, new Size(3, 3), 0);

        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);

        Imgproc.Laplacian(dst, dst, -1, 3, 1, 0, Core.BORDER_DEFAULT);
//        convertScaleAbs( dst, abs_dst );
        Imgcodecs.imwrite("F:\\dst.jpg", dst);

	}
}
