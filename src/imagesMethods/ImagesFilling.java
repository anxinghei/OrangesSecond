package imagesMethods;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class ImagesFilling {
	
	public static BufferedImage Mat2BufImg (Mat matrix, String fileExtension) {

        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, mob);

        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }
	
	public static Mat bfimg2mat(BufferedImage image) {
		DataBufferByte dbi =(DataBufferByte) image.getRaster().getDataBuffer();
        byte[] pixels = dbi.getData();

	    Mat ima = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
	    ima.put(0, 0, pixels);

	    return ima;
	}
	
	public static BufferedImage imfill(BufferedImage src) throws IOException {
			 
//		 BufferedImage src=ImageIO.read(new File(string));
		 // 创建缓冲区
		 BufferedImage imfillImage = new BufferedImage(src.getWidth(),src.getHeight(),src.getType());
		 // 像素矩阵初始化
		 int[][] imageColor = new int[src.getWidth()][src.getHeight()];		 
		 for (int i = 0; i < src.getWidth(); i++) {
             for (int j = 0; j < src.getHeight(); j++) 
                 imageColor[i][j] = src.getRGB(i , j);
         }
		 // 像素矩阵重填充,并画到缓冲区
		 imageColor = imfillImage(30,imageColor);
		 imageColor = imfillImage(20,imageColor);
		 imageColor = imfillImage(10,imageColor);
		 for (int i = 0; i < src.getWidth(); i++) {
             for (int j = 0; j < src.getHeight(); j++) {
                 imfillImage.setRGB(i, j, imageColor[i][j]);         
             }
         }
		 
//		 File output = new File(string);
//		 ImageIO.write(imfillImage, "jpg", output);
		 
		 return imfillImage;
	}
	
	private static int[][] imfillImage(int distance, int[][] imageColor) {

        int[][] newImageColor;
        newImageColor = imageColor;
        int counter;
        for (int i = distance; i < imageColor.length - distance; i++) {
            for (int j = distance; j < imageColor[1].length - distance; j++) {
                counter = 0;            
                //上
                for (int k1 = 0; k1 < distance; k1++) {
                    if (imageColor[i-k1][j]  == 0xffffffff) 
                    {
                        counter++;
                        break;
                    }
                }

                //左
                for (int k1 = 0; k1 < distance; k1++) {
                    if (imageColor[i][j-k1]  == 0xffffffff) 
                    {
                        counter++;
                        break;
                    }
                }   
                //下
                for (int k1 = 0; k1 < distance; k1++) {
                    if (imageColor[i+k1][j]  == 0xffffffff) 
                    {
                        counter++;
                        break;
                    }
                }   
                //右
                for (int k1 = 0; k1 < distance; k1++) {
                    if (imageColor[i][j+k1]  == 0xffffffff) 
                    {
                        counter++;
                        break;
                    }
                }
                // 如果四个方向都为白色，返回此处为白色
                if (counter == 4)  
                	newImageColor[i][j] = 0xffffffff;
            }//for (int j = 0; j < imageColor[1].length; j++)
        }//for (int i = 0; i < imageColor.length; i++)  
        return newImageColor;
	}
}
