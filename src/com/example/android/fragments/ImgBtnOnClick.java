package com.example.android.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class ImgBtnOnClick implements OnClickListener {

	private int temp;
	
	public ImgBtnOnClick(){
		temp = 0;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		Log.i("ImgBtnOnClick", "Click");
		/*if(temp != v.getId()){
			list.get(temp).setAlpha((float)0.5);
			list.get(v.getId()).setAlpha((float)1.0);
			temp = v.getId();
		}//End if condition*/

	}
}
