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
import nkfust.selab.android.explorer.layout.listener.CleanContentListener;
import nkfust.selab.android.explorer.layout.view.DecideFileView;
import nkfust.selab.android.explorer.layout.view.MusicPlayerView;
import nkfust.selab.android.explorer.layout.view.PhotoViewer;
import nkfust.selab.android.explorer.layout.view.VideoPlayerView;
import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
	private TabFragment mTabFragment;
	private IFileFactory mFactory;
	private IFile mIFile;
	
	private Boolean mReadArgument;
	
	private Object mTempObject;
	
	public ContentFragment(){
		decideFileView = new DecideFileView();
		mReadArgument = false;
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
		decideFileView.setContentFragment(this);
		init();
		setCleanContentdListener();
	}
		
	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		if (args != null && !mReadArgument) {
			((ActionBarActivity)getActivity()).getSupportActionBar().hide();
			((ActionBarActivity)getActivity()).getSupportFragmentManager()
				.beginTransaction().hide(TabFragment.getTabFragment()).commit();
			updateBrowseView(mIFile);
			mReadArgument = true;
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
			releaseMultiMedia();
		}
	}
	
	public void init(){
		browseFileSpace.removeAllViews();
		decideFileView.showInitialView();
		if(mTabFragment.getFrameLayout() == null)
			mIFile = null;
	}
	
	public void updateMusicList(){
		decideFileView.updateMusicList();
	}
	
	public void updateBrowseView(IFile file){
		mIFile = file;
		refreshBrowseView();
	}
	
	public void refreshBrowseView(){
		browseFileSpace.removeAllViews();
		decideFileView.setFile(mIFile);
		decideFileView.showView();
	}
	
	public void showWaittingView(){
		browseFileSpace.removeAllViews();
		decideFileView.showWaittingView();
	}
	
	public void releaseMultiMedia(){
		decideFileView.ReleaseMediaPlayer();
		decideFileView.ReleasePhotoViewer();
	}
	
	public void setReadArgument(Boolean bool){
		mReadArgument = bool;
	}

	public void setIFile(IFile file) {
		mIFile = file;
	}
	
	public void setFactory(IFileFactory factory){
		mFactory = factory;
	}

	public void setPhotoGridImagePaths(List<String> paths) {
		decideFileView.setPhotoGridImagePaths(paths);
	}

	public void setIFileList(List<IFile> list) {
		decideFileView.setIFileList(list);
	}
	

	public void setRemoteIFileList(List<IFile> list) {
		decideFileView.setRemoteIFileList(list);
	}
	
	public void setTabFragment(TabFragment tabFragment){
		mTabFragment = tabFragment;
		setCleanContentdListener();
	}
	
	public void setTempObject(Object obj){
		mTempObject = obj;
	}
	
	public Object getTempObject(){
		return mTempObject;
	}
	
	public IFileFactory getFactory(){
		return mFactory;
	}
	
	public IFile getCurrentIFile() {
		return mIFile;
	}
	
	public Boolean isInitalView(){
		return decideFileView.isInitalView();
	}
	
	public List<IFile> getIFileList(){
		return decideFileView.getIFileList();
	}
	
	public List<IFile> getRemoteIFileList(){
		return decideFileView.getRemoteIFileList();
	}
	
	public TabFragment getTabFragment(){
		return mTabFragment;
	}
	
	public DecideFileView getDecideFileView(){
		return decideFileView;
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
	
	public void addPhotoPagerChangeStateListener(OnPageChangeListener listener){
		decideFileView.addPhotoPagerChangeStateListener(listener);
	}
	
	public void clearPhotoPagerChangeStateListener(){
		decideFileView.clearPhotoPagerChangeStateListener();
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
	
	private void setCleanContentdListener(){
		if(mTabFragment != null)
			mTabFragment.addPageSelectedListener(new CleanContentListener(this));
	}
}