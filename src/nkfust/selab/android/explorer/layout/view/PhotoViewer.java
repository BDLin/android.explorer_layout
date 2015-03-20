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
import nkfust.selab.android.explorer.layout.model.ContentFragment;
import nkfust.selab.android.explorer.layout.model.TabFragment;
import nkfust.selab.android.explorer.layout.processer.ImagesFilter;
import poisondog.net.URLUtils;
import poisondog.string.ExtractFileName;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalFileFactory;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
	private ViewPager aPager;
	
	private Context mContext;
	private String currentFolderPath;
	private ContentFragment mContentFragment;
	
	public PhotoViewer(Context context, List<String> paths, String fileName) {
		super(context);
		mContentFragment = new ContentFragment();
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.photo_view_layout, this);
		init();
		setCurrentItem(paths, fileName);
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
	
	public void setCurrentItem(List<String> paths, String fileName){
		aPaths = ImagesFilter.getImagesList(paths);
		currentFolderPath = URLUtils.parentUrl(aPaths.get(0));
		aPagerAdapter = new PhotoPageAdapter(TabFragment.getActionBarActivity().getSupportFragmentManager(), aPaths);
		aPager.setAdapter(aPagerAdapter);
		aPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels){
				pageText = position + 1 + " of " + aPaths.size();
				displayPageView.setText(pageText);
				index = position;
				try {
					mContentFragment.setIFile(new LocalFileFactory().getFile(aPaths.get(position)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		for(int i = 0; i < aPaths.size(); i++)
			if((new ExtractFileName().process(aPaths.get(i)).equals(fileName)))
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
	
	public IFile getCurrentPhotoIFile(){
		try {
			return new LocalFileFactory().getFile(aPaths.get(index));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
}