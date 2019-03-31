package imagesMethods;

import java.io.File;

public class ImagesName {

	// 切割字符串，得到1、路径、图片名和2、图片格式
	public static String[] ImagesFormat(String string) {

		return string.split("\\.");
	}

	// 合并切割得到的字符串数组（重命名），strings来自ImagesFormat
	public static String setImagesName(String[] strings, int num) {
		// 返回 原图绝对路径去掉.jpg,再加上
		strings[0] = strings[0].substring(0, strings[0].length() - 1);
		
		return strings[0] + String.valueOf(num) + "." + strings[1];
	}

	// 设置显示图片时的url(file:绝对路径)
	public static String toShowing(String string) {

		return "file:" + string;
	}
}
