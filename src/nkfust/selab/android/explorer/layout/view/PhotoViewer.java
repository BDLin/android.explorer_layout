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

import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.listener.PhotoAlterListener;
import nkfust.selab.android.explorer.layout.listener.PhotoShareListener;
import nkfust.selab.android.explorer.layout.model.ContentFragment;
import nkfust.selab.android.explorer.layout.model.TabFragment;
import nkfust.selab.android.explorer.layout.processer.ImagesFilter;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class PhotoViewer extends RelativeLayout {

	private ImageButton leftButton, rightButton;
	private TextView displayPageView;
	private String pageText;
	private int index;
	
	private PagerAdapter aPagerAdapter;
	private List<String> aPaths;
	private List<OnPageChangeListener> mOnPageChangeListeners;
	private ViewPager aPager;
	
	private Context mContext;
	private String currentFolderPath;
	private ContentFragment mContentFragment;
	
	public PhotoViewer(Context context, List<String> paths, IFile file) {
		super(context);
		mContentFragment = new ContentFragment();
		mOnPageChangeListeners = new ArrayList<OnPageChangeListener>();
		mContext = context;
		index = -1;
		LayoutInflater.from(context).inflate(R.layout.photo_view_layout, this);
		init();
		setCurrentItem(paths, file);
	}

	private void init(){
		displayPageView = (TextView)findViewById(R.id.page_textview);
		aPager = (ViewPager)findViewById(R.id.photo_pager);
		leftButton = (ImageButton)findViewById(R.id.alter_button);
		rightButton = (ImageButton)findViewById(R.id.share_button);
		leftButton.setImageResource(R.drawable.photo_list);
		rightButton.setImageResource(R.drawable.photo_right);
		leftButton.setOnClickListener(new PhotoAlterListener(this));
		rightButton.setOnClickListener(new PhotoShareListener(mContext, this));
	}
	
	public void setCurrentItem(List<String> paths, IFile currentFile){
		aPaths = ImagesFilter.getImagesList(paths);
		currentFolderPath = URLUtils.parentUrl(aPaths.get(0));
		aPagerAdapter = new PhotoPageAdapter(TabFragment.getActionBarActivity().getSupportFragmentManager(), aPaths);
		aPager.setAdapter(aPagerAdapter);
		aPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels){
				pageText = position + 1 + " of " + aPaths.size();
				displayPageView.setText(pageText);
				
				for(OnPageChangeListener listener : mOnPageChangeListeners)
					listener.onPageSelected(position);
				
				index = position;
				try {
					mContentFragment.setIFile(mContentFragment.getFactory().getFile(aPaths.get(position)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		String currentUrl = null;
		try {
			currentUrl = currentFile.getUrl().substring(currentFile.getUrl().indexOf("/sata_1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < aPaths.size(); i++)
			if(aPaths.get(i).endsWith(currentUrl))
				index = i;
		aPager.setCurrentItem(index);
	}
	
	public void releasePhotoFragmentList(){
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
	
	public String getCurrentFolderPath(){
		return currentFolderPath;
	}
	
	public String getCurrentPhotoPath(){
		return aPaths.get(index);
	}
	
	public int getPhotoIndex(){
		return index;
	}
	
	public void setPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		aPager.setOnPageChangeListener(listener);
	}
	
	public void setPhotoLeftButtonListener(View.OnClickListener listener){
		leftButton.setOnClickListener(listener);
	}
	
	public void setPhotoRightButtonListener(View.OnClickListener listener){
		rightButton.setOnClickListener(listener);
	}
	
	public void setContentFragment(ContentFragment contentfragment){
		mContentFragment = contentfragment;
	}
	
	public void setOnPageChangeListener(List<OnPageChangeListener> listeners){
		mOnPageChangeListeners = listeners;
	}
}