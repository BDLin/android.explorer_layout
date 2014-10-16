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
	private List<ImageButton> list;
	private RelativeLayout relative;
	private ArticleFragment article;
	private Context context;
	private HeadlinesFragment headline;
	private FragmentTransaction fragmentTransaction;
	
	public ImgBtnOnClick(List<ImageButton> list, RelativeLayout relative, 
			             ArticleFragment article, Context context, HeadlinesFragment headline){
		this.list = list;
		Log.i("ImgBtnOnClick", "list_size:" + list.size());
		temp = 0;
		this.relative = relative;
		this.article = article;
		this.context = context;
		this.headline = headline;
		fragmentTransaction = this.headline.getActivity().getSupportFragmentManager().beginTransaction();
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
		Log.i("onClick", "" + relative.getChildAt(0));
		switch(v.getId()){
		
			case 0:
				Fragment remote = new RemoteListFragment();
				headline.getActivity().getSupportFragmentManager().beginTransaction()
			       .replace(R.id.frag_container, remote).commit();
				break;
				
			case 1:
				LocalListFragment local = new LocalListFragment();
				headline.getActivity().getSupportFragmentManager().beginTransaction()
				       .replace(R.id.frag_container, local).commit();
				Log.i("onClick", "" + relative.getChildAt(0));
				break;
				
			case 2:
				Log.i("ImgBtnOnClick", "relative chid count:" + relative.getChildCount());
				Fragment presFrag =new PrefsFragment();
				headline.getActivity().getSupportFragmentManager().beginTransaction()
			       .replace(R.id.frag_container, presFrag).commit();
				break;
				
			default:
				break;
		}//End if switch-case
	}
}
