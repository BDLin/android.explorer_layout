package com.example.android.fragments;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CustomizeImageButton {
	private Context context;
	private int img_id;
	private ImageButton imgBtn;
	
	public CustomizeImageButton(Context context, int img_id){
		this.context = context;
		this.img_id = img_id;
		imgBtn = new ImageButton(context);
		imgBtn.setImageResource(img_id);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
		params.weight = 1;
		imgBtn.setLayoutParams(params);
	}
	
	public ImageButton getButton(){
		return imgBtn;
	}
	
}
