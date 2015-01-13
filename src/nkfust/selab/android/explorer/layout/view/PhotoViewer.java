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
import nkfust.selab.android.explorer.layout.listener.PhotoAlterListener;
import nkfust.selab.android.explorer.layout.listener.PhotoShareListener;
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
	private ImageButton leftBtn, rightBtn;
	
	private ViewPager aPager;
	private PagerAdapter aPagerAdapter;
	private List<String> aPaths;
	
	private Context mContext;
	
	public PhotoViewer(Context context, List<String> paths, String fileName) {
		super(context);
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.photo_view_layout, this);
		init();
		setCurrentItem(paths, fileName);
	}

	private void init(){
		textView = (TextView)findViewById(R.id.page_textview);
		aPager = (ViewPager)findViewById(R.id.photo_pager);
		leftBtn = (ImageButton)findViewById(R.id.alter_button);
		rightBtn = (ImageButton)findViewById(R.id.share_button);
		leftBtn.setImageResource(R.drawable.photo_list);
		rightBtn.setImageResource(R.drawable.photo_right);
		leftBtn.setOnClickListener(new PhotoAlterListener(this));
		rightBtn.setOnClickListener(new PhotoShareListener(mContext, this));
	}
	
	public void setCurrentItem(List<String> paths, String fileName){
		aPaths = ImagesFilter.getImagesList(paths);
		aPagerAdapter = new PhotoPageAdapter(TabFragment.getActionBarActivity().getSupportFragmentManager(), aPaths);
		aPager.setAdapter(aPagerAdapter);
		aPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels){
				text = position + 1 + " of " + aPaths.size();
				textView.setText(text);
				index = position;
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
	
	public int getPhotoIndex(){
		return index;
	}
	
	public void setPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		aPager.setOnPageChangeListener(listener);
	}
	
	public void setPhotoLeftButtonListener(View.OnClickListener listener){
		leftBtn.setOnClickListener(listener);
	}
	
	public void setPhotoRightButtonListener(View.OnClickListener listener){
		rightBtn.setOnClickListener(listener);
	}
}