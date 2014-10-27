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

import java.io.File;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class CreateFolder {

	private EditText editText;
	private Context context;
	private SdcardListFragment sdFrag;

	public CreateFolder(Context context, SdcardListFragment sdFrag) {
		this.sdFrag = sdFrag;
		this.context = context;
		editText = new EditText(context);
		editText.setHint("NewFolder");
	}

	public void DisplayDialog() {
		new AlertDialog.Builder(context)
				.setTitle("Create Folder")
				.setMessage("Please input the folder name.")
				.setView(editText)
				.setNegativeButton("Cancel", null)
				.setPositiveButton("Done",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								File file = new File(sdFrag.getCurrentPath()
										+ "/" + editText.getText());
								if (!file.exists()) {
									file.mkdirs();
									sdFrag.setAdapter(sdFrag.getCurrentPath());
								} else
									new AlertDialog.Builder(context)
											.setTitle("Error!!")
											.setMessage("The folder name is repeat!!")
											.setNegativeButton("OK", null)
											.show();
							}//End of onClick
						}).show();
	}//End of DisplayDialog function
}//End of CreateFolder class
