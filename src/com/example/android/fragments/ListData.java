package com.example.android.fragments;

import java.util.Arrays;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListData implements Viewable {

	static String Tag = "Fragment";
	private String title;
	private String[] presidents = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
	
	public ListData (String title){
		this.title = title;
	}
	
	@Override
	public View getView(Context context) {
		int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
		ListView list = new ListView(context);
		list.setAdapter(new ArrayAdapter<String>(context, layout, presidents));
		//list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		return list;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
