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

import nkfust.selab.android.explorer.layout.listener.PhotoAlterDialogListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class PhotoAlterDialogFragment extends DialogFragment {

	private String aTitle;
	private String[] aItems;
	
	private OnClickListener mListener = null;
	
	public PhotoAlterDialogFragment(String title, String[] items){
		aTitle = title;
		aItems = items;
		mListener = new PhotoAlterDialogListener();
	}

	public PhotoAlterDialogFragment(String title, String[] items, OnClickListener itemsListener){
		this(title, items);
		mListener = itemsListener;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle(aTitle)
                .setItems(aItems, mListener)
                .setNegativeButton("Cancel", null)
                .create();
    }
}