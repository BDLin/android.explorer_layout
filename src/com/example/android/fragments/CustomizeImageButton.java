package com.example.android.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CustomizeImageButton{
	private Context context;
	private int img_id;
	private ImageButton imgBtn;
	private static int count = 0;
	
	@SuppressLint("NewApi")
	public CustomizeImageButton(Context context, int img_id){
		this.context = context;
		this.img_id = img_id;
		imgBtn = new ImageButton(context);
		imgBtn.setImageResource(img_id);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
		params.weight = 1;
		imgBtn.setLayoutParams(params);
		imgBtn.setId(count++);
		if(imgBtn.getId() != 0)
			imgBtn.setAlpha((float)0.5);
		imgBtn.setOnClickListener(new ImgBtnOnClick());
	}
	
	public ImageButton getButtont(){
		return imgBtn;
	}
	
}
