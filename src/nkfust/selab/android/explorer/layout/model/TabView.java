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
 * 如有想要加進TabFragment的內容必須實作此類別。
 * If want to display the page , must implementes this interface.
 * 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public interface TabView {
	/**
	 *它是Tab上面的button，可點選它切換tabView。
	 *@return The index button.  
	 */
	public ImageButton getIndexButton();
	
	/**
	 *這是實作Tab裡的內容。
	 */
	public Fragment getFragment();
	
	/**
	 *個別tabview 裡 menu 的xml檔。 
	 *@return The id for menu XML file.
	 */
	public int getMenuResource();
	
	/**
	 *這是判斷在menu按了哪個項目，且要做什麼事寫在裡面。
	 *@param item	在menu裡所點擊的項目。
	 */
	public boolean onOptionsMenuItemSelected(MenuItem item);
	
	/**
	 *取得該tabView所要顯示的title。
	 */
	public String getActionBarTitle();
}