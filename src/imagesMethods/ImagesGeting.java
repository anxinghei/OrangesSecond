package imagesMethods;

import java.io.File;
import java.util.List;

public class ImagesGeting {

	public static String[] GetImages(String string) {
		// Â·¾¶+¸ñÊ½
		// D:\\Config\\57421470783112381  jpg
		String[] strings=ImagesName.ImagesFormat(string);
		 File file=new File(strings[0]);
		if(file.exists()){
			return file.list();
		}
		return null;
	}
	
}
