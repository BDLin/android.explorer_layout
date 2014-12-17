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
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		relative = (RelativeLayout) getActivity().findViewById(
				R.id.relative_layout);
	}

	@Override
	public void onStart() {
		super.onStart();
		Bundle args = getArguments();
		if (args != null) {
			try {
				updateArticleView(local);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateArticleView(IFile file) throws IOException {
		relative.removeAllViews();
		local = (LocalData) file;
		DecideFileView decideFileView = new DecideFileView(getActivity(),
				local, relative);
		decideFileView.showView();
	}// End of updateArticleView function

	public void setIFile(IFile file) {
		local = (LocalData) file;
	}

	public void setMusicList(List<IFile> list) {
		MusicPlayerView.setMusicList(list);
	}
}