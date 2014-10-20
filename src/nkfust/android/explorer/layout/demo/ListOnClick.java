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

import java.util.ArrayList;
import java.util.List;

import com.example.android.fragments.R;

import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.TabFragment;
import nkfust.android.explorer.layout.modle.Viewable;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListOnClick implements OnItemClickListener {

	private TabFragment headline;
	private ContentFragment article;
	private Context context;
	private List<Viewable> array;
	private View prevView;
	private DisplayMetrics dm;
	
	public ListOnClick(ContentFragment article, Context context, List<Viewable> array, DisplayMetrics dm, TabFragment headline){
		this.headline = headline;
		this.article = article;
		this.context = context;
		this.array = array;
		this.dm = dm;
	}//End of ListOnClick construct
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		
		if (array.get(position).getView(context) != null){
			article.updateArticleView(array.get(position).getView(context), dm);
        	if(prevView != null && prevView != view){
        		prevView.setBackgroundColor(0);
        		view.setBackgroundColor(Color.DKGRAY);
        		prevView = view;
        	}else if(prevView == null){
        		view.setBackgroundColor(Color.DKGRAY);
        		prevView = view;
        	}//End of if else-is condition
		}else{
			SdcardListFragment sdFrag = new SdcardListFragment(((SdcardFileTransform)array.get(position)).getFile().getAbsolutePath(),article,headline);
			headline.getActivity().getSupportFragmentManager().beginTransaction()
		       .replace(R.id.frag_container, sdFrag).addToBackStack(null).commit();
		}
   	}//End of onItemClick Function
}//End of ListOnClick Class
