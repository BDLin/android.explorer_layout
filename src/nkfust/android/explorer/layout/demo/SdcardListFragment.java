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

import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.CustomizeImageButton;
import nkfust.android.explorer.layout.modle.FileData;
import nkfust.android.explorer.layout.modle.TabView;
import poisondog.android.view.list.ComplexListItem;
import poisondog.android.view.list.ImageListAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ImageButton;

public class SdcardListFragment extends ListFragment implements TabView , FileData{

	private List<ComplexListItem> array;
	private CustomizeImageButton remoteBtn;
	private ContentFragment article;

	private String tempPath;
	private String rootPath;

	public SdcardListFragment(String filePath, ContentFragment article) {
		rootPath = filePath;
		tempPath = filePath;
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
		setAdapter(tempPath);
	}

	public void setAdapter(String path) {
		array.clear();
		tempPath = path;
		Log.i("SdcardListFragment", "tempPath:" + tempPath);
		SdcardFileData fileData = new SdcardFileData(path);

		for (File file : fileData.getFileList())
			if (!file.isHidden())
				array.add(new SdcardFileTransform(file));

		reloadList();
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

	@Override
	public String getCurrentPath() {
		return tempPath;
	}

	@Override
	public Boolean isEqualsRootPath() {
		return tempPath.equals(rootPath);
	}
	
	public void doSortByName(){
		array = FileDoSort.doSortByName(array);
	}
	
	public void doSortByTime(){
		array = FileDoSort.doSortByTime(array);
	}
	
	public void reloadList(){
		setListAdapter(new ImageListAdapter(getActivity(), array));
	}
}
