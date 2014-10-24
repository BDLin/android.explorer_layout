package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.CustomizeImageButton;
import nkfust.android.explorer.layout.modle.TabView;
import poisondog.android.view.list.ComplexListItem;
import poisondog.android.view.list.ImageListAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ImageButton;

public class SdcardListFragment extends ListFragment implements TabView {

	private List<ComplexListItem> array;
	private CustomizeImageButton remoteBtn;
	private ContentFragment article;
	private String filePath;

	private static String tempPath;
	public static String rootPath;

	public SdcardListFragment(String filePath, ContentFragment article) {
		rootPath = filePath;
		tempPath = filePath;
		this.filePath = filePath;
		this.article = article;
		array = new ArrayList<ComplexListItem>();
	}

	public SdcardListFragment(Context context, int img_id,
			ContentFragment article, String filePath) {
		this(filePath, article);
		remoteBtn = new CustomizeImageButton(context, img_id);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAdapter(filePath);
	}

	public void setAdapter(String path) {
		array.clear();
		tempPath = path;
		Log.i("SdcardListFragment", "tempPath:" + tempPath);
		SdcardFileData fileData = new SdcardFileData(path);

		for (File file : fileData.getFileList())
			if (!file.isHidden())
				array.add(new SdcardFileTransform(file));

		setListAdapter(new ImageListAdapter(getActivity(), array));
	}

	public ImageButton getBtn() {
		return remoteBtn.getButton();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Set listener of list item
		getListView().setOnItemClickListener(
				new ListOnClick(article, getActivity(), array, this));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		CustomizeImageButton.initBtnCounter();
	}

	public static String getPath() {
		return tempPath;
	}
}
