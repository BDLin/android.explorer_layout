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
package com.example.android.fragments;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         
        Log.i("MainActivity", "onCreate().......");
        setContentView(R.layout.news_articles);
        
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {
        	Log.i("MainActivity", "Is_Phone");
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HeadlinesFragment firstFragment = new HeadlinesFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }else{
        	Log.i("MainActivity", "Is_Tablet");
        	HeadlinesFragment headline = (HeadlinesFragment)getSupportFragmentManager().findFragmentById(R.id.headlines_fragment);
        	ArticleFragment article = (ArticleFragment)getSupportFragmentManager().findFragmentById(R.id.article_fragment);
        	
        	headline.addButtonToView(new RemoteListFragment(this, R.drawable.box_small_icon, article));
        	headline.addButtonToView(new LocalListFragment(this, R.drawable.download_folder_small_icon));
        	headline.addButtonToView(new PrefsFragment(this, R.drawable.setting_small_icon));
        }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if(newConfig.orientation ==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this,"¾î«Ì¼Ò¦¡(landscape)",Toast.LENGTH_SHORT).show();
        }else if(newConfig.orientation ==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this,"½Ý«Ì¼Ò¦¡(portrait)",Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("MainActivity", "onDestroy()...");
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	    Log.i("MainActivity", "onPause()...");
	}

	@Override
	protected void onResume() {
	    super.onResume();
	    Log.i("MainActivity", "onResume()...");
	}
 
	@Override
	protected void onStart() {
	    super.onStart();
	    Log.i("MainActivity", "onStart()...");
	}

	@Override
	protected void onStop() {
	    super.onStop();
	    Log.i("MainActivity", "onStop()...");
	}
    
}