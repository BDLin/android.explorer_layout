/* Copyright (C) 2015 Zi-Xiang Lin <bdl9437@gmail.com>
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

import nkfust.selab.android.explorer.layout.model.PageSelectedListener;
import nkfust.selab.android.explorer.layout.model.TabFragment;

public class FocuseTabButtonListener implements PageSelectedListener {

	private TabFragment mTabFragment;
	
	public FocuseTabButtonListener(TabFragment tabfragment){
		mTabFragment = tabfragment;
	}
	
	@Override
	public void onPageSelect() {
		for (int i = 0; i < mTabFragment.getButtonLinearLayout().getChildCount(); i++) {
			if (mTabFragment.getViewpager().getCurrentItem() != i)
				mTabFragment.getButtonLinearLayout().getChildAt(i).setAlpha((float) 0.5);
			else
				mTabFragment.getButtonLinearLayout().getChildAt(i).setAlpha((float) 1.0);
		}
	}
}
