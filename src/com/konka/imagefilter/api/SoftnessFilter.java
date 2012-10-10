package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;

public class SoftnessFilter {
	//柔化效果
	public static Bitmap changeToSoftness(Bitmap bitmap){
		int width = bitmap.getWidth();
    	int height = bitmap.getHeight();
    	int dst[] = new int[width*height];
    	bitmap.getPixels(dst, 0, width, 0, 0, width, height);
    	
    	int R, G, B, pixel;
    	int pos, pixColor;
    	for(int y=0; y<height; y++){
    		for(int x=0; x<width; x++){
    			pos = y*width + x;
    			pixColor = dst[pos];
    			R = Color.red(pixColor);		//(color >> 16) & 0xFF
    			G = Color.green(pixColor);		//(color >> 8) & 0xFF;
    			B = Color.blue(pixColor);		//color & 0xFF
    			 pixel = 255 - (255-R)*(255-R)/255;
                 if (pixel < 0)
                         pixel = -pixel;
                 pixel = pixel * R / 256;
                 if (pixel > 255)
                         pixel = 255;
                 R = pixel;
                 
                 pixel = 255 - (255-G)*(255-G)/255;
                 if (pixel < 0)
                         pixel = -pixel;
                 pixel = pixel * R / 256;
                 if (pixel > 255)
                         pixel = 255;
                 G = pixel;
                 
                 pixel = 255 - (255-B)*(255-B)/255;
                 if (pixel < 0)
                         pixel = -pixel;
                 pixel = pixel * G / 256;
                 if (pixel > 255)
                         pixel = 255;
                 B = pixel;
                
                dst[pos] = Color.rgb(R, G, B);
    		}
    	}
    	Bitmap processBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	processBitmap.setPixels(dst, 0, width, 0, 0, width, height);
    	
    	return processBitmap;
	}
}
