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
package nkfust.selab.android.explorer.layout.listener;

import java.io.File;

import nkfust.selab.android.explorer.layout.view.PhotoViewer;
import poisondog.net.URLUtils;
import poisondog.string.ExtractFileName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class PhotoShareListener implements OnClickListener {
	
	private Context mContext;
	private PhotoViewer mPhotoView;
	
	public PhotoShareListener(Context context, PhotoViewer photoView){
		mContext = context;
		mPhotoView = photoView;
	}

	@Override
	public void onClick(View v) {
		Intent shareIntent=new Intent(Intent.ACTION_SEND);
		String path = URLUtils.path(mPhotoView.getPaths().get(mPhotoView.getPhotoIndex()));
		shareIntent.setType(URLUtils.guessContentType(new ExtractFileName().process(path)));
		try{
			shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		mContext.startActivity(Intent.createChooser(shareIntent, "Share..."));
	}
}