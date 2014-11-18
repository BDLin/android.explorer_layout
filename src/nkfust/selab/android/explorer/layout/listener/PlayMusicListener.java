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

public class PlayMusicListener implements OnClickListener {

	private ImageButton btnPlay;
	private  MediaPlayer mp;
	
	public PlayMusicListener(ImageButton btnPlay, MediaPlayer mp){
		this.btnPlay = btnPlay;
		this.mp = mp;
	}
	
	@Override
	public void onClick(View v) {
		/**
		 * Play button click event
		 * plays a song and changes button to pause image
		 * pauses a song and changes button to play image
		 * */
		// check for already playing
		if(mp.isPlaying()){
			if(mp!=null){
				mp.pause();
				// Changing button image to play button
				btnPlay.setImageResource(R.drawable.btn_play);
			}
		}else{
			// Resume song
			if(mp!=null){
				mp.start();
				// Changing button image to pause button
				btnPlay.setImageResource(R.drawable.btn_pause);
			}
		}
	}
}
