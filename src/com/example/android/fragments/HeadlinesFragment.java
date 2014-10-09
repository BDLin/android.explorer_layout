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
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class HeadlinesFragment extends Fragment {
    
	OnHeadlineSelectedListener mCallback;
	private ArrayList<Viewable> array = new ArrayList<Viewable>();
    private ListView list;
    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by H	eadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);/****************/
        public void onArticleSelected(View view);   /****************/
    }
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HeadlineFragment", "onCreate()............");     
        
        list = new ListView(getActivity());
        
        Ipsum I1 = new Ipsum("Article One", "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
        Ipsum I2 = new Ipsum("Article Two","Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
        
        array.add(I1);
        array.add(I2);
        array.add(new ListData("List Test"));
        array.add(new ImageData("Image Test", getActivity(), BitmapFactory.decodeResource(getResources(), R.drawable.winter)));
        list.setAdapter(new AnyObjectAdapter(getActivity(), array));
        // Create an array adapter for the list view, using the Ipsum headlines array
        Log.i("HeadlinesFragment", "setList!!");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("HeadlineFragment", "onStrat()..........");
        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
        
        ArticleFragment article = (ArticleFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.article_fragment);
        list.setOnItemClickListener(new ListOnClick(article, getActivity(),array));
        
        
        RelativeLayout relative = (RelativeLayout)getActivity().findViewById(R.id.healines_layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        
        
        
		List<ImageButton> imgBtnList = new ArrayList<ImageButton>();
        imgBtnList.add((new CustomizeImageButton(getActivity(), R.drawable.box_small_icon)).getButtont());
        imgBtnList.add((new CustomizeImageButton(getActivity(), R.drawable.download_folder_small_icon)).getButtont());
        imgBtnList.add((new CustomizeImageButton(getActivity(), R.drawable.setting_small_icon)).getButtont());
        
        ImageBtnTest ibt = new ImageBtnTest(getActivity());
        ibt.addBtn(imgBtnList);
        
        View view = ibt.getView();
        view.setLayoutParams(params);
        
        relative.addView(list);
        relative.addView(view);
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