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

import java.util.ArrayList;
import java.util.HashMap;

import nkfust.selab.android.explorer.layout.model.MusicPlayerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PreviousOrNextListener implements OnClickListener {

	private ImageButton btnNext;
	private ImageButton btnPrevious;
	private MusicPlayerView player;
	private ArrayList<HashMap<String, String>> songsList;
	
	public PreviousOrNextListener(MusicPlayerView player, ArrayList<HashMap<String, String>> songsList, 
						ImageButton btnNext, ImageButton btnPrevious){
		this.player = player;
		this.songsList = songsList;
		this.btnNext = btnNext;
		this.btnNext = btnNext;
	}
	
	@Override
	public void onClick(View v) {
		if(v == btnNext){
			/**
			 * Next button click event
			 * Plays next song by taking currentSongIndex + 1
			 * */
			// check if next song is there or not
			if(MusicPlayerView.getCurrentSongIndex() < (songsList.size() - 1)){
				player.playSong(MusicPlayerView.getCurrentSongIndex() + 1);
			}else{
				// play first song
				player.playSong(0);
			}
		}else{
			/**
			 * Back button click event
			 * Plays previous song by currentSongIndex - 1
			 * */
			if(MusicPlayerView.getCurrentSongIndex() > 0){
				player.playSong(MusicPlayerView.getCurrentSongIndex() - 1);
			}else{
				// play last song
				player.playSong(songsList.size() - 1);
			}
		}
	}
}
