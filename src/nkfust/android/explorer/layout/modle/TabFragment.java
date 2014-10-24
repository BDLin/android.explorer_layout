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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TabFragment extends Fragment {

	public void addTabView(TabView view) {
		LinearLayout linear = (LinearLayout) getActivity().findViewById(
				R.id.btn_layout);
		ImageButton imgBtn = view.getBtn();

		// Set init View page
		if (imgBtn.getId() == 0)
			getActivity().getSupportFragmentManager().beginTransaction()
					.add(R.id.frag_container, (Fragment) view).commit();

		imgBtn.setOnClickListener(new ImgBtnOnClick(this, linear,
				(Fragment) view));
		linear.addView(imgBtn);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.headline_view, container, false);
	}
}