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

import nkfust.android.explorer.layout.R;
import poisondog.android.view.list.ComplexListItem;
import poisondog.format.TimeFormatUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SdcardFileTransform implements ComplexListItem {

	private File file;

	public SdcardFileTransform(File file) {
		this.file = file;
	}

	@Override
	public String getTitle() {
		return file.getName();
	}

	public File getFile() {
		return this.file;
	}
	
	public Long getTime(){
		return file.lastModified();
	}

	@Override
	public String getHideMessage() {
		return null;
	}

	@Override
	public Object getData() {
		return null;
	}

	@Override
	public void setData(Object object) {

	}

	@Override
	public void setSubTitle(TextView view) {
		view.setText(TimeFormatUtils.toString(TimeFormatUtils.SIMPLE,
				file.lastModified()));
	}

	@Override
	public void setComment(TextView view) {

	}

	@Override
	public void setImage(ImageView view) {
		if (file.isFile())
			view.setImageResource(R.drawable.file);
		else {
			if (new File(file.getAbsolutePath()).listFiles().length == 0)
				view.setImageResource(R.drawable.folder_empty);
			else
				view.setImageResource(R.drawable.folder_documents);
		}//End of if-else condition
	}//End of setImage function

	@Override
	public void setState(ImageView view) {

	}
}
