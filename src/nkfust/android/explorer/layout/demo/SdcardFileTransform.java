package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.android.fragments.R;
import poisondog.format.*;

import poisondog.android.view.list.ComplexListItem;
import android.content.Context;
import android.view.View;
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
