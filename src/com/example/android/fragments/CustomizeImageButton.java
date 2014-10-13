package com.example.android.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CustomizeImageButton{
	private Context context;
	private int img_id;
	private ImageButton imgBtn;
	
	@SuppressLint("NewApi")
	public CustomizeImageButton(Context context, int img_id, int btn_id){
		this.context = context;
		this.img_id = img_id;
		imgBtn = new ImageButton(context);
		imgBtn.setImageResource(img_id);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
		params.weight = 1;
		imgBtn.setLayoutParams(params);
		imgBtn.setId(btn_id);
		Log.i("CustomerizeImgBtn", "count:" + btn_id);
		if(imgBtn.getId() != 0)
			imgBtn.setAlpha((float)0.5);
	}
	
	public ImageButton getButtont(){
		return imgBtn;
	}
	
}
