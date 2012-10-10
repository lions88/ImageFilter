package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class IceFilter {
	//冰冻效果
	public static Bitmap changeToIce(Bitmap bitmap){
		int width = bitmap.getWidth();
    	int height = bitmap.getHeight();
    	int dst[] = new int[width*height];
    	bitmap.getPixels(dst, 0, width, 0, 0, width, height);
    	Log.i("IceStyle", "width="+width + "; height="+height);
    	int R, G, B, pixel;
    	int pos, pixColor;
    	for(int y=0; y<height; y++){
    		for(int x=0; x<width; x++){
    			pos = y*width + x;
    			pixColor = dst[pos];
    			R = Color.red(pixColor);		
    			G = Color.green(pixColor);		
    			B = Color.blue(pixColor);		
    			
    			pixel = R-G-B;
    			pixel = pixel*3/2;
    			if(pixel < 0)
    				pixel = -pixel;
    			if(pixel > 255)
    				pixel = 255;
    			R = pixel;	
    			
    			pixel = G - B - R;
                pixel = pixel * 3 / 2;
                if (pixel < 0)
                        pixel = -pixel;
                if (pixel > 255)
                        pixel = 255;
                G = pixel;

                pixel = B - R - G;
                pixel = pixel * 3 / 2;
                if (pixel < 0)
                        pixel = -pixel;
                if (pixel > 255)
                        pixel = 255;
                B = pixel;
                
                dst[pos] = Color.rgb(R, G, B);
    		}
    	}
    	Bitmap iceBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    	iceBitmap.setPixels(dst, 0, width, 0, 0, width, height);
    	return iceBitmap;
	}
}
