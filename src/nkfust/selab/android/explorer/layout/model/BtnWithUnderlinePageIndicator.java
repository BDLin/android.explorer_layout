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
import android.view.Menu;

import com.viewpagerindicator.UnderlinePageIndicator;

public class BtnWithUnderlinePageIndicator extends UnderlinePageIndicator {

	private TabFragment tabFragment;

	public BtnWithUnderlinePageIndicator(Context context,
			TabFragment tabFragment) {
		super(context);
		this.tabFragment = tabFragment;
	}

	public void onPageSelected(int position) {
		super.onPageSelected(position);
		
		TabView tabView = tabFragment.getCurrentTabView();
		tabView.updateContent();
		Menu menu = tabFragment.getMenu();
		menu.clear();
		if(tabView.getMenuResource() != 0)
			tabFragment.getActivity().getMenuInflater().inflate(tabView.getMenuResource(), menu);
		
		for (int i = 0; i < tabFragment.getButtonLinearLayout().getChildCount(); i++) {
			if (tabFragment.getViewpager().getCurrentItem() != i)
				tabFragment.getButtonLinearLayout().getChildAt(i)
						.setAlpha((float) 0.5);
			else
				tabFragment.getButtonLinearLayout().getChildAt(i)
						.setAlpha((float) 1.0);
		}
	}
}
