/*
 * Copyright (C) 2012 The Android Open Source Project
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class ArticleFragment extends Fragment {
	final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("ArticleFragment", "onCreateView()............");
		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@SuppressLint("NewApi")
	public void updateArticleView(View view) {
		RelativeLayout relative = (RelativeLayout)getActivity().findViewById(R.id.relative_layout);
		relative.removeAllViews();
		if(view instanceof ImageView){
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			((ImageView) view).setScaleType(ImageView.ScaleType.CENTER);
			view.setLayoutParams(params);
		}
		relative.addView(view);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the current article selection in case we need to recreate the
		// fragment
		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("ArticleFragment", "onStart()............");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("ArticleFragment", "onCreate()............");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i("ArticleFragment", "onActivityCreate()............");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("ArticleFragment", "onDestroyView()............");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("ArticleFragment", "onDestroy()............");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i("ArticleFragment", "onDetach()............");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("ArticleFragment", "onPause()............");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("ArticleFragment", "onResume()............");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("ArticleFragment", "onStop()............");
	}

	@Override
	public void onAttach(Activity activity) {
		Log.i("ArticleFragment", "onAttach()............");
		super.onAttach(activity);
	}
}