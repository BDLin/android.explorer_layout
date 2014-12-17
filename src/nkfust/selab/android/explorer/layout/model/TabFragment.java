/*
FF * Copyright (C) 2012 The Android Open Source Project
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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TabFragment extends Fragment {

	private ViewPager vp;
	private ScreenSlidePagerAdapter pagerAdapter;
	private LinearLayout linear;
	private BtnWithUnderlinePageIndicator indicator;
	private ImageButton imgBtn;
	private LinearLayout.LayoutParams params;
	private Menu mMenu;

	private static Fragment tabFragment;
	private static FrameLayout frame;

	public void addTabView(TabView view) {
		imgBtn = view.getIndexButton();
		params.weight = 1;
		imgBtn.setLayoutParams(params);
		imgBtn.setOnClickListener(new ImgBtnOnClick(linear, vp));
		linear.addView(imgBtn);
		pagerAdapter.addTabView(view);
	}

	public void clean() {
		linear.removeAllViews();
		pagerAdapter.clean();
	}

	public Boolean isFragmentStatePagerAdapterNull() {
		return (vp.getAdapter() == null) ? true : false;
	}
	
	public Menu getMenu(){
		return mMenu;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.headline_view, container, false);
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu){
		mMenu = menu;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		linear = (LinearLayout) getActivity().findViewById(R.id.btn_layout);
		vp = (ViewPager) getActivity().findViewById(R.id.frame_pager);
		pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
		indicator = new BtnWithUnderlinePageIndicator(getActivity(), this);
		params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT);
		((LinearLayout) getActivity().findViewById(R.id.viewpager_layout)).addView(indicator);
		tabFragment = this;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (isFragmentStatePagerAdapterNull())
			setViewPager();

		for (int i = 0; i < linear.getChildCount(); i++) {
			if (vp.getCurrentItem() != i)
				linear.getChildAt(i).setAlpha((float) 0.5);
			else
				linear.getChildAt(i).setAlpha((float) 1.0);
		}
	}
	
	private void setViewPager() {
		vp.setAdapter(pagerAdapter);
		indicator.setViewPager(vp);
		indicator.setFades(false);
		indicator.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 2));
	}
	
	public Fragment getCurrentFragment() {
		return (Fragment) pagerAdapter.instantiateItem(vp, vp.getCurrentItem());
	}
	
	public TabView getCurrentTabView() {
		return (TabView) pagerAdapter.getItemTabView(vp.getCurrentItem());
	}
	
	public ViewPager getViewpager(){
		return vp;
	}
	
	public LinearLayout getButtonLinearLayout(){
		return linear;
	}
	
	public static void setFrameLayout(FrameLayout framelayout){
		frame = framelayout;
	}
	
	public static FrameLayout getFrameLayout(){
		return frame;
	}

	public static Fragment getTabFragment() {
		return tabFragment;
	}
}