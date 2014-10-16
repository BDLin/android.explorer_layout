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


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HeadlinesFragment extends Fragment {
    
	OnHeadlineSelectedListener mCallback;
	
    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by H	eadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);/****************/
        public void onArticleSelected(View view);   /****************/
    }
    
    public void addButtonToView(TabView view){
    	LinearLayout linear = (LinearLayout)getActivity().findViewById(R.id.btn_layout);
    	ImageButton imgBtn = view.getBtn();
    	
    	//Set init View page
    	if(imgBtn.getId() == 0)
    		getActivity().getSupportFragmentManager().beginTransaction()
            	.add(R.id.frag_container, (Fragment)view).commit();
    	
    	imgBtn.setOnClickListener(new ImgBtnOnClick(this, linear, (Fragment)view));
    	Log.i("HeadlineFragment", "Btn set listener Finish");
    	linear.addView(imgBtn);
    	Log.i("HeadlineFragment", "Btn add to linear Finish");
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HeadlineFragment", "onCreate()............");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("HeadlineFragment", "onStrat()..........");
        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            //list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            Log.i("HeadlinesFragment", "article_fragment is not null");
        }
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("HeadlineFragment", "onAttach()............");
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("HeadlineFragment", "onCreateView()............");
        return inflater.inflate(R.layout.headline_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("HeadlineFragment", "onActivityCreate()............");
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("HeadlineFragment", "onDestroyView()............");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("HeadlineFragment", "onDestroy()............");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("HeadlineFragment", "onDetach()............");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("HeadlineFragment", "onPause()............");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("HeadlineFragment", "onResume()............");
    }
    
    @Override
    public void onStop() {
        super.onStop();
        Log.i("HeadlineFragment", "onStop()............");
    }
}