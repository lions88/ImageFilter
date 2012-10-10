package com.konka.imagefilter.api;

import android.graphics.Bitmap;

/**
 * 滤镜效果的类，定义了基本的渲染方法
 *
 */
public class BitmapFilter {
	/**
	 * 所有滤镜效果的id
	 */
	public static final int GRAY_STYLE = 1; 	// 黑白效果
	public static final int BLOCK_STYLE = 2; 	// 版画
	public static final int OLD_STYLE = 3; 		// 怀旧效果
	public static final int ICE_STYLE = 4;		//冰冻效果
	public static final int CARTON_STYLE = 5;	//连环画效果
	public static final int MOLTEN_STYLE = 6;	//铸融效果
	public static final int SOFTNESS_STYLE = 7; //柔化效果
	public static final int ECLOSION_STYLE = 8; //羽化效果
	public static final int RELIEF_STYLE = 9; 	// 浮雕效果
	public static final int OIL_STYLE = 10; 	// 油画效果
	public static final int INVERT_STYLE = 11; 	// 反色效果
	public static final int LIGHT_STYLE = 12; 	// 光照效果
	public static final int LOMO_STYLE = 13;	//LOMO效果
	public static final int HAHA_STYLE = 14;	//哈哈镜效果
	
	/**
	 * 设置滤镜效果，
	 * @param bitmap
	 * @param styeNo, 效果id
	 */
	public static Bitmap changeStyle(Bitmap bitmap, int styleNo) {
		Bitmap newBitmap = null;
		switch(styleNo){
		case GRAY_STYLE:
			newBitmap = GrayFilter.changeToGray(bitmap);
			break;
		case BLOCK_STYLE:
			newBitmap =  BlockFilter.changeToBlock(bitmap);
			break;
		case OLD_STYLE:
			newBitmap =  OldFilter.changeToOld(bitmap);
			break;
		case ICE_STYLE:
			newBitmap =  IceFilter.changeToIce(bitmap);
			break;
		case CARTON_STYLE:
			newBitmap =  CartonFilter.changeToCarton(bitmap);
			break;
		case MOLTEN_STYLE:
			newBitmap = MoltenFilter.changeToMolten(bitmap);
			break;
		case SOFTNESS_STYLE:
			newBitmap = SoftnessFilter.changeToSoftness(bitmap);
			break;
		case ECLOSION_STYLE:
			newBitmap = EclosionFilter.changeToEclosion(bitmap);
			break;
		case RELIEF_STYLE:
			newBitmap =  ReliefFilter.changeToRelief(bitmap);
			break;
		case OIL_STYLE:
			newBitmap =  OilFilter.changeToOil(bitmap);
			break;
		case INVERT_STYLE:
			newBitmap =  InvertFilter.chageToInvert(bitmap);
			break;
		case LIGHT_STYLE:
			newBitmap =  LightFilter.changeToLight(bitmap);
			break;
		case LOMO_STYLE:
			newBitmap =  LomoFilter.changeToLomo(bitmap);
			break;
		case HAHA_STYLE:
			newBitmap =  HahaFilter.changeToHaha(bitmap);
			break;
		default:
			newBitmap = bitmap;
			break;
		}
		
		return newBitmap;
	}
}

















