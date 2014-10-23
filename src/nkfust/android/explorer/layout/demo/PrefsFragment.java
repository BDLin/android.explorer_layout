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
package nkfust.android.explorer.layout.demo;

import nkfust.android.explorer.layout.R;
import nkfust.android.explorer.layout.modle.CustomizeImageButton;
import nkfust.android.explorer.layout.modle.TabView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import android.widget.ImageButton;

public class PrefsFragment extends PreferenceFragment implements TabView {

	private CustomizeImageButton settingBtn;

	public PrefsFragment() {}

	public PrefsFragment(Context context, int img_id) {
		settingBtn = new CustomizeImageButton(context, img_id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}

	public ImageButton getBtn() {
		return settingBtn.getButton();
	}
}