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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.model.TabFragment;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.viewer.DocumentState;
/**
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class DecideFileView {

	private RelativeLayout mRelative;
	private OtherFileView otherView;
	private VideoPlayerView video;
	private MusicPlayerView audio;
	private PhotoViewer photoView;
	private LocalData localFile;
	private List<IFile> aList, mRemoteIFileList;
	private Context mContext;
	private View contentView;
	
	private View.OnClickListener openOtherListener;
	private View.OnClickListener photoLeftButtonListener;
	
	public DecideFileView() {
		aList = new ArrayList<IFile>();
		mRemoteIFileList = new ArrayList<IFile>();
	}

	public DecideFileView(Context context, RelativeLayout relative) {
		this();
		mContext = context;
		mRelative = relative;
	}

	public void showView(){
		Log.i("DecideFile", "title:" + getFileSubtype(localFile.getName()));
		Log.i("DecideFile", "File name:" + localFile.getName());
		if(contentView != null)
			mRelative.addView(contentView);
	}

	public String getFileSubtype(String fileName) {
		String[] token = URLUtils.guessContentType(localFile.getName()).split("/");
		return token[1];
	}

	public String getFileType(String fileName) {
		String[] token = URLUtils.guessContentType(localFile.getName()).split("/");
		return token[0];
	}
	
	public void setBrowseViewLayout(Context context, RelativeLayout relative){
		mContext = context;
		mRelative = relative;
	}
	
	public void setFile(LocalData local){
		localFile = local;
		contentView = selectView(getFileType(localFile.getName()),
				getFileSubtype(localFile.getName()));
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
			return photoView;
		} else if (fileType.equals("video")) {
			video = new VideoPlayerView(mContext, localFile);
			return video;
		} else if (fileSubType.equals("pdf")) {
			settingPDFViewer();
			return null;
		} else {
			otherView = new OtherFileView(mContext, localFile);
			otherView.setOnClickListener(openOtherListener);
			return otherView;
		}
	}

	private void settingMusicPlayerView(){
		if (audio == null)
			audio = new MusicPlayerView(mContext, localFile, aList);
		else{
			updateMusicList();
			audio.playSong(localFile);
		}
	}
	
	private void settingPDFViewer(){
		// create a listener for receiving provide pdf loading results
		SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
			@Override
			public void onLoadFinish(DocumentState.OPEN state) {}
		};
		try {
			InputStream is = localFile.getInputStream();
			int size = is.available();
			if (size > 0) {
				byte[] data = new byte[size];
				is.read(data);
				// pdfviewer create.
				SimpleDocumentReader viewer = SimpleReaderFactory
						.createSimpleViewer(TabFragment.getTabFragment()
								.getActivity(), m_listener);
				// pdf data load.
				mRelative.addView(viewer.getReaderView());
				viewer.openData(data, data.length, "");
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void settingPhotoViewr(){
		List<String> images = new ArrayList<String>();
		for(IFile ifile : aList)
			try {
				images.add(ifile.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(photoView == null)
			photoView = new PhotoViewer(mContext, images, localFile.getName());
		else
			photoView.setCurrentItem(images, localFile.getName());
		photoView.setPhotoLeftButtonListener(photoLeftButtonListener);
	}
}