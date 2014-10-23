package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

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
	}//End of SdcardFileData construct

	public List<File> getFileList() {
		return files;
	}//End of getFileList function
}//End of SdcardFileData class
