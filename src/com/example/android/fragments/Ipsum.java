/*
 * Copyright (C) 2012 The Android Open Source Project
 * Copyright (C) 20114 Zi-Xiang Lin <bdl9437@gmail.com>
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

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Ipsum implements Viewable{

    private String title;
	private String articles;
	
	public Ipsum(String title, String article){
		this.setTitle(title);
		this.setArticles(article);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	@Override
	public View getView(Context context) {
		TextView text = new TextView(context);
		text.setText(articles);
		text.setTextSize(25);
		return text;
	}
	
	
    
}
