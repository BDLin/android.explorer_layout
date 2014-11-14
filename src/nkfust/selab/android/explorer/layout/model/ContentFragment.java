/*
 * Copyright (C) 2012 The Android Open Source Project
 * Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.model;


import java.io.IOException;
import java.io.InputStream;

import nkfust.selab.android.explorer.layout.R;
import poisondog.net.URLUtils;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.viewer.DocumentState;

public class ContentFragment extends Fragment {

	private RelativeLayout relative;
	private	LocalData local;
	
	public void setIFile (IFile file){
		local = (LocalData)file;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.article_view, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i("ContentFragment", "onActivityCreated....");
		relative = (RelativeLayout) getActivity().findViewById(R.id.relative_layout);
	}
	
	@Override
    public void onStart() {
        super.onStart();
        Log.i("ContentFragment", "onStart....");
        Bundle args = getArguments();
        if (args != null){
        	Log.i("ContentFragment", "args not null~~");
			try {
				updateArticleView(local);
				Log.i("ContentFragment", "Inner onStart update finish....");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else
        	Log.i("ContentFragment", "args is null~~");
    }

	public void updateArticleView(IFile file) throws IOException {
		relative.removeAllViews();
		local = (LocalData)file;
		InputStream is = local.getInputStream();
		int size = is.available();
		Log.i("ContentFragment", "File Size:" + size);
		
        if(getFileSubtype(URLUtils.guessContentType(local.getName())).equals("pdf")){
        	if (size > 0) {
            	byte[] data = new byte[size];
            	is.read(data);
            	open(data);
            }
            
            is.close();
        }else{
        	TextView text = new TextView(getActivity());
        	text.setText(local.getName());
        	relative.addView(text);
        }
	}//End of updateArticleView function
	
	public String getFileSubtype(String fileName){
		String[] token = fileName.split("/");
		return token[1];
	}
	
	public String getFileType(String fileName){
		String[] token = fileName.split("/");
		return token[0];
	}
	
	public void open(byte[] data) {
		// pdfviewer create.
		SimpleDocumentReader viewer = SimpleReaderFactory.createSimpleViewer(
				this.getActivity(), m_listener);
		// pdf data load.
		relative.addView(viewer.getReaderView());
		viewer.openData(data, data.length, "");
	}
 
	// create a listener for receiving provide pdf loading results
	SimpleDocumentReaderListener m_listener = new SimpleDocumentReaderListener() {
 
		@Override
		public void onLoadFinish(DocumentState.OPEN state) {
		}
	};
}