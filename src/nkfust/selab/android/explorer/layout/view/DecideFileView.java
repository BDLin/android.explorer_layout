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
package nkfust.selab.android.explorer.layout.view;

import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.model.ContentFragment;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * This class is display corresponding to file view on content page. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class DecideFileView {

	private RelativeLayout mRelative;
	private OtherFileView otherView;
	private VideoPlayerView video;
	private MusicPlayerView audio;
	private PhotoViewer photoView;
	private IFile mIFile;
	private List<IFile> mList, mRemoteIFileList;
	private List<String> mImagePaths;
	private Context mContext;
	private View contentView;
	
	private View.OnClickListener openOtherListener;
	private View.OnClickListener photoLeftButtonListener;
	private List<OnPageChangeListener> mPhotoGridImageChangeListeners;
	
	private ContentFragment mContentFragment;
	
	public DecideFileView() {
		mList = new ArrayList<IFile>();
		mRemoteIFileList = new ArrayList<IFile>();
		mImagePaths = new ArrayList<String>();
		mPhotoGridImageChangeListeners = new ArrayList<OnPageChangeListener>();
	}

	public DecideFileView(Context context, RelativeLayout relative) {
		this();
		mContext = context;
		mRelative = relative;
	}

	/**
	 * This function is display the corresponding to file view.
	 */
	public void showView(){
		if(contentView != null)
			mRelative.addView(contentView);
	}
	
	public Boolean isInitalView(){
		if(mRelative.getChildAt(0) instanceof InitialView)
			return true;
		
		return false;
	}
	
	/**
	 * This function is show initial view to content page.
	 */
	public void showInitialView(){
		mRelative.addView(new InitialView(mContext));
	}
	
	/**
	 * This function is show waiting view to content page.
	 */
	public void showWaittingView(){
		mRelative.addView(new WaittingView(mContext));
	}

	/**
	 * @param fileName The file name.
	 * @return The file extension.
	 */
	public String getFileExtension(String fileName){
		String[] token = URLUtils.guessContentType(fileName).split("/");
		return token[1];
	}

	/**
	 * @param fileName The file name.
	 * @return The file type.
	 */
	public String getFileType(String fileName){
		String[] token = URLUtils.guessContentType(fileName).split("/");
		return token[0];
	}
	
	/**
	 * First if use empty construct instantiates this class, can use this function set 
	 * browse view layout.
	 * @param context The Activity parent.
	 * @param relative A layout.
	 */
	public void setBrowseViewLayout(Context context, RelativeLayout relative){
		mContext = context;
		mRelative = relative;
	}
	
	/**
	 * Must be use this function set contentFragment 
	 * before using to instantiates this class object, 
	 * because some of view can use to contentFragment.
	 */
	public void setContentFragment(ContentFragment contentFragment){
		mContentFragment = contentFragment;
	}
	
	/**
	 * This function is set file that will be read.
	 * @param file Will be read the file.
	 */
	public void setFile(IFile file){
		mIFile = file;
		try {
			contentView = selectView(getFileType(mIFile.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setIFileList(List<IFile> list){
		mList = list;
	}
	
	public List<IFile> getIFileList(){
		return mList;
	}
	
	public void setRemoteIFileList(List<IFile> list){
		mRemoteIFileList = list;
	}
	
	public List<IFile> getRemoteIFileList(){
		return mRemoteIFileList;
	}
	
	public void setPhotoGridImagePaths(List<String> paths){
		mImagePaths = paths;
	}
	
	/**
	 * If file list have been change when playing music, 
	 * can use this function update to song list. 
	 */
	public void updateMusicList(){
		audio.updateMusicList(mList);
	}

	public VideoPlayerView getVideoView() {
		return video;
	}

	public MusicPlayerView getMusicView() {
		return audio;
	}
	
	public PhotoViewer getPhotoView(){
		return photoView;
	}
	
	public void setOpenOtherFileListener(View.OnClickListener listener){
		openOtherListener = listener;
	}
	
	public void setPhotoPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		photoView.setPagerChangeStateListener(listener);
	}
	
	public void addPhotoPagerChangeStateListener(OnPageChangeListener listener){
		mPhotoGridImageChangeListeners.add(listener);
	}
	
	public void clearPhotoPagerChangeStateListener(){
		mPhotoGridImageChangeListeners.clear();
	}
	
	public void setPhotoLeftButtonListener(View.OnClickListener listener){
		photoLeftButtonListener = listener;
	}
	
	public void setPhotoRightButtonListener(View.OnClickListener listener){
		photoView.setPhotoRightButtonListener(listener);
	}

	/**
	 * This function is release photo view used resource.
	 */
	public void ReleasePhotoViewer(){
		if(photoView != null){
			photoView.releasePhotoFragmentList();
			photoView = null;
		}
	}
	
	/**
	 * This function is release media player used resource.
	 */
	public void ReleaseMediaPlayer() {
		if (audio != null) {
			audio.endPlayer();
			audio = null;
		}
		if (video != null) {
			video.releasePlayer();
			video = null;
		}
	}

	private View selectView(String fileType){
		if(!fileType.equals("image"))
			ReleasePhotoViewer();
		if(!fileType.equals("audio"))
			ReleaseMediaPlayer();
		
		if (fileType.equals("audio")) {
			settingMusicPlayerView();
			return audio;
		} else if(fileType.equals("image")){
			settingPhotoViewr();
			photoView.setContentFragment(mContentFragment);
			return photoView;
		} else if (fileType.equals("video")) {
			video = new VideoPlayerView(mContext, mIFile);
			return video;
		} else {
			otherView = new OtherFileView(mContext, mIFile);
			otherView.setOnClickListener(openOtherListener);
			return otherView;
		}
	}

	private void settingMusicPlayerView(){
		if (audio == null)
			audio = new MusicPlayerView(mContext, mIFile, mList, mContentFragment.getFactory());
		else{
			updateMusicList();
			audio.playSong(mIFile);
		}
	}
	
	private void settingPhotoViewr(){
		if(mContentFragment.getTabFragment().getCurrentPageIndex() != 1){
			mImagePaths = new ArrayList<String>();
			for(IFile ifile : mList)
				try {
					mImagePaths.add(ifile.getUrl());
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		if(photoView == null)
			photoView = new PhotoViewer(mContext, mImagePaths, mIFile);
		else
			photoView.setDisplayPhoto(mImagePaths, mIFile);
		photoView.setPhotoLeftButtonListener(photoLeftButtonListener);
		photoView.setOnPageChangeListener(mPhotoGridImageChangeListeners);
	}
}