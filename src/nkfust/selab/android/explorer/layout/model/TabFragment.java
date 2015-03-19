/*
 * Copyright (C) 2012 The Android Open Source Project
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
package nkfust.selab.android.explorer.layout.model;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.listener.ChangeActionBarTitleListener;
import nkfust.selab.android.explorer.layout.listener.ChangeMenuListener;
import nkfust.selab.android.explorer.layout.listener.FocuseTabButtonListener;
import nkfust.selab.android.explorer.layout.listener.ImageButtonOnClick;
import nkfust.selab.android.explorer.layout.listener.UpdateTabFragmentListener;
import nkfust.selab.android.explorer.layout.view.BtnWithUnderlinePageIndicator;
import nkfust.selab.android.explorer.layout.view.ScreenSlidePagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
/**
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class TabFragment extends Fragment {

	private BtnWithUnderlinePageIndicator indicator;
	private ScreenSlidePagerAdapter pagerAdapter;
	private LinearLayout.LayoutParams params;
	private LinearLayout buttonLinearLyaout;
	private ViewPager fileListViewPager;
	private ImageButton imgBtn;
	private Menu mMenu;

	private static ActionBarActivity activity;
	private static Fragment tabFragment;
	private static FrameLayout frameLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.headline_view, container, false);
	}
	
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
		indicator = new BtnWithUnderlinePageIndicator(getActivity());
		indicator.addPageSelectedListener(new ChangeMenuListener(this));
		indicator.addPageSelectedListener(new FocuseTabButtonListener(this));
		indicator.addPageSelectedListener(new UpdateTabFragmentListener(pagerAdapter));
		indicator.addPageSelectedListener(new ChangeActionBarTitleListener(this));
		params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT);
		fileListViewPager = (ViewPager) view.findViewById(R.id.frame_pager);
		if(frameLayout == null)	{
			buttonLinearLyaout = (LinearLayout) view.findViewById(R.id.btn_layout_bottom);
			((LinearLayout) view.findViewById(R.id.viewpager_layout)).addView(indicator);
		}else{
			buttonLinearLyaout = (LinearLayout) view.findViewById(R.id.btn_layout_top);
			((LinearLayout) view.findViewById(R.id.viewpager_layout)).addView(indicator, 0);
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		setHasOptionsMenu(true);
		activity = (ActionBarActivity) getActivity();
		tabFragment = this;
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu){
		mMenu = menu;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (isFragmentStatePagerAdapterNull())
			setViewPager();

		for (int i = 0; i < buttonLinearLyaout.getChildCount(); i++) {
			if (fileListViewPager.getCurrentItem() != i)
				buttonLinearLyaout.getChildAt(i).setAlpha((float) 0.5);
			else
				buttonLinearLyaout.getChildAt(i).setAlpha((float) 1.0);
		}
	}
	
	public void addTabView(TabView view) {
		imgBtn = view.getIndexButton();
		params.weight = 1;
		imgBtn.setLayoutParams(params);
		imgBtn.setOnClickListener(new ImageButtonOnClick(buttonLinearLyaout, fileListViewPager));
		buttonLinearLyaout.addView(imgBtn);
		pagerAdapter.addTabView(view);
	}

	public void clean() {
		buttonLinearLyaout.removeAllViews();
		pagerAdapter.clean();
	}

	public Boolean isFragmentStatePagerAdapterNull() {
		return (fileListViewPager.getAdapter() == null) ? true : false;
	}
	
	public Menu getMenu(){
		return mMenu;
	}
	
	private void setViewPager() {
		fileListViewPager.setAdapter(pagerAdapter);
		indicator.setViewPager(fileListViewPager);
		indicator.setFades(false);
		indicator.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 2));
	}
	
	public void addPageSelectedListener(PageSelectedListener listener){
		if(indicator != null)
			indicator.addPageSelectedListener(listener);
	}
	
	public ScreenSlidePagerAdapter getTabAdapter(){
		return pagerAdapter;
	}
	
	public int getCurrentPageIndex(){
		return fileListViewPager.getCurrentItem();
	}
	
	public Fragment getCurrentFragment() {
		return (Fragment) pagerAdapter.instantiateItem(fileListViewPager, fileListViewPager.getCurrentItem());
	}
	
	public TabView getCurrentTabView() {
		return (TabView) pagerAdapter.getItemTabView(fileListViewPager.getCurrentItem());
	}
	
	public ViewPager getViewpager(){
		return fileListViewPager;
	}
	
	public LinearLayout getButtonLinearLayout(){
		return buttonLinearLyaout;
	}
	
	public void setFrameLayout(FrameLayout framelayout){
		frameLayout = framelayout;
	}
	
	public static FrameLayout getFrameLayout(){
		return frameLayout;
	}

	public static Fragment getTabFragment() {
		return tabFragment;
	}
	
	public static ActionBarActivity getActionBarActivity() {
		return activity;
	}
}