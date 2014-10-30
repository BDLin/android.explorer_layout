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

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
		implements IconPagerAdapter {

	private List<TabView> fragmentList;

	public ScreenSlidePagerAdapter(FragmentManager fm) {
		super(fm);
		fragmentList = new ArrayList<TabView>();
	}
	
	public void addTabView(TabView view){
		fragmentList.add(view);
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {
		return (fragmentList == null || fragmentList.size() == 0) ? null
				: fragmentList.get(position).getFragment();
	}

	@Override
	public int getCount() {
		return fragmentList == null ? 0 : fragmentList.size();
	}

	@Override
	public int getIconResId(int index) {
		return 0;
	}
}
