/* Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.fragments;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListOnClick implements OnItemClickListener {

	private ArticleFragment article;
	private Context context;
	private List<Viewable> array;
	private View prevView;
	
	public ListOnClick(ArticleFragment article, Context context, List<Viewable> array){
		this.article = article;
		this.context = context;
		this.array = array;
	}//End of ListOnClick construct
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		
        	article.updateArticleView(array.get(position).getView(context));
        	
        	if(prevView != null && prevView != view){
        		prevView.setBackgroundColor(0);
        		view.setBackgroundColor(Color.DKGRAY);
        		prevView = view;
        	}else if(prevView == null){
        		view.setBackgroundColor(Color.DKGRAY);
        		prevView = view;
        	}//End of if else-is condition
   	}//End of onItemClick Function
}//End of ListOnClick Class
