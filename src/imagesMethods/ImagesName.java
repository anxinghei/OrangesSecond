package imagesMethods;

import java.io.File;

public class ImagesName {

	// �и��ַ������õ�1��·����ͼƬ����2��ͼƬ��ʽ
	public static String[] ImagesFormat(String string) {

		return string.split("\\.");
	}

	// �ϲ��и�õ����ַ������飨����������strings����ImagesFormat
	public static String setImagesName(String[] strings, int num) {
		// ���� ԭͼ����·��ȥ��.jpg,�ټ���
		strings[0] = strings[0].substring(0, strings[0].length() - 1);
		
		return strings[0] + String.valueOf(num) + "." + strings[1];
	}

	// ������ʾͼƬʱ��url(file:����·��)
	public static String toShowing(String string) {

		return "file:" + string;
	}
}
