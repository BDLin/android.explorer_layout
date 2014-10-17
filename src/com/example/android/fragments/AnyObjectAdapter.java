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

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AnyObjectAdapter extends BaseAdapter{
    
	List<Viewable> list_title;
	Context context;

	int count = 0;
	public AnyObjectAdapter(Context context ,List<Viewable> array){
		super();
		list_title = array;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list_title.size();
	}

	@Override
	public Object getItem(int position) {
		return list_title.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView text_view = new TextView(context);
		text_view.setText(list_title.get(position).getTitle());
		text_view.setTextSize(25);
		return text_view;
	}
}
