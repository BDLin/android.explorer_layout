package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import nkfust.android.explorer.layout.modle.Viewable;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SdcardFileTransform implements Viewable {

	private File file;
	
	public SdcardFileTransform(File file){
		this.file = file;
	}
	
	@Override
	public View getView(Context context) {
		if(this.file.isFile()){
			TextView text = new TextView(context);
			text.setText(readFromSDcard(file));
			text.setTextSize(25);
			return text;
		}else
			return null;
	}
	
	private String readFromSDcard(File file){
	    
	    StringBuilder sb = new StringBuilder();
	    try {
	          FileInputStream fin = new FileInputStream(file);
	          byte[] data = new byte[fin.available()];
	          while (fin.read(data) != -1) {
	            sb.append(new String(data));
	    }
	        fin.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    //Log.d("TAG", "Read from SDCARD: " + json.toString());
	    return sb.toString();
	}

	@Override
	public String getTitle() {
		return file.getName();
	}
	
	public File getFile(){
		return this.file;
	}
}
