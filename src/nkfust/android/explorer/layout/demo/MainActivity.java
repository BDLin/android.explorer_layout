/*
 * Copyright (C) 2012 The Android Open Source Project
 * Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.TabFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articles);

		TabFragment headline = (TabFragment) getSupportFragmentManager()
				.findFragmentById(R.id.headlines_fragment);
		ContentFragment article = (ContentFragment) getSupportFragmentManager()
				.findFragmentById(R.id.article_fragment);

		headline.addButtonToView(new SdcardListFragment(this,
				R.drawable.folder_remote, article, Environment
						.getExternalStorageDirectory().getAbsolutePath(),
				headline));
		headline.addButtonToView(new LocalListFragment(this,
				R.drawable.download_folder_small_icon));
		headline.addButtonToView(new PrefsFragment(this,
				R.drawable.android_settings));
	}// End of onCreate

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}// End of onConfigurationChanged function
}// End of MainActivity