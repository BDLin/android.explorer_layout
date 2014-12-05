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
package nkfust.selab.android.explorer.layout.model;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URLDecoder;
import java.util.ArrayList;

import poisondog.android.view.list.ComplexListItem;
import poisondog.net.URLUtils;
import poisondog.string.ExtractPath;
import poisondog.vfs.LocalData;
import android.content.Context;

public class SongsManager {
	// SDCard Path
	public static String current_path;
	private ArrayList<ComplexListItem> songsList = new ArrayList<ComplexListItem>();

	// Constructor
	public SongsManager(LocalData local, Context context) {
		String path = null;
		try {
			path = URLDecoder.decode(local.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		current_path = new ExtractPath().process(URLDecoder.decode(path)).replace(local.getName(), "");
	}
	
	public static String getmusicDataPath(){
		return current_path;
	}

	/**
	 * Function to read all mp3 files from sdcard and store the details in
	 * ArrayList
	 * */
	public ArrayList<ComplexListItem> getPlayList() {
		File home = new File(current_path);

		if (home.listFiles(new FileExtensionFilter()).length > 0) {
			for (File file : home.listFiles(new FileExtensionFilter())) {
				songsList.add(new SongFileFactory(new LocalData(file)));
			}
		}
		// return songs list array
		return songsList;
	}

	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			String[] token = URLUtils.guessContentType(name).split("/");
			return token[0].equals("audio");
		}
	}
}
