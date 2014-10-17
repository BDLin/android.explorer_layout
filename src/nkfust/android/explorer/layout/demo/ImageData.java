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
package nkfust.android.explorer.layout.demo;

import nkfust.android.explorer.layout.modle.Viewable;

import com.example.android.fragments.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class ImageData implements Viewable {
	
	private String title;
	private Context context;
	private ImageView imageView;
	private LinearLayout linear;
	private ZoomImageAction action;
	
	public ImageData(String title,Context context, Bitmap bitmap, DisplayMetrics dm){
		this.title = title;
		this.context = context;
	    this.imageView = new ImageView(context);
        this.linear = linear;
        Log.i("image", "dm:" + dm);
        action = new ZoomImageAction(bitmap,dm);
        imageView.setImageMatrix(action.getMatrix());
        imageView.setImageBitmap(bitmap);
        action.setImage(imageView);
        action.setImageSize();
        action.minZoom();
        action.center();
        action.setIsZoom(false);
	}
	
	@Override
	public View getView(Context context) {
		imageView = action.getImage();
		imageView.setAdjustViewBounds(true);
		//imageView.setImageResource(R.drawable.winter);
		return imageView;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
}//End of class ImageData
