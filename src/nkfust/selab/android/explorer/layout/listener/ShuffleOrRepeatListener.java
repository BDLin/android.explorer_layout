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

public class ShuffleOrRepeatListener implements OnClickListener {

	private static boolean isShuffle;
	private static boolean isRepeat;
	private Context context;
	private ImageButton btnRepeat;
	private ImageButton btnShuffle;

	public ShuffleOrRepeatListener(boolean shuffle, boolean repeat,
			Context context, ImageButton btnRepeat, ImageButton btnShuffle) {
		isShuffle = shuffle;
		isRepeat = repeat;
		this.context = context;
		this.btnRepeat = btnRepeat;
		this.btnShuffle = btnShuffle;
	}

	@Override
	public void onClick(View v) {
		if (v == btnShuffle) {
			/**
			 * Button Click event for Shuffle button Enables shuffle flag to
			 * true
			 * */
			if (isShuffle) {
				isShuffle = false;
				Toast.makeText(context, "Shuffle is OFF", Toast.LENGTH_SHORT)
						.show();
				btnShuffle.setImageResource(R.drawable.btn_shuffle);
			} else {
				// make repeat to true
				isShuffle = true;
				Toast.makeText(context, "Shuffle is ON", Toast.LENGTH_SHORT)
						.show();
				// make shuffle to false
				isRepeat = false;
				btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
				btnRepeat.setImageResource(R.drawable.btn_repeat);
			}
		} else {
			/**
			 * Button Click event for Repeat button Enables repeat flag to true
			 * */
			if (isRepeat) {
				isRepeat = false;
				Toast.makeText(context, "Repeat is OFF", Toast.LENGTH_SHORT)
						.show();
				btnRepeat.setImageResource(R.drawable.btn_repeat);
			} else {
				// make repeat to true
				isRepeat = true;
				Toast.makeText(context, "Repeat is ON", Toast.LENGTH_SHORT)
						.show();
				// make shuffle to false
				isShuffle = false;
				btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
				btnShuffle.setImageResource(R.drawable.btn_shuffle);
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
