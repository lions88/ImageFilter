package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Color;

public class BlockFilter {
	// 版画效果函数
	public static Bitmap changeToBlock(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int dst[] = new int[width * height];
		bitmap.getPixels(dst, 0, width, 0, 0, width, height);

		int iPixel = 0;
		int i, j, color, pos;
		for (j = 0; j < height; j++) {
			for (i = 0; i < width; i++) {
				pos = j * width + i;
				color = dst[pos];
				int avg = (Color.red(color) + Color.green(color) + Color.blue(color)) / 3;
				if (avg >= 100)
					iPixel = 255;
				else
					iPixel = 0;

				dst[pos] = Color.rgb(iPixel, iPixel, iPixel);
			}
		}

		Bitmap bmpReturn = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		bmpReturn.setPixels(dst, 0, width, 0, 0, width, height);
		return bmpReturn;
	}
}
