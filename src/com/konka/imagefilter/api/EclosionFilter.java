package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;

public class EclosionFilter {
	// 羽化效果
	public static Bitmap changeToEclosion(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int dst[] = new int[width * height];
		bitmap.getPixels(dst, 0, width, 0, 0, width, height);

		int ratio = width > height ? height*32768/width : width*32768/height;

		int cx = width >> 1;
		int cy = height >> 1;
		int max = cx * cx + cy * cy;
		int min = (int) (max * (1 - 0.5f));
		int diff = max - min;

		int R, G, B;
		int pos, pixColor;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pos = y * width + x;
				pixColor = dst[pos];
				R = Color.red(pixColor); 
				G = Color.green(pixColor); 
				B = Color.blue(pixColor); 

				int dx = cx - x;
				int dy = cy - y;
				if (width > height) {
					dx = (dx * ratio) >> 15;
				} else {
					dy = (dy * ratio) >> 15;
				}

				int distSq = dx * dx + dy * dy;
				float v = ((float) distSq / diff) * 255;
				R = (int) (R + (v));
				G = (int) (G + (v));
				B = (int) (B + (v));
				R = (R > 255 ? 255 : (R < 0 ? 0 : R));
				G = (G > 255 ? 255 : (G < 0 ? 0 : G));
				B = (B > 255 ? 255 : (B < 0 ? 0 : B));

				dst[pos] = Color.rgb(R, G, B);
			}
		}
		Bitmap processBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		processBitmap.setPixels(dst, 0, width, 0, 0, width, height);
		return processBitmap;
	}
}
