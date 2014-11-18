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

import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ForwardOrBackwardListener implements OnClickListener {

	private ImageButton btnForward;
	private ImageButton btnBackward;
	private int seekForwardTime = 5000; // 5000 milliseconds
	private int seekBackwardTime = 5000; // 5000 milliseconds
	private  MediaPlayer mp;
	
	public ForwardOrBackwardListener(ImageButton btnForward, ImageButton btnBackward, MediaPlayer mp){
		this.btnForward = btnForward;
		this.btnBackward = btnBackward;
		this.mp = mp;
	}
	
	@Override
	public void onClick(View v) {
		if(v == btnForward){
			/**
			 * Forward button click event
			 * Forwards song specified seconds
			 * */
			// get current song position				
			int currentPosition = mp.getCurrentPosition();
			// check if seekForward time is lesser than song duration
			if(currentPosition + seekForwardTime <= mp.getDuration()){
				// forward song
				mp.seekTo(currentPosition + seekForwardTime);
			}else{
				// forward to end position
				mp.seekTo(mp.getDuration());
			}
		}else{
			/**
			 * Backward button click event
			 * Backward song to specified seconds
			 * */
			// get current song position				
			int currentPosition = mp.getCurrentPosition();
			// check if seekBackward time is greater than 0 sec
			if(currentPosition - seekBackwardTime >= 0){
				// forward song
				mp.seekTo(currentPosition - seekBackwardTime);
			}else{
				// backward to starting position
				mp.seekTo(0);
			}
		}
	}
}
