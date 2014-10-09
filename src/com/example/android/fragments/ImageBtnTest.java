package com.example.android.fragments;

import java.util.List;

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
	
	public void addBtn(List<ImageButton> imgBtnList){

		for(int i = 0; i < imgBtnList.size(); i++)
			linear.addView(imgBtnList.get(i));
	}
	
	public View getView(){
		return linear;
	}
	
}
