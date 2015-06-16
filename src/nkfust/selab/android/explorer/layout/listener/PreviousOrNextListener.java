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
package nkfust.selab.android.explorer.layout.listener;

import java.util.List;
import java.util.Random;

import nkfust.selab.android.explorer.layout.view.MusicPlayerView;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
/**
 * This class function is play previous or next song 
 * when playing music and press previous or next button.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class PreviousOrNextListener implements OnClickListener {

	private ImageButton mNextButton;
	private ImageButton mPreviousButton;
	private MusicPlayerView mPlayer;

	public PreviousOrNextListener(MusicPlayerView player, ImageButton btnNext,
			ImageButton btnPrevious) {
		mPlayer = player;
		mNextButton = btnNext;
		mPreviousButton = btnPrevious;
	}

	@Override
	public void onClick(View v) {
		
		List<IFile>arrayList = mPlayer.getMusicList();
		List<IFile>songList = mPlayer.getSongList();
		
		if (ShuffleOrRepeatListener.isShuffle()) {
			// shuffle is on - play a random song
			Random rand = new Random();
			int currentSongIndex = rand.nextInt(songList.size());
			mPlayer.playSong((LocalData) songList.get(currentSongIndex));
		} else {
			if (v == mNextButton) {
				// check if next song is there or not
				for (int i = mPlayer.getCurrentSongIndex(); i < arrayList
						.size(); i++)
					if (i != (arrayList.size() - 1) && URLUtils.guessContentType(
									(((LocalData) arrayList.get(i + 1)).getName()))
									                       .startsWith("audio/")) {
						mPlayer.playSong((LocalData) arrayList.get(i + 1));
						mPlayer.setCurrentSongIndex(i + 1);
						break;
					} else if (i == (arrayList.size() - 1)) {
						i = -2;
					}
			} else {

				for (int i = mPlayer.getCurrentSongIndex(); i >= 0; i--)
					if (i != 0 && URLUtils.guessContentType(
									(((LocalData) arrayList.get(i - 1)).getName()))
											               .startsWith("audio/")) {
						mPlayer.playSong((LocalData) arrayList.get(i - 1));
						mPlayer.setCurrentSongIndex(i - 1);
						break;
					} else if (i == 0) {
						i = arrayList.size() + 1;
					}
			}// End of inner if-else
		}// End of outer if-else
	}// End of onClick function
}// End of PreviousOrNextListener class
