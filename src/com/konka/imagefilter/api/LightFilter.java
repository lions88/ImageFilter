package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;

public class LightFilter {

	// 光照效果函数
	public static Bitmap changeToLight(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int pixColor = 0;
		int R = 0;
		int G = 0;
		int B = 0;

		int centerX = width / 3;
		int centerY = height / 3;
		int radius = Math.min(centerX, centerY);

		int strength = 150; // 光照强度 100~150
		int[] pixels = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		
		int pos = 0, distance, result;
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				pos = y * width + x;
				pixColor = pixels[pos];

				R = Color.red(pixColor);
				G = Color.green(pixColor);
				B = Color.blue(pixColor);

				// 计算当前点到光照中心的距离，平面座标系中求两点之间的距离
				distance = (centerY - y)*(centerY - y) + (centerX - x)*(centerX-x);
				if (distance < radius * radius) {
					// 按照距离大小计算增加的光照值
					result = (int) (strength * (1.0 - Math.sqrt(distance)/radius));
					R += result;
					G += result;
					B += result;
					
					R = Math.min(255, Math.max(0, R));
					G = Math.min(255, Math.max(0, G));
					B = Math.min(255, Math.max(0, B));

					pixels[pos] = Color.rgb(R, G, B);
				}
			}
		}

		Bitmap returnBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		returnBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return returnBitmap;
	}
}
