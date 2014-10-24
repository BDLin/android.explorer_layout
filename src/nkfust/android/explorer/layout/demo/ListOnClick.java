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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import nkfust.android.explorer.layout.modle.ContentFragment;
import poisondog.android.view.list.ComplexListItem;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ListOnClick implements OnItemClickListener {

	//private TabFragment headline;
	private ContentFragment article;
	private Context context;
	private List<ComplexListItem> array;
	private View prevView;
	private SdcardListFragment listFrag;

	public ListOnClick(ContentFragment article, Context context,
			List<ComplexListItem> array, SdcardListFragment listFrag) {
		//this.headline = headline;
		this.listFrag = listFrag;
		this.article = article;
		this.context = context;
		this.array = array;
	}// End of ListOnClick construct

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		if (((SdcardFileTransform) array.get(position)).getFile().isFile()) {

			TextView text = new TextView(context);
			text.setText(readFromSDcard(((SdcardFileTransform) array
					.get(position)).getFile()));
			text.setTextSize(25);
			article.updateArticleView(text);

			if (prevView != null && prevView != view) {
				prevView.setBackgroundColor(0);
				view.setBackgroundColor(Color.DKGRAY);
				prevView = view;
			} else if (prevView == null) {
				view.setBackgroundColor(Color.DKGRAY);
				prevView = view;
			}// End of if else-is condition
		} else {
			listFrag.setAdapter(((SdcardFileTransform) array.get(position)).getFile()
							.getAbsolutePath());
		}//End of if-else condition
	}// End of onItemClick Function

	private String readFromSDcard(File file) {

		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] data = new byte[fin.available()];
			while (fin.read(data) != -1) {
				sb.append(new String(data));
			}//End of while loop
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//End try-catch
		return sb.toString();
	}// End of readFromSDcard function
}// End of ListOnClick Class
