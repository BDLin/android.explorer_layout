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
package nkfust.selab.android.explorer.layout.demo;

import nkfust.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.model.ContentFragment;
import nkfust.selab.android.explorer.layout.model.TabFragment;
import poisondog.string.ExtractParentUrl;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	private TabFragment tabView;
	private ContentFragment article;
	private SdcardListFragment sdFrag;
	private SdcardListFragment offFrag;
	private PrefsFragment presFrag;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_articles);

		tabView = (TabFragment) getSupportFragmentManager().findFragmentById(
				R.id.headlines_fragment);
		article = (ContentFragment) getSupportFragmentManager()
				.findFragmentById(R.id.article_fragment);

		sdFrag = new SdcardListFragment(this, R.drawable.folder_remote,
				article, Environment.getExternalStorageDirectory()
						.getAbsolutePath());
		offFrag = new SdcardListFragment(this,
				R.drawable.download_folder_small_icon, article, Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/Download");
		presFrag = new PrefsFragment(this, R.drawable.android_settings);
	}// End of onCreate
	
	@Override
	public void onStart (){
		super.onStart();
		tabView.addTabView(sdFrag);
		tabView.addTabView(offFrag);
		tabView.addTabView(presFrag);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}// End of onConfigurationChanged function

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.title_file_list, menu);
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.create_folder:
			new CreateFolder(this, sdFrag).DisplayDialog();
			break;

		case R.id.sort_by_name:
			sdFrag.doSortByName();
			sdFrag.reloadList();
			break;

		case R.id.sort_by_time:
			sdFrag.doSortByTime();
			sdFrag.reloadList();
			break;

		default:
			break;
		}// End of switch-case
		return true;
	}// End of onOptionsItemSelected

	public void onBackPressed() {
		if (sdFrag.getBtn().getAlpha() == 1.0) {
			if (sdFrag.isEqualsRootPath())
				super.onBackPressed();
			else
				try {
					sdFrag.setAdapter(new ExtractParentUrl().process(sdFrag
							.getCurrentPath()));
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else if (offFrag.getBtn().getAlpha() == 1.0) {
			if (offFrag.isEqualsRootPath())
				super.onBackPressed();
			else
				try {
					offFrag.setAdapter(new ExtractParentUrl().process(offFrag
							.getCurrentPath()));
				} catch (Exception e) {
					e.printStackTrace();
				}
		} else if (presFrag.getBtn().getAlpha() == 1.0)
			super.onBackPressed();
	}// End of onBackPressed
}// End of MainActivity