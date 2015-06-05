/* Copyright (C) 2015 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.view;

import nkfust.selab.android.explorer.layout.R;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * This is a initial view can instantiates it and add it to any place.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class InitialView extends RelativeLayout {

	public InitialView(Context context) {
		super(context);
		this.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));
		
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
				128,128);
		relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(R.drawable.myskybox_pix1024);
		imageView.setId(55588);
		
		RelativeLayout.LayoutParams trelativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		trelativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		trelativeParams.addRule(RelativeLayout.BELOW, imageView.getId());
		TextView text = new TextView(context);
		text.setText("MySkyBox");
		text.setTextSize(20);
		
		this.addView(imageView, relativeParams);
		this.addView(text, trelativeParams);
	}
}