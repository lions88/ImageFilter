package com.konka.imagefilter.api;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ReliefFilter {
	// 浮雕效果函数
	public static Bitmap changeToRelief(Bitmap mBitmap) {
		int width = mBitmap.getWidth();
		int heigth = mBitmap.getHeight();

		int preColor = 0;
		int prepreColor = 0;
		preColor = mBitmap.getPixel(0, 0);

		int dst[] = new int[heigth * width];
		mBitmap.getPixels(dst, 0, width, 0, 0, width, heigth);
		int pos, curr_color, R, G, B;
		for (int y = 0; y < heigth; y++) {
			for (int x = 0; x < width; x++) {
				pos = y * width + x;
				curr_color = dst[pos];
				R = Color.red(curr_color) - Color.red(prepreColor) + 128;
				G = Color.green(curr_color) - Color.red(prepreColor) + 128;
				B = Color.blue(curr_color) - Color.blue(prepreColor) + 128;

				dst[pos] = Color.rgb(R, G, B);
				prepreColor = preColor;
				preColor = curr_color;
			}
		}
		Bitmap bmpReturn = Bitmap.createBitmap(width, heigth, Bitmap.Config.RGB_565);
		bmpReturn.setPixels(dst, 0, width, 0, 0, width, heigth);

		Canvas c = new Canvas(bmpReturn);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpReturn, 0, 0, paint);

		return bmpReturn;

	}
}
