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

import java.util.List;

import nkfust.selab.android.explorer.layout.model.TabFragment;
import nkfust.selab.android.explorer.layout.view.PhotoAlterDialogFragment;
import nkfust.selab.android.explorer.layout.view.PhotoAlterDialogItem;
import nkfust.selab.android.explorer.layout.view.PhotoViewer;
import poisondog.string.ExtractFileName;
import android.view.View;
import android.view.View.OnClickListener;

public class PhotoAlterListener implements OnClickListener {

	private List<PhotoAlterDialogItem> mItems;
	private PhotoViewer mPhotoView;
	
	public PhotoAlterListener(PhotoViewer photoView, List<PhotoAlterDialogItem> items){
		mPhotoView = photoView;
		mItems = items;
	}
	
	@Override
	public void onClick(View v) {
		String path = mPhotoView.getPaths().get(mPhotoView.getPhotoIndex());
		String title = new ExtractFileName().process(path);
		PhotoAlterDialogFragment dialog = new PhotoAlterDialogFragment( title, mItems);
		dialog.show(TabFragment.getActionBarActivity().getSupportFragmentManager(), "dialog");
	}
}