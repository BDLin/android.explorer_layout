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
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;

public class SetScreenSize {
	
	private static int dataHeight, dataWidth, contentHeight, contentWidth;
	
	public static void set(Context context, View aView, Boolean isFullScreen){
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metrics);
		ViewGroup.MarginLayoutParams params = (MarginLayoutParams) aView.getLayoutParams();
		// Get the width of the screen
		int screenWidth = windowManager.getDefaultDisplay().getWidth();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();
		float screenProportion = (float) screenWidth / (float) screenHeight;
		float videoProportion = (float) dataWidth / (float) dataHeight;
		float contentScreenProportion = (float) contentWidth / (float) contentHeight;
		if (isFullScreen) {
			
			TabFragment.getActionBarActivity().getSupportActionBar().hide();
			TabFragment.getActionBarActivity().getSupportFragmentManager()
			        .beginTransaction().hide(TabFragment.getTabFragment()).commit();
			
			if (videoProportion > screenProportion) {
				params.width = screenWidth;
				params.height = (int) ((float) screenWidth / videoProportion);
			} else {
				params.width = (int) (videoProportion * (float) screenHeight);
				params.height = screenHeight;
			}
		} else {
			
			TabFragment.getActionBarActivity().getSupportActionBar().show();
			TabFragment.getActionBarActivity().getSupportFragmentManager()
			        .beginTransaction().show(TabFragment.getTabFragment()).commit();
			
			if (videoProportion > contentScreenProportion) {
				params.width = contentWidth;
				params.height = (int) ((float) contentWidth / videoProportion);
			} else {
				params.width = (int) (videoProportion * (float) contentHeight);
				params.height = contentHeight;
			}
		}
		params.setMargins(0, 0, 0, 0);
		aView.setLayoutParams(params);
	}
	
	public static void setDataViewSize(int Height, int Width) {
		dataHeight = Height;
		dataWidth = Width;
	}
	
	public static void setContentSize(int Height, int Width) {
		contentHeight = Height;
		contentWidth = Width;
	}
}