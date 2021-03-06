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

import nkfust.selab.android.explorer.layout.model.ContentFragment;
import nkfust.selab.android.explorer.layout.model.PageSelectedListener;
/**
 * This class function is clean content view when tab view changed page.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class CleanContentListener implements PageSelectedListener {

	private ContentFragment mContentFragment;
	
	public CleanContentListener(ContentFragment contentFragment){
		mContentFragment = contentFragment;
	}
	
	@Override
	public void onPageSelect() {
		mContentFragment.clearPhotoPagerChangeStateListener();
		mContentFragment.init();
		mContentFragment.releaseMultiMedia();
	}
}
