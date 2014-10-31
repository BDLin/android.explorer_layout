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
package nkfust.selab.android.explorer.layout.model;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.viewpagerindicator.UnderlinePageIndicator;


public class BtnWithUnderlinePageIndicator extends UnderlinePageIndicator {

	private ViewPager vp;
	private LinearLayout linear;
	
	public BtnWithUnderlinePageIndicator(Context context, ViewPager vp, LinearLayout linear) {
		super(context);
		this.vp = vp;
		this.linear = linear;
	}
	
	public void onPageSelected(int position) {
		super.onPageSelected(position);
		for (int i = 0; i < linear.getChildCount(); i++){
			if(vp.getCurrentItem() != i)
				linear.getChildAt(i).setAlpha((float) 0.5);
			else
				linear.getChildAt(i).setAlpha((float) 1.0);
		}
	}
}
