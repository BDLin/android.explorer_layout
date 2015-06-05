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
import poisondog.vfs.IFile;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * This class is a page of can't read file.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class OtherFileView extends RelativeLayout {

	public OtherFileView(Context context, IFile iFile) {
		super(context);
		this.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT));
		
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(R.drawable.file_128);
		imageView.setId(55688);
		
		RelativeLayout.LayoutParams trelativeParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		trelativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		trelativeParams.addRule(RelativeLayout.BELOW, imageView.getId());
		TextView text = new TextView(context);
		try {
			text.setText(iFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.addView(imageView, relativeParams);
		this.addView(text, trelativeParams);
	}
}
