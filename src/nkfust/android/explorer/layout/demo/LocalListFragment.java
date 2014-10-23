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
import nkfust.android.explorer.layout.modle.TabFragment;
import nkfust.android.explorer.layout.modle.TabView;
import poisondog.android.view.list.ComplexListItem;
import poisondog.android.view.list.ImageListAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ImageButton;

public class LocalListFragment extends ListFragment implements TabView{
	
	private CustomizeImageButton localBtn;
	private List<ComplexListItem> array;
	private ContentFragment article;
	private String filePath;
	private TabFragment headline;
	
	public LocalListFragment(String filePath, ContentFragment article,
			TabFragment headline){
		this.filePath = filePath + "/Download/";
		this.article = article;
		this.headline = headline;
		array = new ArrayList<ComplexListItem>();
	}
	
	public LocalListFragment(Context context, int img_id,
			ContentFragment article, String filePath, TabFragment headline){
		this(filePath, article, headline);
	    localBtn = new CustomizeImageButton(context, img_id);
	}
	
    public ImageButton getBtn(){
    	return localBtn.getButton();
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SdcardFileData fileData = new SdcardFileData(filePath);

		for (File file : fileData.getFileList())
			if (!file.isHidden())
				array.add(new SdcardFileTransform(file));

		setListAdapter(new ImageListAdapter(getActivity(), array));
	}
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    	// Set listener of list item
     		getListView().setOnItemClickListener(
     				new ListOnClick(article, getActivity(), array, headline));
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        array.clear();
    }
}

