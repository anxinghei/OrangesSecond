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
	// 读取图片序列
	private static int num=0;
	// 原图片地址
	private static String str="";
	// 柑橘个数
	int a=0;

	// 上一張圖片
	@FXML
	public void LastImage(ActionEvent event) throws IOException {
		// 得到文件夾的圖片
		String[] strings=ImagesGeting.GetImages(str);
		// 切割文件名，得到路徑
		String[] strSpilt=ImagesName.ImagesFormat(str);
		if(num>1) {
			num--;
			// 设置图片的地址，使得能用于显示
			String string=strSpilt[0]+"\\"+strings[num-1];
			ImagesToShowing(string);
			System.out.println(string);
		}
	}
	
	// 下一張圖片
	@FXML
	public void NextImage(ActionEvent event) throws IOException {
		
		// 得到文件夾的圖片
		String[] strings=ImagesGeting.GetImages(str);
		// 切割文件名，得到路徑
		String[] strSpilt=ImagesName.ImagesFormat(str);
		//不显示缩放后的原图
		if(num==0) {
			num++;
		}
		if(num<strings.length) {
			num++;
			// 设置图片的地址，使得能用于显示
			String string=strSpilt[0]+"\\"+strings[num-1];
			ImagesToShowing(string);
			System.out.println(string);
		}
	}
	
	// 图片（待识别）的选择与展示
	@FXML
	public void ChoosingImages(ActionEvent event) throws Exception {
		// 得到图片绝对地址
		str=ImagesChooser.imagesChooser();
		if(str!=null) {
			// 创建同级的文件夹
			ImagesSaving.FolderCreating(str);
			num=0;
			// 图片显示 xxx\\xxx\\0.jpg
			String string=ImagesToShowing(str);
			string=_01_ColorFilter.Shaixuan(string);
			string=_02_MedianBlur.Blur(string);
			string=_03_CannyEdges.Edges(string);
			string=_04_Expanding.Qingshi(string);
			string=_05_KaiYunSuan.Kai(string);
			a=_06_minRect.minRect(string);
		}
	
	}
	
	// 显示图片
	public String ImagesToShowing(String string) throws IOException {
		
		if(num==0) {
			//原图缩放
			String string1 = ImagesZooming.Zooming(string);
			String string2 = ImagesName.toShowing(string1);
			Image image = new Image(string2);
			imv.setImage(image);
			return string1;
		}else {
			//检测图
			String string3 = ImagesName.toShowing(string);
			Image image = new Image(string3);
			imv.setImage(image);
			return string3;
		}
	}
	
}
