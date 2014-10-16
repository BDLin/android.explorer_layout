package com.example.android.fragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import android.widget.ImageButton;
import android.widget.LinearLayout;

//import android.preference.PreferenceFragment;


public class PrefsFragment extends PreferenceFragment implements TabView{

    private CustomizeImageButton settingBtn;
    
    public PrefsFragment(){}
	
    public PrefsFragment(Context context, int img_id){
    	settingBtn = new CustomizeImageButton(context, img_id);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
    
    public ImageButton getBtn(){
    	return settingBtn.getButton();
    }
}