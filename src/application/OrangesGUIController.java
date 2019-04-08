package application;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;

import Checked._01_ColorFilter;
import Checked._02_MedianBlur;
import Checked._03_CannyEdges;
import Checked._04_Expanding;
import Checked._05_KaiYunSuan;
import Checked._06_minRect;
import imagesMethods.ImagesChooser;
import imagesMethods.ImagesGeting;
import imagesMethods.ImagesName;
import imagesMethods.ImagesSaving;
import imagesMethods.ImagesZooming;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OrangesGUIController {
	@FXML
	private Button as;
	@FXML
	private ImageView imv;
	@FXML
	private Button next;
	// ��ȡͼƬ����
	private static int num=0;
	// ԭͼƬ��ַ
	private static String str="";
	// ���ٸ���
	int a=0;

	// ��һ���DƬ
	@FXML
	public void LastImage(ActionEvent event) throws IOException {
		// �õ��ļ��A�ĈDƬ
		String[] strings=ImagesGeting.GetImages(str);
		// �и��ļ������õ�·��
		String[] strSpilt=ImagesName.ImagesFormat(str);
		if(num>1) {
			num--;
			// ����ͼƬ�ĵ�ַ��ʹ����������ʾ
			String string=strSpilt[0]+"\\"+strings[num-1];
			ImagesToShowing(string);
			System.out.println(string);
		}
	}
	
	// ��һ���DƬ
	@FXML
	public void NextImage(ActionEvent event) throws IOException {
		
		// �õ��ļ��A�ĈDƬ
		String[] strings=ImagesGeting.GetImages(str);
		// �и��ļ������õ�·��
		String[] strSpilt=ImagesName.ImagesFormat(str);
		//����ʾ���ź��ԭͼ
		if(num==0) {
			num++;
		}
		if(num<strings.length) {
			num++;
			// ����ͼƬ�ĵ�ַ��ʹ����������ʾ
			String string=strSpilt[0]+"\\"+strings[num-1];
			ImagesToShowing(string);
			System.out.println(string);
		}
	}
	
	// ͼƬ����ʶ�𣩵�ѡ����չʾ
	@FXML
	public void ChoosingImages(ActionEvent event) throws Exception {
		// �õ�ͼƬ���Ե�ַ
		str=ImagesChooser.imagesChooser();
		if(str!=null) {
			// ����ͬ�����ļ���
			ImagesSaving.FolderCreating(str);
			num=0;
			// ͼƬ��ʾ xxx\\xxx\\0.jpg
			String string=ImagesToShowing(str);
			string=_01_ColorFilter.Shaixuan(string);
			string=_02_MedianBlur.Blur(string);
			string=_03_CannyEdges.Edges(string);
			string=_04_Expanding.Qingshi(string);
			string=_05_KaiYunSuan.Kai(string);
			a=_06_minRect.minRect(string);
		}
	
	}
	
	// ��ʾͼƬ
	public String ImagesToShowing(String string) throws IOException {
		
		if(num==0) {
			//ԭͼ����
			String string1 = ImagesZooming.Zooming(string);
			String string2 = ImagesName.toShowing(string1);
			Image image = new Image(string2);
			imv.setImage(image);
			return string1;
		}else {
			//���ͼ
			String string3 = ImagesName.toShowing(string);
			Image image = new Image(string3);
			imv.setImage(image);
			return string3;
		}
	}
	
}
