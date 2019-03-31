package Checked;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;

import imagesMethods.ImagesSaving;
 
public class _07_Binary {
	public static String Binary(String string) throws IOException {
//		String string="D:\\Config\\57421470783112381.jpg";
		BufferedImage image = ImageIO.read(new File(string));
		int w = image.getWidth();
		int h = image.getHeight();
		float[] rgb = new float[3];
		double[][] zuobiao = new double[w][h];
		int R = 0;
		float red = 0;
		float green = 0;
		float blue = 0;
		BufferedImage bi= new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_BINARY);;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int pixel = image.getRGB(x, y); 

				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				red += rgb[0];
				green += rgb[1];
				blue += rgb[2];
				R = (x+1) *(y+1);
				float avg = (rgb[0]+rgb[1]+rgb[2])/3;
				zuobiao[x][y] = avg;	
				
			}
		}
		double SW = 100;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (zuobiao[x][y] <= SW) {
					int max = new Color(0, 0, 0).getRGB();
					bi.setRGB(x, y, max);
				}else{
					int min = new Color(255, 255, 255).getRGB();
					bi.setRGB(x, y, min);
				}
			}			
		}
		
		// ±£´æÍ¼Æ¬
		String url=ImagesSaving.ImagesNameToSaving(string, 7);
		ImageIO.write(bi, "jpg", new File(url));
		return url;
	}	
}
