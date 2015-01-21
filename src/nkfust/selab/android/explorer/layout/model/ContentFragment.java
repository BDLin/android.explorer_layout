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
package nkfust.selab.android.explorer.layout.model;

import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.view.DecideFileView;
import nkfust.selab.android.explorer.layout.view.MusicPlayerView;
import nkfust.selab.android.explorer.layout.view.PhotoViewer;
import nkfust.selab.android.explorer.layout.view.VideoPlayerView;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
/**
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class ContentFragment extends Fragment {

	private RelativeLayout browseFileSpace;
	private DecideFileView decideFileView;
	private LocalData localFile;
	
	public ContentFragment(){
		decideFileView = new DecideFileView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		browseFileSpace = (RelativeLayout) view.findViewById(R.id.relative_layout);
		decideFileView.setBrowseViewLayout(getActivity(), browseFileSpace);
	}
		
	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		if (args != null) {
			((ActionBarActivity)getActivity()).getSupportActionBar().hide();
			((ActionBarActivity)getActivity()).getSupportFragmentManager()
				.beginTransaction().hide(TabFragment.getTabFragment()).commit();
			updateArticleView(localFile);
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (decideFileView != null && decideFileView.getVideoView() != null)
			decideFileView.getVideoView().stop();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		if(decideFileView != null){
			decideFileView.ReleaseMediaPlayer();
			decideFileView.ReleasePhotoViewer();
		}
	}
	
	public void updateArticleView(IFile file){
		clean();
		localFile = (LocalData) file;
		decideFileView.setFile(localFile);
		decideFileView.showView();
	}// End of updateArticleView function

	public void setIFile(IFile file) {
		localFile = (LocalData) file;
	}
	
	public List<IFile> getIFileList(){
		return decideFileView.getIFileList();
	}
	
	public void setIFileList(List<IFile> list) {
		decideFileView.setIFileList(list);
	}
	
	public void clean(){
		browseFileSpace.removeAllViews();
	}
	
	public void updateMusicList(){
		decideFileView.updateMusicList();
	}
	
	public VideoPlayerView getVideoView(){
		return decideFileView.getVideoView();
	}
	
	public MusicPlayerView getMusicView(){
		if(decideFileView != null)
			return decideFileView.getMusicView();
		
		return null;
	}
	
	public PhotoViewer getPhotoView(){
		return decideFileView.getPhotoView();
	}
	
	public void setOpenOtherFileListener(View.OnClickListener listener){
		decideFileView.setOpenOtherFileListener(listener);
	}
	
	public void setPhotoPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		decideFileView.setPhotoPagerChangeStateListener(listener);
	}
	
	public void setPhotoLeftButtonListener(View.OnClickListener listener){
		decideFileView.setPhotoLeftButtonListener(listener);
	}
	
	public void setPhotoRightButtonListener(View.OnClickListener listener){
		decideFileView.setPhotoRightButtonListener(listener);
	}
}