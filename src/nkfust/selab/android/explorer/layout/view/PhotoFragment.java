/*
 * Copyright (C) 2013 Adam Huang
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
/*******************************
 * PhotoFragment
 *******************************/
package nkfust.selab.android.explorer.layout.view;

import nkfust.selab.android.explorer.layout.R;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;

import poisondog.android.image.ImageCache;
import poisondog.android.image.ImageFetcher;
import poisondog.android.image.RecyclingBitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * This class is a page of show photo.
 * <p>
 * This used to getItem() function in the FragmentStatePagerAdapter,
 * and must be use setPath function to set path of photo.
 * @author poisondog <poisondog@gmail.com>
 */
public class PhotoFragment extends Fragment {
	private static final String PATH = "path";
	private String mPath;
	private CoustomizedImageView mImage;
	private ImageFetcher mFetcher;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mPath = savedInstanceState.getString(PATH);
		}

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		try{
			FileObject cache = VFS.getManager().resolveFile(getActivity().getExternalCacheDir().getPath());
			mFetcher = new ImageFetcher(getActivity(), (int)(dm.widthPixels), (int)(dm.heightPixels), cache);
			mFetcher.setImageCache(new ImageCache(getActivity(), cache));
		}catch(FileSystemException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(PATH, mPath);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		FrameLayout fl = new FrameLayout(getActivity());
		ProgressBar pb = new ProgressBar(getActivity());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		pb.setLayoutParams(params);
		mImage = new CoustomizedImageView(getActivity());
		mImage.setMaxZoom(2f);
		mImage.setDrawingCacheEnabled(false);
		mFetcher.loadImage(mPath, mImage);

		fl.addView(pb);
		fl.addView(mImage);
		return fl;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (RecyclingBitmapDrawable.class.isInstance(mImage.getDrawable())) {
			((RecyclingBitmapDrawable) mImage.getDrawable()).setIsDisplayed(false);
		}
		mImage.setImageResource(R.drawable.file);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
	}
	
	public void setPath(String path) {
		mPath = path;
	}
}