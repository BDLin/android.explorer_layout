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

import nkfust.android.explorer.layout.modle.AnyObjectAdapter;
import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.CustomizeImageButton;
import nkfust.android.explorer.layout.modle.TabView;
import nkfust.android.explorer.layout.modle.Viewable;

import com.example.android.fragments.R;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class RemoteListFragment extends ListFragment implements TabView{
		
		private ArrayList<Viewable> array = new ArrayList<Viewable>();
		private CustomizeImageButton remoteBtn;
		private ContentFragment article;
		private DisplayMetrics dm;
		
		public RemoteListFragment(){}
		
		public RemoteListFragment(Context context, int img_id, ContentFragment article){
			
			this.article = article;
	    	remoteBtn = new CustomizeImageButton(context, img_id);
	    	Log.i("RemoteListFragmet",	"New Button Finish");

	    }
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.i("RemoteListFragment", "onCreate()............");     
	        Log.i("RemoteListFragment", "Setting List~~");
	        dm = new DisplayMetrics();
	        
	        Ipsum I1 = new Ipsum("Article One", "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
	        Ipsum I2 = new Ipsum("Article Two","Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
	        
	        array.add(I1);
	        array.add(I2);
	        array.add(new ListData("List Test"));
	        array.add(new ImageData("Image Test", getActivity(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.winter),dm));
	        Log.i("RemoteListFragment", "Array_size:" + array.size());
	        setListAdapter(new AnyObjectAdapter(getActivity(), array));
	        Log.i("RemoteListFragment", "Setting List Finish!!");
	    }
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        Log.i("RemoteListFragment", "onActivityCreate()............");
	        //Set listener of list item
	        getListView().setOnItemClickListener(new ListOnClick(article, getActivity(), array, dm));
	        Log.i("RemoteListFragment", "Set item listener finish");
	    }
	    
	    public ImageButton getBtn(){
	    	return remoteBtn.getButton();
	    }
	    
	    @Override
	    public void onStart() {
	        super.onStart();
	        Log.i("RemoteListFragment", "onStrat()..........");
	    }
	    
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        Log.i("RemoteListFragment", "onAttach()............");
	    }
	    
	    /*@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        Log.i("HeadlineFragment", "onCreateView()............");
	        return inflater.inflate(R.layout.headline_view, container, false);
	    }*/
	    
	    @Override
	    public void onDestroyView() {
	        super.onDestroyView();
	        Log.i("RemoteListFragment", "onDestroyView()............");
	    }

	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        Log.i("RemoteListFragment", "onDestroy()............");
	        CustomizeImageButton.initBtnCounter();
	        array.clear();
	        Log.i("RemoteListFragment", "Array_size:" + array.size());
	    }

	    @Override
	    public void onDetach() {
	        super.onDetach();
	        Log.i("RemoteListFragment", "onDetach()............");
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        Log.i("RemoteListFragment", "onPause()............");
	    }

	    @Override
	    public void onResume() {
	        super.onResume();
	        Log.i("RemoteListFragment", "onResume()............");
	    }
	    
	    @Override
	    public void onStop() {
	    	super.onStop();
	        Log.i("RemoteListFragment", "onStop()............");
	    }
	
}
