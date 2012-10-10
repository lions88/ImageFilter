package com.konka.imagefilter.api;

import android.graphics.Bitmap;

public class InvertFilter {
	// 反色效果函数
	public static Bitmap chageToInvert(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		int colorArray[] = new int[width * height];
		int r, g, b, index;
		bitmap.getPixels(colorArray, 0, width, 0, 0, width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				index = y * width + x;
				r = (colorArray[index] >> 16) & 0xff;
				g = (colorArray[index] >> 8) & 0xff;
				b = colorArray[index] & 0xff;
				colorArray[index] = 0xff000000 | (r << 16) | (g << 8) | b;
				
				r = (255-(int)(colorArray[index]& 0x00FF0000) >>> 16);
				g = (255-(int)(colorArray[index]& 0x0000FF00) >>> 8);
				b = (255-(int)(colorArray[index] & 0x000000FF));
				
				colorArray[index] = (255 << 24) + (r << 16) + (g << 8) + b;
			}
		}
		Bitmap returnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		returnBitmap.setPixels(colorArray, 0, width, 0, 0, width, height);
		
		return returnBitmap;
	}
}
