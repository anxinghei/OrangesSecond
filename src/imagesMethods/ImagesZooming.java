package imagesMethods;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImagesZooming {
	public static String Zooming(String string) throws IOException {
		//获取原图片长宽x,y
		File picture=new File(string);
		BufferedImage sourceImg=ImageIO.read(new FileInputStream(picture));
		int x = sourceImg.getWidth();
		int y = sourceImg.getHeight();

		int view_x = 160*4;
		int view_y = 110*4; 
		while(x < view_x && y <view_y) {
			if(x/y<view_x/view_y) {
				x = x*view_y/y;
				y = view_y;
			}else {
				y = y*view_x/x;
				x = view_x;
			}
		}
		while(x > view_x || y >view_y) {
			if((float)x/y<(float)view_x/view_y) {
				x = x*view_y/y;
				y = view_y;
			}else {
				y = y*view_x/x;
				x = view_x;
			}
		}
		
		BufferedImage bufferedImage=new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        Graphics graphics=bufferedImage.getGraphics();
        
        //读取原始位图
        Image srcImage= ImageIO.read(new File(string));
       
        //将原始位图缩小后绘制到bufferedImage对象中 
        graphics.drawImage(srcImage,0,0,x,y,null);
        //将bufferedImage对象输出到磁盘上
        String[] strings=ImagesName.ImagesFormat(string);
		String url=strings[0]+"\\"+String.valueOf(0)+"."+strings[1];
		
        ImageIO.write(bufferedImage,"jpg",new File(url));
        return url;
	}
}
