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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.model.TabFragment;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.viewer.DocumentState;

public class DecideFileView {

	private LocalData local;
	private Context mContext;
	private RelativeLayout mRelative;
	private List<IFile> aList;
	private VideoPlayerView video;
	private MusicPlayerView audioPlayer;
	private PhotoViewer photoView;
	private View otherView;

	public DecideFileView(Context context, RelativeLayout relative) {
		mContext = context;
		mRelative = relative;
		aList = new ArrayList<IFile>();
	}

	public void showView() throws IOException {
		Log.i("DecideFile", "title:" + getFileSubtype(local.getName()));
		Log.i("DecideFile", "File name:" + local.getName());
		if (getFileType(local.getName()).equals("audio")) {
			ReleasePhotoViewer();
			if (audioPlayer == null)
				audioPlayer = new MusicPlayerView(mContext, local, aList);
			else{
				updateMusicList(aList);
				audioPlayer.playSong(local);
			}
			mRelative.addView(audioPlayer);
		} else {
			ReleaseMediaPlayer();
			if(getFileType(local.getName()).equals("image")){
				List<String> images = new ArrayList<String>();
				for(IFile ifile : aList)
					images.add(((LocalData)ifile).getUrl());
				if(photoView == null)
					photoView = new PhotoViewer(mContext, images, local.getName());
				else
					photoView.setCurrentItem(images, local.getName());
				mRelative.addView(photoView);
			} else {
				ReleasePhotoViewer();
				if (getFileType(local.getName()).equals("video")) {
					if (TabFragment.getFrameLayout() != null)
						TabFragment.getActionBarActivity().getSupportActionBar().hide();
					video = new VideoPlayerView(mContext, local);
					mRelative.addView(video);
				} else if (getFileSubtype(local.getName()).equals("pdf")) {
					InputStream is = local.getInputStream();
					int size = is.available();
					// create a listener for receiving provide pdf loading results
					SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
						@Override
						public void onLoadFinish(DocumentState.OPEN state) {}
					};
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
				} else if (getFileSubtype(local.getName()).equals("msword")) {
					HWPFDocument doc = new HWPFDocument(local.getInputStream());
					Range r = doc.getRange();
					String content = r.text();
					r.delete();
					TextView text = new TextView(mContext);
					text.setText(content);
					mRelative.addView(text);
				} else {
					otherView = getAnyFileView();
					mRelative.addView(otherView);
				}
			}
		}
	}

	private RelativeLayout getAnyFileView() {
		RelativeLayout innerRelative = new RelativeLayout(mContext);
		innerRelative.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));
		
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(R.drawable.file_128);
		imageView.setId(55688);
		
		RelativeLayout.LayoutParams trelativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		trelativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		trelativeParams.addRule(RelativeLayout.BELOW, imageView.getId());
		TextView text = new TextView(mContext);
		text.setText(local.getName());
		
		innerRelative.addView(imageView, relativeParams);
		innerRelative.addView(text, trelativeParams);
		return innerRelative;
	}

	public String getFileSubtype(String fileName) {
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[1];
	}

	public String getFileType(String fileName) {
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[0];
	}
	
	public void setFile(LocalData local){
		this.local = local;
	}

	public void setIFileList(List<IFile> list){
		aList.clear();
		aList.addAll(list);
	}
	
	public List<IFile> getIFileList(){
		return aList;
	}
	
	public void updateMusicList(List<IFile> list){
		audioPlayer.updateMusicList(list);
	}

	public VideoPlayerView getVideoView() {
		return video;
	}

	public MusicPlayerView getMusicView() {
		return audioPlayer;
	}
	
	public PhotoViewer getPhotoView(){
		return photoView;
	}
	
	public void setOpenOtherFileListener(View.OnClickListener listener){
		if(otherView != null)
			otherView.setOnClickListener(listener);
	}
	
	public void setPhotoPagerChangeStateListener(ViewPager.SimpleOnPageChangeListener listener){
		photoView.setPagerChangeStateListener(listener);
	}
	
	public void setPhotoLeftButtonListener(View.OnClickListener listener){
		photoView.setPhotoLeftButtonListener(listener);
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
		if (audioPlayer != null) {
			audioPlayer.endPlayer();
			audioPlayer = null;
		}
		if (video != null) {
			video.releasePlayer();
			video = null;
		}
	}
}