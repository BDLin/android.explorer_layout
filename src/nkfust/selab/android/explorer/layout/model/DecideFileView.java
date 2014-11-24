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
package nkfust.selab.android.explorer.layout.model;

import java.io.IOException;
import java.io.InputStream;

import poisondog.net.URLUtils;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.viewer.DocumentState;

public class DecideFileView {

	private LocalData local;
	private Context context;
	private RelativeLayout relative;
	private static VideoPlayerView video;
	private static MusicPlayerView audioPlayer;

	private Fragment tabFrag, contentFrag;
	private int position;

	public DecideFileView(Context context, LocalData local,
			RelativeLayout relative, int position, Fragment tabFrag,
			Fragment contentFrag) {
		this.local = local;
		this.context = context;
		this.relative = relative;
		this.position = position;
		this.tabFrag = tabFrag;
		this.contentFrag = contentFrag;
	}

	public void setView() throws IOException {
		ReleaseMediaPlayer();
		if (getFileSubtype(local.getName()).equals("pdf")) {
			InputStream is = local.getInputStream();
			int size = is.available();
			if (size > 0) {
				byte[] data = new byte[size];
				is.read(data);
				open(data);
			}
			is.close();
		} else if (getFileType(local.getName()).equals("audio")) {
			audioPlayer = new MusicPlayerView(context, local, position);
			relative.addView(audioPlayer);
		} else if (getFileType(local.getName()).equals("video")) {
			video = new VideoPlayerView(context, local, tabFrag, contentFrag);
			relative.addView(video);
		} else {
			TextView text = new TextView(context);
			text.setText(local.getName());
			relative.addView(text);
		}
	}

	public static void ReleaseMediaPlayer() {
		if (audioPlayer != null) {
			audioPlayer.endPlayer();
			audioPlayer = null;
		}
		if (video != null) {
			video.releasePlayer();
			video = null;
		}
	}
	
	public String getFileSubtype(String fileName) {
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[1];
	}

	public String getFileType(String fileName) {
		String[] token = URLUtils.guessContentType(local.getName()).split("/");
		return token[0];
	}

	// create a listener for receiving provide pdf loading results
	SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
		@Override
		public void onLoadFinish(DocumentState.OPEN state) {
		}
	};

	public void open(byte[] data) {
		// pdfviewer create.
		SimpleDocumentReader viewer = SimpleReaderFactory.createSimpleViewer(
				tabFrag.getActivity(), m_listener);
		// pdf data load.
		relative.addView(viewer.getReaderView());
		viewer.openData(data, data.length, "");
	}

	public static VideoPlayerView getVideoView() {
		return video;
	}
}
