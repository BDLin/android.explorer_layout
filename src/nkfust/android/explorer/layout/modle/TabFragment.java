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
package nkfust.android.explorer.layout.modle;

import nkfust.android.explorer.layout.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TabFragment extends Fragment {

	private ViewPager vp;
	private LinearLayout linear;
	private ScreenSlidePagerAdapter pagerAdapter;
	private BtnWithUnderlinePageIndicator indicator;

	public void addTabView(TabView view) {
		ImageButton imgBtn = view.getBtn();
		imgBtn.setOnClickListener(new ImgBtnOnClick(linear, vp));
		linear.addView(imgBtn);
		pagerAdapter.addTabView(view);
		indicator.addTabView(view);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.headline_view, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		linear = (LinearLayout) getActivity().findViewById(R.id.btn_layout);
		vp = (ViewPager) getActivity().findViewById(R.id.frame_pager);
		pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
		indicator = new BtnWithUnderlinePageIndicator(getActivity());
	}
	
	@Override
	public void onStart (){
		super.onStart();
		setViewPager();
	}
	
	private void setViewPager() {
		vp.setAdapter(pagerAdapter);
		
		indicator.setViewPager(vp);
		indicator.setFades(false);
		indicator.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 2));
		((LinearLayout) getActivity().findViewById(R.id.viewpager_layout))
				.addView(indicator);
	}
}