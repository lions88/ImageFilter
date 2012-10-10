package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;

public class HahaFilter {
	//哈哈镜效果
	public static Bitmap changeToHaha(Bitmap bitmap){
		int centerX = bitmap.getWidth() / 2;
		int centerY = bitmap.getHeight() / 2;
		float radius = Math.min(centerX*2/3, centerY*2/3);
		float mutiple = 2.0f;
		return changeToHaha(bitmap, radius, centerX, centerY, mutiple);
	}
	
	/**
	 * @param bitmap	原图bitmap
	 * @param radius	半径
	 * @param centerX 	圆心x坐标
	 * @param centerY	圆心y坐标
	 * @param mutiple	系数
	 * @return
	 */
	public static Bitmap changeToHaha(Bitmap bitmap, float radius, int centerX, int centerY, float mutiple){
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		int[] src = new int[width*height];
		int[] dst = new int[width*height];
		bitmap.getPixels(src, 0, width, 0, 0, width, height);
		
		int x, y, pos, color;
		int R, G, B;
		int distance;
		int src_x, src_y, src_color;
		int real_radius = (int)(radius / mutiple);
		
		for(y=0; y<height; y++){
			for(x=0; x<width; x++){
				pos = y*width + x;
				color = src[pos];
				
				R = Color.red(color);
				G = Color.green(color);
				B = Color.blue(color);
				
				distance = (centerX-x)*(centerX-x) + (centerY-y)*(centerY-y);
				if (distance < radius * radius){
					//放大镜效果
					//src_x = (int)((float)(x-centerX)/xishu + centerX);
					//src_y = (int)((float)(y-centerY)/xishu + centerY);
					//哈哈镜效果
					src_x = (int)((float)(x-centerX) / mutiple );
					src_y = (int)((float)(y-centerY) / mutiple );
					src_x = (int)(src_x * (Math.sqrt(distance) / real_radius));
					src_y = (int)(src_y * (Math.sqrt(distance) / real_radius));
					src_x += centerX;
					src_y += centerY;
					
					src_color = src[src_y*width+src_x];
					R = Color.red(src_color);
					G = Color.green(src_color);
					B = Color.blue(src_color);
					
					R = Math.min(255, Math.max(0, R));
					G = Math.min(255, Math.max(0, G));
					B = Math.min(255, Math.max(0, B));
					
					dst[pos] = Color.rgb(R, G, B);
				}
				else{
					dst[pos] = src[pos];
				}
				
			}
		}
		
		Bitmap returnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		returnBitmap.setPixels(dst, 0, width, 0, 0, width, height);
		return returnBitmap;
	}
}
