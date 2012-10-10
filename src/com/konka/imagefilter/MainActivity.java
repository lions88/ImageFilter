package com.konka.imagefilter;

import com.konka.imagefilter.api.BitmapFilter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{

	private ImageView iv;
	private Bitmap originalBitmap;
	private Bitmap sBitmap;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.original_btn).setOnClickListener(this);
        findViewById(R.id.compose_btn).setOnClickListener(this);
        
        findViewById(R.id.button_change_to_gray).setOnClickListener(this);
        findViewById(R.id.button_change_to_block).setOnClickListener(this);
        findViewById(R.id.button_change_to_old).setOnClickListener(this);
        findViewById(R.id.button_change_to_ice).setOnClickListener(this);
        findViewById(R.id.button_change_to_carton).setOnClickListener(this);
        findViewById(R.id.button_change_to_molten).setOnClickListener(this);
        findViewById(R.id.button_change_to_soft).setOnClickListener(this);
        findViewById(R.id.button_change_to_eclosion).setOnClickListener(this);
        findViewById(R.id.button_change_to_relief).setOnClickListener(this);
        findViewById(R.id.button_change_to_oid).setOnClickListener(this);
        findViewById(R.id.button_change_to_invert).setOnClickListener(this);
        findViewById(R.id.button_change_to_light).setOnClickListener(this);
        findViewById(R.id.button_change_to_haha).setOnClickListener(this);
        
        originalBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.huishu);
        iv = (ImageView)findViewById(R.id.iv);
        iv.setImageBitmap(originalBitmap);
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	if(sBitmap != null){
    		sBitmap.recycle();
    		sBitmap = null;
    	}
    }
    
    @Override
	public void onClick(View v) {
    	if(sBitmap != null){
    		sBitmap.recycle();
    		sBitmap = null;
    	}
    	
    	int styleNo = BitmapFilter.GRAY_STYLE;
    	switch(v.getId()){
    	case R.id.original_btn:
    		iv.setImageBitmap(originalBitmap);
    		return;
    	case R.id.compose_btn:
    		Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.huishu3);
    		sBitmap = Componse.getComponseBitmap(originalBitmap, b2);
    		if(sBitmap != null)
    			iv.setImageBitmap(sBitmap);
    		return;
    	case R.id.button_change_to_gray:
    		styleNo = BitmapFilter.GRAY_STYLE;
    		break;
    	case R.id.button_change_to_block:
    		//版画效果
    		styleNo = BitmapFilter.BLOCK_STYLE;
    		break;
    	case R.id.button_change_to_old:
    		styleNo = BitmapFilter.OLD_STYLE;
    		break;
    	case R.id.button_change_to_ice:
    		styleNo = BitmapFilter.ICE_STYLE;
    		break;
    	case R.id.button_change_to_carton:
    		//连环画效果
    		styleNo = BitmapFilter.CARTON_STYLE;
    		break;
    	case R.id.button_change_to_molten:
    		//铸融效果
    		styleNo = BitmapFilter.MOLTEN_STYLE;
    		break;
    	case R.id.button_change_to_soft:
    		//羽化效果
    		styleNo = BitmapFilter.SOFTNESS_STYLE;
    		break;
    	case R.id.button_change_to_eclosion:
    		styleNo = BitmapFilter.ECLOSION_STYLE;
    		break;
    	case R.id.button_change_to_relief:
    		//浮雕
    		styleNo = BitmapFilter.RELIEF_STYLE;
    		break;
    	case R.id.button_change_to_oid:
    		//油画效果
    		styleNo = BitmapFilter.OIL_STYLE;
    		break;
    	case R.id.button_change_to_invert:
    		//反色效果
    		styleNo = BitmapFilter.INVERT_STYLE;
    		break;
    	case R.id.button_change_to_light:
    		styleNo = BitmapFilter.LIGHT_STYLE;
    		break;
    	case R.id.button_change_to_haha:
    		//哈哈镜效果
    		styleNo = BitmapFilter.HAHA_STYLE;
    		break;
		default:
			return;
    	}
    	sBitmap = BitmapFilter.changeStyle(originalBitmap, styleNo);
    	iv.setImageBitmap(sBitmap);
	}

}
