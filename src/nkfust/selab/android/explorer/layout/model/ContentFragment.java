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

import java.io.IOException;
import java.io.InputStream;

import nkfust.selab.android.explorer.layout.R;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.viewer.DocumentState;

public class ContentFragment extends Fragment {

	private MusicPlayerView audioPlayer;
	private VideoPlayerView video ;
	private RelativeLayout relative;
	private Fragment frag;
	private	LocalData local;
	private int position;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		relative = (RelativeLayout) getActivity().findViewById(R.id.relative_layout);
	}
	
	@Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null){
        	position = args.getInt("position");
			try {
				updateArticleView(local);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	
	public void setTabFragment(Fragment frag){
		this.frag = frag;
	}
	
	public void setIFile (IFile file){
		local = (LocalData)file;
	}
	
	public void setPosition(int position){
		this.position = position;
	}
	
	public void updateArticleView(IFile file) throws IOException {
		relative.removeAllViews();
		local = (LocalData)file;
		InputStream is = local.getInputStream();
		int size = is.available();
		Log.i("ContentFragment", "File Size:" + size);
		Log.i("ContentFragment", "local Url:" + local.getUrl());
		ReleaseMediaPlayer();
        if(getFileSubtype(local.getName()).equals("pdf")){
        	if (size > 0) {
            	byte[] data = new byte[size];
            	is.read(data);
            	open(data);
            }
            is.close();
        }else if(getFileType(local.getName()).equals("audio")){
        	audioPlayer = new MusicPlayerView(getActivity(), local, position);
        	relative.addView(audioPlayer);
        }else if(getFileType(local.getName()).equals("video")){
//        	VideoView video = new VideoView(getActivity());
//        	video.setVideoPath(local.getUrl().replace("file:", ""));
//        	MediaController vidControl = new MediaController(getActivity());
//        	vidControl.setAnchorView(video);
//        	video.setMediaController(vidControl);
//        	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        	params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        	video.setLayoutParams(params);
//        	video.requestFocus();
//        	video.start();
        	video = new VideoPlayerView(getActivity(), local, frag);
        	relative.addView(video);
        }else{
        	TextView text = new TextView(getActivity());
        	text.setText(local.getName());
        	relative.addView(text);
        }
	}//End of updateArticleView function
	
	public void ReleaseMediaPlayer(){
		if(audioPlayer != null){
    		audioPlayer.endPlayer();
    		audioPlayer = null;
    	}
		if(video != null){
			video.releasePlayer();
			video = null;
		}
	}
	
	public String getFileSubtype(String fileName){
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[1];
	}
	
	public String getFileType(String fileName){
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[0];
	}
	
	public void open(byte[] data) {
		// pdfviewer create.
		SimpleDocumentReader viewer = SimpleReaderFactory.createSimpleViewer(this.getActivity(), m_listener);
		// pdf data load.
		relative.addView(viewer.getReaderView());
		viewer.openData(data, data.length, "");
	}
 
	// create a listener for receiving provide pdf loading results
	SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
 
		@Override
		public void onLoadFinish(DocumentState.OPEN state) {
		}
	};
}