/* Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.listener;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
/**
 * This class function is will tab view change to corresponding the page when press tag.
 * @author <bdl9437@gmail.com>
 */
public class TabButtonOnClick implements OnClickListener {

	private LinearLayout mLinear;
	private ViewPager mViewPager;

	/**
	 * 
	 * @param linear	The linear is a linear layout of put tab button.
	 * @param vp		The vp is a viewPager of put tab view.
	 */
	public TabButtonOnClick(LinearLayout linear, ViewPager vp) {
		mLinear = linear;
		mViewPager = vp;
	}// End of ImgBtnOnClick construct

	public void onClick(View v) {
		for (int i = 0; i < mLinear.getChildCount(); i++) {
			if (mLinear.getChildAt(i) == v) {
				mViewPager.setCurrentItem(i);
				v.setAlpha((float) 1.0);
			} else
				mLinear.getChildAt(i).setAlpha((float) 0.5);
		}
	}// End of onClick function
}// End of ImgBtnOnClick class