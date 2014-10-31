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

import android.content.Context;

import com.viewpagerindicator.UnderlinePageIndicator;

public class BtnWithUnderlinePageIndicator extends UnderlinePageIndicator {

	private List<TabView> fragmentList;
	
	public BtnWithUnderlinePageIndicator(Context context) {
		super(context);
		fragmentList = new ArrayList<TabView>();
	}
	
	public void addTabView(TabView view){
		fragmentList.add(view);
	}
	
	public void clean(){
		fragmentList.clear();
	}
	
	public void onPageSelected(int position) {
		super.onPageSelected(position);
		for (TabView view:fragmentList)
			view.getIndexButton().setAlpha((float)0.5);
		fragmentList.get(position).getIndexButton().setAlpha((float) 1.0);
	}
}
