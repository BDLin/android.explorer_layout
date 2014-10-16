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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class ImgBtnOnClick implements OnClickListener {

	private HeadlinesFragment headline;
	private LinearLayout linear;
	private Fragment frag;
	
	public ImgBtnOnClick(HeadlinesFragment headline, LinearLayout linear, Fragment frag){
		this.headline = headline;
		this.linear = linear;
		this.frag = frag;
	}
	
	public void onClick(View v) {
		if(v.getAlpha() != 1.0){
			headline.getActivity().getSupportFragmentManager().beginTransaction()
		       .replace(R.id.frag_container, frag).commit();
			for(int i = 0; i < linear.getChildCount(); i++)
				linear.getChildAt(i).setAlpha((float) 0.5);
			v.setAlpha((float) 1.0);
		}
	}
}
