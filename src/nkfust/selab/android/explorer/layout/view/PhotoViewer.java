/*
 * Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.view;

import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.model.TabFragment;
import nkfust.selab.android.explorer.layout.processer.ImagesFilter;
import poisondog.string.ExtractFileName;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PhotoViewer extends RelativeLayout {

	private TextView textView;
	private String text;
	private int index;
	private ImageButton alterBtn, shareBtn;
	
	private ViewPager aPager;
	private PagerAdapter aPagerAdapter;
	private List<String> aPaths;
	
	public PhotoViewer(Context context, List<String> paths, String fileName) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.photo_view_layout, this);
		init();
		setCurrentItem(paths, fileName);
	}

	private void init(){
		textView = (TextView)findViewById(R.id.page_textview);
		aPager = (ViewPager)findViewById(R.id.photo_pager);
		alterBtn = (ImageButton)findViewById(R.id.alter_button);
		shareBtn = (ImageButton)findViewById(R.id.share_button);
		alterBtn.setImageResource(R.drawable.photo_list);
		shareBtn.setImageResource(R.drawable.photo_right);
		alterBtn.setOnClickListener(new View.OnClickListener() {
			String[] items = {"Delete","Rename"};
			@Override
			public void onClick(View v) {
				String title = new ExtractFileName().process(aPaths.get(index));
				PhotoAlterDialogFragment dialog = new PhotoAlterDialogFragment( title, items);
				dialog.show(TabFragment.getActionBarActivity().getSupportFragmentManager(), "dialog");
			}
		});
	}
	
	public void setCurrentItem(List<String> paths, String fileName){
		aPaths = ImagesFilter.getImagesList(paths);
		aPagerAdapter = new PhotoPageAdapter(TabFragment.getActionBarActivity().getSupportFragmentManager(), aPaths);
		aPager.setAdapter(aPagerAdapter);
		aPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels){
				text = position + 1 + " of " + aPaths.size();
				textView.setText(text);
			}
		});
		
		for(int i = 0; i < aPaths.size(); i++)
			if((new ExtractFileName().process(aPaths.get(i)).equals(fileName)))
				index = i;
		aPager.setCurrentItem(index);
	}
	
	public void releasePhotoFragmentList(){
		Log.i("PhotoViewer","aPager count: " + aPager.getChildCount());
		if (aPager.getChildCount() > 0){
			aPager.removeAllViews();
			aPagerAdapter.notifyDataSetChanged();
		}
		Log.i("PhotoViewer","aPager count: " + aPager.getChildCount());
		aPager = null;
		aPagerAdapter = null;
		aPaths.clear();
		System.gc();
	}
	
	public ViewPager getViewPager(){
		return aPager;
	}
	
	public PagerAdapter getPagerAdapter(){
		return aPagerAdapter;
	}
	
	public List<String> getPaths(){
		return aPaths;
	}
	
	public void setPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		aPager.setOnPageChangeListener(listener);
	}
	
	public void setPhotoAlterButtonListener(View.OnClickListener listener){
		alterBtn.setOnClickListener(listener);
	}
	
	public void setPhotoShareButtonListener(View.OnClickListener listener){
		shareBtn.setOnClickListener(listener);
	}
}