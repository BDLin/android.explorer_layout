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

import nkfust.selab.android.explorer.layout.model.TabFragment;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
/**
 * This class function is screen zoom full when browsing image. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class ImageDoFullScreenListener extends SimpleOnGestureListener {
	
	private boolean isFullScreen;
	
	public ImageDoFullScreenListener(){
		isFullScreen = false;
	}
	
	@Override
	public boolean onDoubleTap(MotionEvent e){
		if(isFullScreen){
			isFullScreen = false;
			TabFragment.getActionBarActivity().getSupportActionBar().show();
			TabFragment.getActionBarActivity().getSupportFragmentManager()
			        .beginTransaction().show(TabFragment.getTabFragment()).commit();
		}else{
			isFullScreen = true;
			TabFragment.getActionBarActivity().getSupportActionBar().hide();
			TabFragment.getActionBarActivity().getSupportFragmentManager()
			        .beginTransaction().hide(TabFragment.getTabFragment()).commit();
		}
		return false;
	}
}
