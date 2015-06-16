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
import nkfust.selab.android.explorer.layout.model.TabView;
import android.view.Menu;
/**
 * This class function is will menu change to the corresponding menu when tab view changed page.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class ChangeMenuListener implements PageSelectedListener {

	private TabFragment mTabFragment;
	
	public ChangeMenuListener(TabFragment tabfragment){
		mTabFragment = tabfragment;
	}
	
	@Override
	public void onPageSelect() {
		TabView tabView = mTabFragment.getCurrentTabView();
		Menu menu = mTabFragment.getMenu();
		menu.clear();
		if(tabView.getMenuResource() != 0)
			mTabFragment.getActivity().getMenuInflater().inflate(tabView.getMenuResource(), menu);
	}
}
