package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

public class LomoFilter {
	
	public static Bitmap changeToLomo(Bitmap bitmap) {
		
		int width = bitmap.getWidth();  
	        int height = bitmap.getHeight();  
	        int dst[] = new int[width*height];  
	        bitmap.getPixels(dst, 0, width, 0, 0, width, height);  
	          
	        int ratio = width > height ? height*32768/width : width*32768/height;  
	        int cx = width >> 1;  
	        int cy = height >> 1;  
	        int max = cx * cx + cy * cy;  
	        int min = (int) (max * (1 - 0.8f));  
	        int diff = max - min;  
          
	        int ri, gi, bi;  
	        int dx, dy, distSq, v;  
	          
	        int R, G, B;  
	          
	        int value;  
	        int pos, pixColor;  
	        int newR, newG, newB;  
	        for(int y=0; y<height; y++){  
	            for(int x=0; x<width; x++){  
	                pos = y*width + x;  
	                pixColor = dst[pos];  
	                R = Color.red(pixColor);          
	                G = Color.green(pixColor);        
	                B = Color.blue(pixColor);  
	                  
	                value = R<128 ? R : 256-R;  
	                newR = (value*value*value)/64/256;  
	                newR = (R<128 ? newR : 255-newR);  
	                  
	                value = G<128 ? G : 256-G;  
	                newG = (value*value)/128;  
	                newG = (G<128 ? newG : 255-newG);  
	                  
	                newB = B/2 + 0x25;    
	                  
	                //==========边缘黑暗==============//  
	                dx = cx - x;  
	                dy = cy - y;  
	                if (width > height)   
	                    dx = (dx * ratio) >> 15;  
	                else   
	                    dy = (dy * ratio) >> 15;  
	                  
	                distSq = dx * dx + dy * dy;  
	                if (distSq > min){  
	                    v = ((max - distSq) << 8) / diff;  
	                    v *= v;  
	  
	                    ri = (int)(newR * v) >> 16;  
	                    gi = (int)(newG * v) >> 16;  
	                    bi = (int)(newB * v) >> 16;  
	  
	                    newR = ri > 255 ? 255 : (ri < 0 ? 0 : ri);  
	                    newG = gi > 255 ? 255 : (gi < 0 ? 0 : gi);  
	                    newB = bi > 255 ? 255 : (bi < 0 ? 0 : bi);  
	                }  
	                //==========边缘黑暗end==============//  
	                  
	                dst[pos] = Color.rgb(newR, newG, newB);  
	            }  
	        }  
	  
	        Bitmap acrossFlushBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);  
	        acrossFlushBitmap.setPixels(dst, 0, width, 0, 0, width, height);  
	        return acrossFlushBitmap;  
	}
	
}
