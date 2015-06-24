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
package nkfust.selab.android.explorer.layout.view;

import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.model.PageSelectedListener;
import android.content.Context;

import com.viewpagerindicator.UnderlinePageIndicator;
/**
 * 這是用在左邊的TabFragment，指示目前位於哪個tabView的橫條。
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class BtnWithUnderlinePageIndicator extends UnderlinePageIndicator {

	private List<PageSelectedListener> mList;
	
	public BtnWithUnderlinePageIndicator(Context context) {
		super(context);
		mList = new ArrayList<PageSelectedListener>();
	}

	@Override
	public void onPageSelected(int position) {
		super.onPageSelected(position);
		for(PageSelectedListener listener : mList)
			listener.onPageSelect();
	}
	
	/**
	 * This function is add listener to process event 
	 * that when changed to other page in the tab page.
	 */
	public void addPageSelectedListener(PageSelectedListener listener){
		mList.add(listener);
	}
}