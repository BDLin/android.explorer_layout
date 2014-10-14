package com.example.android.fragments;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
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
	
	public ImageBtnTest(Context context, RelativeLayout relative, ArticleFragment article, HeadlinesFragment headline){
		linear = new LinearLayout(context);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		this.relative = relative;
		this.context = context;
		this.article = article;
		this.headline = headline;
	}
	
	public void addBtn(List<ImageButton> imgBtnList){

		btnListener = new ImgBtnOnClick(imgBtnList, relative, article, context, headline);
		for(int i = 0; i < imgBtnList.size(); i++){
			imgBtnList.get(i).setOnClickListener(btnListener);
			linear.addView(imgBtnList.get(i));
		}
	}
	
	public View getView(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linear.setLayoutParams(params);
		return linear;
	}
	
}
