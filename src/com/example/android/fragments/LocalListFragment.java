package com.example.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class LocalListFragment extends ListFragment implements TabView{
	
	private String[] presidents = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
	private CustomizeImageButton localBtn;
	
	public LocalListFragment(){}
	
	public LocalListFragment(Context context, int img_id){
	    	localBtn = new CustomizeImageButton(context, img_id);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LocalListFragment", "onCreate()............");     
        
        // Create an array adapter for the list view, useing the Ipsum headlines array
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, presidents));
        
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("LocalListFragment", "onActivityCreate()............");
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
    
    public ImageButton getBtn(){
    	return localBtn.getButton();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        Log.i("LocalListFragment", "onStrat()..........");
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("LocalListFragment", "onAttach()............");
    }
    
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("HeadlineFragment", "onCreateView()............");
        return inflater.inflate(R.layout.headline_view, container, false);
    }*/
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("LocalListFragment", "onDestroyView()............");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("LocalListFragment", "onDestroy()............");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("LocalListFragment", "onDetach()............");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("LocalListFragment", "onPause()............");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("LocalListFragment", "onResume()............");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
        Log.i("LocalListFragment", "onStop()............");
    }

}

