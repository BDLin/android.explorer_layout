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
 * This class is a photo viewer.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class PhotoViewer extends RelativeLayout {

	private ImageButton leftButton, rightButton;
	private TextView displayPageView;
	private String pageText;
	private int index;
	
	private PagerAdapter mPagerAdapter;
	private List<String> mPaths;
	private List<OnPageChangeListener> mOnPageChangeListeners;
	private ViewPager mPager;
	
	private Context mContext;
	private String currentFolderPath;
	private ContentFragment mContentFragment;
	
	/**
	 * @param context The Activity parent.
	 * @param paths	  The paths is all file path at current folder path.
	 * @param file	  The file is photo file that want to read.
	 */
	public PhotoViewer(Context context, List<String> paths, IFile file) {
		super(context);
		mContentFragment = new ContentFragment();
		mOnPageChangeListeners = new ArrayList<OnPageChangeListener>();
		mContext = context;
		index = -1;
		LayoutInflater.from(context).inflate(R.layout.photo_view_layout, this);
		init();
		setDisplayPhoto(paths, file);
	}

	private void init(){
		displayPageView = (TextView)findViewById(R.id.page_textview);
		mPager = (ViewPager)findViewById(R.id.photo_pager);
		leftButton = (ImageButton)findViewById(R.id.alter_button);
		rightButton = (ImageButton)findViewById(R.id.share_button);
		leftButton.setImageResource(R.drawable.photo_list);
		rightButton.setImageResource(R.drawable.photo_right);
		leftButton.setOnClickListener(new PhotoAlterListener(this));
		rightButton.setOnClickListener(new PhotoShareListener(mContext, this));
	}
	
	/**
	 * This function is setting show photo to view.
	 * @param paths	The paths is all file path at current folder path.
	 * @param file	The file is a want to display photo of file.		
	 */
	public void setDisplayPhoto(List<String> paths, IFile file){
		mPaths = ImagesFilter.getImagesList(paths);
		currentFolderPath = URLUtils.parentUrl(mPaths.get(0));
		mPagerAdapter = new PhotoPageAdapter(TabFragment.getActionBarActivity().getSupportFragmentManager(), mPaths);
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels){
				pageText = position + 1 + " of " + mPaths.size();
				displayPageView.setText(pageText);
				
				for(OnPageChangeListener listener : mOnPageChangeListeners)
					listener.onPageSelected(position);
				
				index = position;
				try {
					mContentFragment.setIFile(mContentFragment.getFactory().getFile(mPaths.get(position)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String currentUrl = null;
		try {
			currentUrl = file.getUrl().substring(file.getUrl().indexOf("/sata_1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < mPaths.size(); i++)
			if(mPaths.get(i).endsWith(currentUrl))
				index = i;
		mPager.setCurrentItem(index);
	}
	
	/**
	 * This function is release photo viewer used resource.
	 */
	public void releasePhotoFragmentList(){
		if (mPager.getChildCount() > 0){
			mPager.removeAllViews();
//			mPagerAdapter.notifyDataSetChanged();
		}
		mPager = null;
		mPagerAdapter = null;
		mPaths.clear();
		System.gc();
	}
	
	public ViewPager getViewPager(){
		return mPager;
	}
	
	public PagerAdapter getPagerAdapter(){
		return mPagerAdapter;
	}
	
	/**
	 * @return The paths content is all photo path at the current folder path.
	 */
	public List<String> getPaths(){
		return mPaths;
	}
	
	public String getCurrentFolderPath(){
		return currentFolderPath;
	}
	
	public String getCurrentPhotoPath(){
		return mPaths.get(index);
	}
	
	public int getPhotoIndex(){
		return index;
	}
	
	public void setPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		mPager.setOnPageChangeListener(listener);
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