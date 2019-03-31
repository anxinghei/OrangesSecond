package imagesMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImagesSaving {

	// 创建文件夹
	public static void FolderCreating(String string) {
		// 路径+格式
		// D:\\Config\\57421470783112381  jpg
		String[] strings=ImagesName.ImagesFormat(string);
		// 创建以原图片名命名的文件夹
		File file=new File(strings[0]);
		if(!file.exists()){
			// 如果文件夹不存在,则创建新文件夹
			file.mkdir();
		}
	}
	public static String ImagesNameToSaving(String string,int num) throws IOException {

		// 得到路径名 D:\Config\57421470783112381  和 文件格式jpg
		String[] strings=ImagesName.ImagesFormat(string);
		// 给处理后的图片编号
		String url=ImagesName.setImagesName(strings, num);
		
	    return url;
	}
}
