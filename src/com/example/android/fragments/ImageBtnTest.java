package com.example.android.fragments;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ImageBtnTest {

	private LinearLayout linear;
	private ImgBtnOnClick btnListener;
	private RelativeLayout relative;
	private ArticleFragment article;
	private Context context;
	private HeadlinesFragment headline;
	
	public ImageBtnTest(Context context, RelativeLayout relative, ArticleFragment article, 
			HeadlinesFragment headline, LinearLayout linear){
		this.relative = relative;
		this.context = context;
		this.article = article;
		this.headline = headline;
		this.linear = linear;
	}
	
	public void addBtn(List<ImageButton> imgBtnList){
		btnListener = new ImgBtnOnClick();
		for(int i = 0; i < imgBtnList.size(); i++){
			imgBtnList.get(i).setOnClickListener(btnListener);
			linear.addView(imgBtnList.get(i));
		}
	}
	
}
