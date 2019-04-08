package Checked;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		String string = "D:\\Config\\34567\\0.jpg";
		string = _01_ColorFilter.Shaixuan(string);
		string = _02_MedianBlur.Blur(string);
		string = _03_CannyEdges.Edges(string);
		string = _04_Expanding.Qingshi(string);
		string = _05_KaiYunSuan.Kai(string);
		int a = _06_minRect.minRect(string);
		System.out.println(a);
	}
}
