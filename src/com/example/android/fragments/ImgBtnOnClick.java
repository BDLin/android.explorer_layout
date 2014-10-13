package com.example.android.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class ImgBtnOnClick implements OnClickListener {

	private int temp;
	private List<ImageButton> list;
	private RelativeLayout relative;
	private ArticleFragment article;
	private Context context;
	
	public ImgBtnOnClick(List<ImageButton> list, RelativeLayout relative, 
			             ArticleFragment article, Context context){
		this.list = list;
		Log.i("ImgBtnOnClick", "list_size:" + list.size());
		temp = 0;
		this.relative = relative;
		this.article = article;
		this.context = context;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		Log.i("ImgBtnOnClick", "Click: " + ((ImageButton)v).getId());
		if(temp != v.getId()){
			list.get(temp).setAlpha((float)0.5);
			list.get(v.getId()).setAlpha((float)1.0);
			temp = v.getId();
		}//End if condition
		
		switch(v.getId()){
			case 0:
				relative.removeViewAt(0);
				AnyObjectList list = new AnyObjectList(context);
				list.setItemListener(article);
				relative.addView(list.getView(), 0);
				break;
			case 1:
				relative.removeViewAt(0);
				relative.addView(new ListData("Button click list").getView(relative.getContext()), 0);
				Log.i("ImgBtnOnClick", "relative chid count:" + relative.getChildCount());
				break;
			case 2:
				//relative.removeViewAt(0);
				break;
			default:
				break;
		}//End if switch-case
			
	}

}
