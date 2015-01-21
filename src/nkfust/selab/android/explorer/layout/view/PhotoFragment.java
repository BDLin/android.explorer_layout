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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class PhotoFragment extends Fragment {
	private static final String PATH = "path";
	private String path;
	private CoustomizedImageView image;
	private ImageFetcher aFetcher;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			path = savedInstanceState.getString(PATH);
		}

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		try{
			FileObject cache = VFS.getManager().resolveFile(getActivity().getExternalCacheDir().getPath());
			aFetcher = new ImageFetcher(getActivity(), (int)(dm.widthPixels), (int)(dm.heightPixels), cache);
			aFetcher.setImageCache(new ImageCache(getActivity(), cache));
		}catch(FileSystemException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(PATH, path);
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
		image = new CoustomizedImageView(getActivity());
		image.setMaxZoom(2f);
		image.setDrawingCacheEnabled(false);
		aFetcher.loadImage(path, image);

		fl.addView(pb);
		fl.addView(image);
		return fl;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (RecyclingBitmapDrawable.class.isInstance(image.getDrawable())) {
			((RecyclingBitmapDrawable) image.getDrawable()).setIsDisplayed(false);
		}
		image.setImageResource(R.drawable.file);
		Log.i("PhotoFragment", "onDestoryView!!!");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("PhotoFragment", "onDestory!!!");
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		Log.i("PhotoFragment", "onDetach!!!");
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}