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

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.ImageButton;
/**
 * If want to display the page , must implementes this interface.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public interface TabView {
	/**
	 *Get index tag.
	 *@return The index button.  
	 */
	public ImageButton getIndexButton();
	
	/**
	 *Get display page.
	 */
	public Fragment getFragment();
	
	/**
	 *Get menu style on this page.
	 *@return The id for menu XML file.
	 */
	public int getMenuResource();
	
	/**
	 *This process event is triggered on the menu.
	 */
	public boolean onOptionsMenuItemSelected(MenuItem item);
	
	/**
	 *Get each page to display the title.
	 */
	public String getActionBarTitle();
}