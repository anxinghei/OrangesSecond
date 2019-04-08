package imagesMethods;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImagesChooser {
	
	public static String imagesChooser() {
		// 新建文件选择器
		FileChooser fileChooser = new FileChooser();
		// 设置文件选择器标题
		fileChooser.setTitle("View Pictures");
		// 设置初始位置
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // 设置可读取的文件类型
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // 选择文件
        File file = fileChooser.showOpenDialog(new Stage());
        if(file==null)
        	return null;
        // 返回文件所在路径
        return file.getAbsolutePath();
        
	}
	
}
