package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class SdcardFileData{

	private String filePath;
	private File file;
	private List<File> files;
	
	public SdcardFileData(String filePath){
		this.filePath = filePath;
		files = new ArrayList<File>();
		Log.i("SdcardFileData", "filePath:" + this.filePath);
		file = new File(this.filePath);
		
		Log.i("SdcardFileData", "file_size:" + file.listFiles().length);
		for(File mCurrentFile:file.listFiles())
			files.add(mCurrentFile);
		Log.i("SdcardFileData", "files_size:" + files.size());
	}
	
	public List<File> getFileList(){
		return files;
	}
}
