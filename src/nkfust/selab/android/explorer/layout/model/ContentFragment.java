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
import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.view.DecideFileView;
import nkfust.selab.android.explorer.layout.view.MusicPlayerView;
import nkfust.selab.android.explorer.layout.view.VideoPlayerView;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ContentFragment extends Fragment {

	private RelativeLayout relative;
	private LocalData local;
	private DecideFileView decideFileView;
	private List<IFile> aList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		aList = new ArrayList<IFile>();
		relative = (RelativeLayout) view.findViewById(R.id.relative_layout);
		decideFileView = new DecideFileView(getActivity(), relative);
	}
		
	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		if (args != null) {
			TabFragment.getActionBarActivity().getSupportFragmentManager().
			beginTransaction().hide(TabFragment.getTabFragment()).commit();
			try {
				updateArticleView(local);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	public void updateArticleView(IFile file) throws IOException {
		relative.removeAllViews();
		local = (LocalData) file;
		decideFileView.setFile(local);
		decideFileView.setIFileList(aList);
		decideFileView.showView();
	}// End of updateArticleView function
	
	public void setOpenOtherFileListener(View.OnClickListener listener){
		decideFileView.setOpenOtherFileListener(listener);
	}

	public void setIFile(IFile file) {
		local = (LocalData) file;
	}
	
	public void setIFileList(List<IFile> list) {
		aList = list;
	}
	
	public void updateIFileList(List<IFile> list){
		decideFileView.updateMusicList(list);
	}
	
	public VideoPlayerView getVideoView(){
		return decideFileView.getVideoView();
	}
	
	public MusicPlayerView getMusicView(){
		if(decideFileView != null)
			return decideFileView.getMusicView();
		
		return null;
	}
}