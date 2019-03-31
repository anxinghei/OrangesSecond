package imagesMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImagesSaving {

	// �����ļ���
	public static void FolderCreating(String string) {
		// ·��+��ʽ
		// D:\\Config\\57421470783112381  jpg
		String[] strings=ImagesName.ImagesFormat(string);
		// ������ԭͼƬ���������ļ���
		File file=new File(strings[0]);
		if(!file.exists()){
			// ����ļ��в�����,�򴴽����ļ���
			file.mkdir();
		}
	}
	public static String ImagesNameToSaving(String string,int num) throws IOException {

		// �õ�·���� D:\Config\57421470783112381  �� �ļ���ʽjpg
		String[] strings=ImagesName.ImagesFormat(string);
		// ��������ͼƬ���
		String url=ImagesName.setImagesName(strings, num);
		
	    return url;
	}
}
