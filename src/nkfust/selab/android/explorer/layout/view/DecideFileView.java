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
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class DecideFileView {

	private RelativeLayout mRelative;
	private OtherFileView otherView;
	private VideoPlayerView video;
	private MusicPlayerView audio;
	private PhotoViewer photoView;
	private IFile mIFile;
	private List<IFile> aList, mRemoteIFileList;
	private List<String> mImagePaths;
	private Context mContext;
	private View contentView;
	
	private View.OnClickListener openOtherListener;
	private View.OnClickListener photoLeftButtonListener;
	private List<OnPageChangeListener> mPhotoGridImageChangeListeners;
	
	private ContentFragment mContentFragment;
	
	public DecideFileView() {
		aList = new ArrayList<IFile>();
		mRemoteIFileList = new ArrayList<IFile>();
		mImagePaths = new ArrayList<String>();
		mPhotoGridImageChangeListeners = new ArrayList<OnPageChangeListener>();
	}

	public DecideFileView(Context context, RelativeLayout relative) {
		this();
		mContext = context;
		mRelative = relative;
	}

	public void showView(){
		try {
			Log.i("DecideFile", "title:" + getFileSubtype(mIFile.getName()));
			Log.i("DecideFile", "File name:" + mIFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(contentView != null)
			mRelative.addView(contentView);
	}
	
	public Boolean isInitalView(){
		if(mRelative.getChildAt(0) instanceof InitialView)
			return true;
		
		return false;
	}
	
	public void showInitialView(){
		mRelative.addView(new InitialView(mContext));
	}
	
	public void showWaittingView(){
		mRelative.addView(new WaittingView(mContext));
	}

	public String getFileSubtype(String fileName) throws Exception {
		String[] token = URLUtils.guessContentType(mIFile.getName()).split("/");
		return token[1];
	}

	public String getFileType(String fileName) throws Exception {
		String[] token = URLUtils.guessContentType(mIFile.getName()).split("/");
		return token[0];
	}
	
	public void setBrowseViewLayout(Context context, RelativeLayout relative){
		mContext = context;
		mRelative = relative;
	}
	
	public void setContentFragment(ContentFragment contentFragment){
		mContentFragment = contentFragment;
	}
	
	public void setFile(IFile file){
		mIFile = file;
		try {
			contentView = selectView(getFileType(mIFile.getName()),
					getFileSubtype(mIFile.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setIFileList(List<IFile> list){
		aList = list;
	}
	
	public List<IFile> getIFileList(){
		return aList;
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
	
	public void updateMusicList(){
		audio.updateMusicList(aList);
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

	public void ReleasePhotoViewer(){
		if(photoView != null){
			photoView.releasePhotoFragmentList();
			photoView = null;
		}
	}
	
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

	private View selectView(String fileType, String fileSubType){
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
		} 
//		else if (fileSubType.equals("pdf")) {
//			settingPDFViewer();
//			return null;
//		} 
		else {
			otherView = new OtherFileView(mContext, mIFile);
			otherView.setOnClickListener(openOtherListener);
			return otherView;
		}
	}

	private void settingMusicPlayerView(){
		if (audio == null)
			audio = new MusicPlayerView(mContext, mIFile, aList, mContentFragment.getFactory());
		else{
			updateMusicList();
			audio.playSong(mIFile);
		}
	}
	
//	private void settingPDFViewer(){
//		// create a listener for receiving provide pdf loading results
//		SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
//			@Override
//			public void onLoadFinish(DocumentState.OPEN state) {}
//		};
//		try {
//			InputStream is = localFile.getInputStream();
//			int size = is.available();
//			if (size > 0) {
//				byte[] data = new byte[size];
//				is.read(data);
//				// pdfviewer create.
//				SimpleDocumentReader viewer = SimpleReaderFactory
//						.createSimpleViewer(TabFragment.getTabFragment()
//								.getActivity(), m_listener);
//				// pdf data load.
//				mRelative.addView(viewer.getReaderView());
//				viewer.openData(data, data.length, "");
//			}
//			is.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private void settingPhotoViewr(){
		if(mContentFragment.getTabFragment().getCurrentPageIndex() != 1){
			mImagePaths = new ArrayList<String>();
			for(IFile ifile : aList)
				try {
					mImagePaths.add(ifile.getUrl());
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		String fileName = null;
		try {
			fileName = mIFile.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(photoView == null)
			photoView = new PhotoViewer(mContext, mImagePaths, fileName);
		else
			photoView.setCurrentItem(mImagePaths, fileName);
		photoView.setPhotoLeftButtonListener(photoLeftButtonListener);
		photoView.setOnPageChangeListener(mPhotoGridImageChangeListeners);
	}
}