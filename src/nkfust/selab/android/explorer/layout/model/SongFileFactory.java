package nkfust.selab.android.explorer.layout.model;

import java.net.URLDecoder;

import poisondog.android.view.list.ComplexListItem;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.widget.ImageView;
import android.widget.TextView;

public class SongFileFactory implements ComplexListItem {

	private LocalData file;

	public SongFileFactory(LocalData file) {
		this.file = file;
	}

	public Long getTime() {
		try {
			return file.getLastModifiedTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Long(0);
	}

	@Override
	public IFile getData() {
		return this.file;
	}

	@Override
	public String getTitle() {
		return URLDecoder.decode(file.getName());
	}

	@Override
	public String getHideMessage() {
		return null;
	}

	@Override
	public void setData(Object object) {
	}

	@Override
	public void setSubTitle(TextView view) {
	}

	@Override
	public void setComment(TextView view) {
	}

	@Override
	public void setImage(ImageView view) {
	}

	@Override
	public void setState(ImageView view) {
	}
}
