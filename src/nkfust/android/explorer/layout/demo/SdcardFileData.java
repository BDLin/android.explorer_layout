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
import java.util.ArrayList;
import java.util.List;

public class SdcardFileData {

	private String filePath;
	private File file;
	private List<File> files;

	public SdcardFileData(String filePath) {
		this.filePath = filePath;
		files = new ArrayList<File>();
		file = new File(this.filePath);

		for (File mCurrentFile : file.listFiles())
			files.add(mCurrentFile);
	}// End of SdcardFileData construct

	public List<File> getFileList() {
		return files;
	}// End of getFileList function
}// End of SdcardFileData class
