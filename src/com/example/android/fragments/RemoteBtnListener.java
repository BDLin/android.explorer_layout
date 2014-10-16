package com.example.android.fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


public class RemoteBtnListener implements OnClickListener {

	private HeadlinesFragment headline;
	private LinearLayout linear;
	
	public RemoteBtnListener(HeadlinesFragment headline, LinearLayout linear){
		this.headline = headline;
		this.linear = linear;		
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onClick(View v) {
		Log.i("RemoteBtnListener", "Click");
		Fragment remote = new RemoteListFragment();
		headline.getActivity().getSupportFragmentManager().beginTransaction()
	       .replace(R.id.frag_container, remote).commit();
		for(int i = 0; i < linear.getChildCount(); i++)
			linear.getChildAt(i).setAlpha((float) 0.5);
		v.setAlpha((float) 1.0);
	}

}
