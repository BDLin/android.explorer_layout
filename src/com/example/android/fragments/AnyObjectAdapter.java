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
		// TODO Auto-generated method stub
		return list_title.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list_title.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("Counter", String.valueOf(++count));
		
		TextView text_view = new TextView(context);
		text_view.setText(list_title.get(position).getTitle());
		text_view.setTextSize(25);
		return text_view;
	}
	
}
