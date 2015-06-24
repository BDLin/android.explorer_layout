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
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
/**
 * This class function is repeat or shuffle play music 
 * when playing music.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class ShuffleOrRepeatListener implements OnClickListener {

	private static boolean isShuffle = false;
	private static boolean isRepeat = false;
	private Context mContext;
	private ImageButton mRepeatButton;
	private ImageButton mShuffleButton;

	
	/**
	 * @param context		The activity parent.
	 * @param btnRepeat		The repeat button.
	 * @param btnShuffle	The shuffle button.
	 */
	public ShuffleOrRepeatListener(Context context, ImageButton btnRepeat, ImageButton btnShuffle) {
		mContext = context;
		mRepeatButton = btnRepeat;
		mShuffleButton = btnShuffle;
	}

	@Override
	public void onClick(View v) {
		if (v == mShuffleButton) {
			/**
			 * Button Click event for Shuffle button Enables shuffle flag to
			 * true
			 * */
			if (isShuffle) {
				isShuffle = false;
				Toast.makeText(mContext, "Shuffle is OFF", Toast.LENGTH_SHORT)
						.show();
				mShuffleButton.setImageResource(R.drawable.btn_shuffle);
			} else {
				// make repeat to true
				isShuffle = true;
				Toast.makeText(mContext, "Shuffle is ON", Toast.LENGTH_SHORT)
						.show();
				// make shuffle to false
				isRepeat = false;
				mShuffleButton.setImageResource(R.drawable.btn_shuffle_focused);
				mRepeatButton.setImageResource(R.drawable.btn_repeat);
			}
		} else {
			/**
			 * Button Click event for Repeat button Enables repeat flag to true
			 * */
			if (isRepeat) {
				isRepeat = false;
				Toast.makeText(mContext, "Repeat is OFF", Toast.LENGTH_SHORT)
						.show();
				mRepeatButton.setImageResource(R.drawable.btn_repeat);
			} else {
				// make repeat to true
				isRepeat = true;
				Toast.makeText(mContext, "Repeat is ON", Toast.LENGTH_SHORT)
						.show();
				// make shuffle to false
				isShuffle = false;
				mRepeatButton.setImageResource(R.drawable.btn_repeat_focused);
				mShuffleButton.setImageResource(R.drawable.btn_shuffle);
			}
		}
	}

	public static Boolean isRepeat() {
		return isRepeat;
	}

	public static Boolean isShuffle() {
		return isShuffle;
	}
}