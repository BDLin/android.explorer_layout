package com.example.android.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListOnClick implements OnItemClickListener {

	private ArticleFragment article;
	private Context context;
	private List<Viewable> array;
	
	public ListOnClick(ArticleFragment article, Context context, List<Viewable> array){
		this.article = article;
		this.context = context;
		this.array = array;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		
        	article.updateArticleView(array.get(position).getView(context));
   	}

	
	

}
