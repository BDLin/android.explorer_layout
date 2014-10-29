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
package nkfust.android.explorer.layout.modle;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class ImgBtnOnClick implements OnClickListener {

	private LinearLayout linear;
	private ViewPager vp;

	public ImgBtnOnClick(LinearLayout linear, ViewPager vp) {
		this.linear = linear;
		this.vp = vp;
	}//End of ImgBtnOnClick construct

	public void onClick(View v) {
		if (v.getAlpha() != 1.0) {
			vp.setCurrentItem(v.getId());
			
			for (int i = 0; i < linear.getChildCount(); i++)
				linear.getChildAt(i).setAlpha((float) 0.5);
			v.setAlpha((float) 1.0);
		}//End of if condition
	}//End of onClick function
}//End of ImgBtnOnClick class
