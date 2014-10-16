package com.example.android.fragments;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class LocalBtnListener implements OnClickListener {

	private HeadlinesFragment headline;
	private LinearLayout linear;
	
	public LocalBtnListener(HeadlinesFragment headline, LinearLayout linear){
		this.headline = headline;
		this.linear = linear;
	}
	
	@Override
	public void onClick(View v) {
		Log.i("LocalBtnListener", "Click");
		LocalListFragment local = new LocalListFragment();
		headline.getActivity().getSupportFragmentManager().beginTransaction()
		       .replace(R.id.frag_container, local).commit();
		for(int i = 0; i < linear.getChildCount(); i++)
			linear.getChildAt(i).setAlpha((float) 0.5);
		v.setAlpha((float) 1.0);
	}

}
