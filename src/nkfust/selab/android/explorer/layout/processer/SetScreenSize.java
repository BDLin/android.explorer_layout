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
package nkfust.selab.android.explorer.layout.processer;

import nkfust.selab.android.explorer.layout.model.TabFragment;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
/**
 * This class is set specific view size in the content page.
 * <p>
 * This usage:
 * <ul>
 * <li>First must set content page size use "setContentSize(int Height, int Width)" function, 
 * and specific view size use  "setDataViewSize(int Height, int Width)"  function.
 * <li>Second used to "set(Context context, View aView, Boolean isFullScreen)" function 
 * set specific view size.
 * </ul>
 * 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class SetScreenSize {
	
	private static int dataHeight, dataWidth, contentHeight, contentWidth;
	
	/**
	 * This function is set specific view size in the content page.
	 * @param context 		The context is a Activity parent.
	 * @param aView			The aView is specific view.
	 * @param isFullScreen	The isFullScreen is judge content page whether full screen.
	 */
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
			if(TabFragment.getFrameLayout() == null)
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
	
	/**
	 * This function is set specific view original size.
	 * @param Height	The specific view original height.
	 * @param Width		The specific view original width.
	 */
	public static void setDataViewSize(int Height, int Width) {
		dataHeight = Height;
		dataWidth = Width;
	}
	
	/**
	 * This function is set content page current size.
	 * @param Height	The content page current height.
	 * @param Width		The content page current width.
	 */
	public static void setContentSize(int Height, int Width) {
		contentHeight = Height;
		contentWidth = Width;
	}
}