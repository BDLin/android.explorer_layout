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

import nkfust.selab.android.explorer.layout.R;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
/**
 * This class function is change play button image when playing music and press play button.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class PlayMusicListener implements OnClickListener {

	private ImageButton mPlayButton;
	private MediaPlayer mPlayer;

	public PlayMusicListener(ImageButton btnPlay, MediaPlayer mp) {
		mPlayButton = btnPlay;
		mPlayer = mp;
	}

	@Override
	public void onClick(View v) {
		/**
		 * Play button click event plays a song and changes button to pause
		 * image pauses a song and changes button to play image
		 * */
		// check for already playing
		if (mPlayer.isPlaying()) {
			if (mPlayer != null) {
				mPlayer.pause();
				// Changing button image to play button
				mPlayButton.setImageResource(R.drawable.btn_play);
			}
		} else {
			// Resume song
			if (mPlayer != null) {
				mPlayer.start();
				// Changing button image to pause button
				mPlayButton.setImageResource(R.drawable.btn_pause);
			}
		}
	}
}