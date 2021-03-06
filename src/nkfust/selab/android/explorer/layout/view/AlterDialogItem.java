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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * This class is a item of PhotoAlterDialog content.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class AlterDialogItem extends LinearLayout {

	/**
	 * @param context 		The Activity parent.
	 * @param displayText	Want to display text.
	 * @param listener		The listener is process click event of this item. 
	 */
	public AlterDialogItem(Context context, String displayText, View.OnClickListener listener) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.photo_alter_dialog_item_layout, this);
		TextView text = (TextView)findViewById(R.id.dialog_item_title);
		text.setText(displayText);
		text.setTextSize(20);
		this.setOnClickListener(listener);
	}
}
