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
package nkfust.android.explorer.layout.modle;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomizeImageButton{

	private ImageButton imgBtn;
	private static int temp = 0;
	
	public CustomizeImageButton(Context context, int img_id){
		imgBtn = new ImageButton(context);
		imgBtn.setImageResource(img_id);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
		params.weight = 1;
		imgBtn.setLayoutParams(params);
		imgBtn.setId(temp++);
		if(imgBtn.getId() != 0)
			imgBtn.setAlpha((float)0.5);
	}
	
	public ImageButton getButton(){
		return imgBtn;
	}
	
	public static void initBtnCounter(){
		temp = 0;
	}
}
