package com.konka.imagefilter.api;

import java.util.Random;

import android.graphics.Bitmap;

public class OilFilter {
	// 油画效果
	public static Bitmap changeToOil(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int dst[] = new int[width * height];
		bitmap.getPixels(dst, 0, width, 0, 0, width, height);

		int color = 0;
		Random random = new Random();
		int iModel = 4;
		int i = width - iModel;
		int pos = 0, iPos;

		while (i > 1) {
			int j = height - iModel;
			while (j > 1) {
				iPos = random.nextInt(100000000) % iModel;
				pos = (j + iPos) * width + (i + iPos);
				color = dst[pos];
				pos = j * width + i;
				dst[pos] = color;
				j--;
			}
			i--;
		}

		Bitmap returnBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		returnBitmap.setPixels(dst, 0, width, 0, 0, width, height);
		return returnBitmap;
	}
}
