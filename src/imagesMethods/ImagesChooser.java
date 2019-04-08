package imagesMethods;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImagesChooser {
	
	public static String imagesChooser() {
		// �½��ļ�ѡ����
		FileChooser fileChooser = new FileChooser();
		// �����ļ�ѡ��������
		fileChooser.setTitle("View Pictures");
		// ���ó�ʼλ��:user.dir�û��ĵ�ǰ����Ŀ¼ 
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // ���ÿɶ�ȡ���ļ�����
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        // ѡ���ļ�
        File file = fileChooser.showOpenDialog(new Stage());
        if(file==null)
        	return null;
        // �����ļ�����·��
        return file.getAbsolutePath();
        
	}
	
}
