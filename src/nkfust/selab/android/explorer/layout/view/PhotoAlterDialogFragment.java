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

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.LinearLayout;
/**
 * This is a inherit DialogFragment class.
 * <p>
 * This class usage is must add PhotoAlterDialogItem to dialog so that it have content. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class PhotoAlterDialogFragment extends DialogFragment {
	
	private String mTitle;
	
	private List<PhotoAlterDialogItem> mItems;
	
	public PhotoAlterDialogFragment(String title){
		mTitle = title;
		mItems = new ArrayList<PhotoAlterDialogItem>();
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		DialogContentView mDialogContentView = new DialogContentView(getActivity());
		 for(PhotoAlterDialogItem item : mItems){
			if(!item.equals(mItems.get(0))){
				LinearLayout line = new LinearLayout(getActivity());
				line.setBackgroundColor(Color.GRAY);
				line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
				line.setAlpha((float)0.4);
				mDialogContentView.addView(line);
			}
			mDialogContentView.addView(item);
		 }
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 builder.setView(mDialogContentView);
         return builder
                .setTitle(mTitle)
                .setNegativeButton("Cancel", null)
                .create();
    }
	
	/**
	 * This function is add item to dialog content.
	 * @param item The item is a object of instantiates PhotoAlterDialogItem.
	 */
	public void addPhotoAlterDialogItem(PhotoAlterDialogItem item){
		mItems.add(item);
	}
}