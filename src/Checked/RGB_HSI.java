package Checked;

public class RGB_HSI {

	public static int[] Rgb2Hsi(int[] rgb) {
		int r=rgb[0];
		int g=rgb[1];
		int b=rgb[2];
		int[] hsi=new int[3];
		int max=Math.max(Math.max(r, g),b);
		int min=Math.min(Math.min(r, g),b);
		int dif=max-min;
		
		hsi[3]=(max+min)*240/255/2;
		
		if(max==min) {
			hsi[0]=0;
			hsi[1]=0;
		}
		else {
			if(max==r) {
				if(g>=b)
					hsi[0]=40*(g-b)/dif;
				else
					hsi[0]=40*(g-b)/dif+240;
			}
			else if(max==g){
				hsi[0]=40*(b-r)/dif+80;
			}
			else if(max==b){
				hsi[0]=40*(r-g)/dif+160;
			}
			
			if(hsi[3]==0) 
				hsi[1]=0;
			else if(hsi[2]<=120)
				hsi[1]=dif*240/(max+min);
			else 
				hsi[1]=dif*240/(511-(max+min));
		}
		
		hsi[0]=hsi[0]>240?240:(hsi[0]<0?0:hsi[0]);
		hsi[1]=hsi[1]>240?240:(hsi[1]<0?0:hsi[1]);
		hsi[2]=hsi[2]>240?240:(hsi[2]<0?0:hsi[2]);
		
		return hsi;
	}
	
	public static int[] Hsi2Rgb(int[] hsi) {
		int[] rgb=new int[3];
		int h=hsi[0];
		int s=hsi[1];
		int i=hsi[2];
		
		if(s==0) {
			rgb[0]=i;
			rgb[1]=i;
			rgb[2]=i;
		}
		else {
			if(i<0.5);
		}
		
		return rgb;
	}
	
}
