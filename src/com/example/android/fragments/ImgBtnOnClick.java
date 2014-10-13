package com.example.android.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

@SuppressLint("NewApi")
public class ImgBtnOnClick implements OnClickListener {

	private int temp;
	private List<ImageButton> list;
	
	public ImgBtnOnClick(List<ImageButton> list){
		this.list = list;
		temp = 0;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		Log.i("ImgBtnOnClick", "Click: " + ((ImageButton)v).getId());
		if(temp != v.getId()){
			list.get(temp).setAlpha((float)0.5);
			list.get(v.getId()).setAlpha((float)1.0);
			temp = v.getId();
		}
			
	}

}
