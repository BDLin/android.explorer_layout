package com.example.android.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ImageBtnTest {

	private LinearLayout linear;
	
	public ImageBtnTest(Context context){
		linear = new LinearLayout(context);
		linear.setOrientation(LinearLayout.HORIZONTAL);
	}
	
	public void addBtn(ImageButton btn){
		linear.addView(btn);
	}
	
	public View getView(){
		return linear;
	}
}
