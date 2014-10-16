package com.example.android.fragments;

import java.util.ArrayList;
import java.util.List;

import com.example.android.fragments.HeadlinesFragment.OnHeadlineSelectedListener;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
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

public class RemoteListFragment extends ListFragment {
		
		private ArrayList<Viewable> array = new ArrayList<Viewable>();
		private CustomizeImageButton remoteBtn;
		
		public RemoteListFragment(){}
		
		public RemoteListFragment(Context context, int img_id, HeadlinesFragment headline, LinearLayout linear){
			
	    	remoteBtn = new CustomizeImageButton(context, img_id);
	    	remoteBtn.getButtont().setOnClickListener(new RemoteBtnListener(headline, linear));
	    }
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.i("RemoteListFragment", "onCreate()............");     
	        // Create an array adapter for the list view, using the Ipsum headlines array
	        Log.i("RemoteListFragment", "setList!!");
	        
	        Ipsum I1 = new Ipsum("Article One", "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut trust fund twee occupy viral. Laboris small batch scenester pork belly, leggings ut farm-to-table aliquip yr nostrud iphone viral next level. Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. Esse single-origin coffee banksy do next level tempor. Velit synth dreamcatcher, magna shoreditch in american apparel messenger bag narwhal PBR ennui farm-to-table.");
	        Ipsum I2 = new Ipsum("Article Two","Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, vegan carles odd future.");
	        
	        array.add(I1);
	        array.add(I2);
	        array.add(new ListData("List Test"));
	        array.add(new ImageData("Image Test", getActivity(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.winter)));
	        // Create an array adapter for the list view, useing the Ipsum headlines array
	        setListAdapter(new AnyObjectAdapter(getActivity(), array));
	    }
	    
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);
	        Log.i("RemoteListFragment", "onActivityCreate()............");
	      
	        ArticleFragment article = (ArticleFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.article_fragment);
	        getListView().setOnItemClickListener(new ListOnClick(article, getActivity(), array));
	    }
	    
	    public ImageButton getBtn(){
	    	return remoteBtn.getButtont();
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
