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
package nkfust.selab.android.explorer.layout.processer;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import poisondog.net.URLUtils;
import poisondog.string.ExtractPath;
import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;
import android.content.Context;
import android.util.Log;

public class SongsManager {
	// SDCard Path
	private String current_path;
	private List<IFile> songsList = new ArrayList<IFile>();
	private IFileFactory mFactory;

	// Constructor
	public SongsManager(Context context, IFile iFile, IFileFactory factory) {
		mFactory = factory;
		String path = null;
		try {
			path = URLDecoder.decode(iFile.getUrl());
			current_path = new ExtractPath().process(path).replace(iFile.getName(), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getmusicDataPath(){
		return current_path;
	}

	/**
	 * Function to read all mp3 files from sdcard and store the details in
	 * ArrayList
	 * */
	public List<IFile> getPlayList() {
		File home = new File(current_path);
		Log.i("SongManager", "path: " + current_path);
		songsList.clear();
		if (home.listFiles(new FileExtensionFilter()).length > 0) {
			for (File file : home.listFiles(new FileExtensionFilter())) {
				try {
					songsList.add(mFactory.getFile(file.getAbsolutePath()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// return songs list array
		
		Log.i("SongManager", "Size: " + songsList.size());
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
