package nkfust.android.explorer.layout.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nkfust.android.explorer.layout.modle.AnyObjectAdapter;
import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.CustomizeImageButton;
import nkfust.android.explorer.layout.modle.TabFragment;
import nkfust.android.explorer.layout.modle.TabView;
import nkfust.android.explorer.layout.modle.Viewable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

public class SdcardListFragment extends ListFragment implements TabView{
	
	
	private List<Viewable> array;
	private CustomizeImageButton remoteBtn;
	private ContentFragment article;
	private DisplayMetrics dm;
	private String filePath;
	private TabFragment headline;
	
	public SdcardListFragment(String filePath, ContentFragment article, TabFragment headline){
		this.filePath = filePath;
		this.article = article;
		this.headline = headline;
		array = new ArrayList<Viewable>();
	}
	
	public SdcardListFragment(Context context, int img_id, ContentFragment article, String filePath, TabFragment headline){
		
		this(filePath, article, headline);
		remoteBtn = new CustomizeImageButton(context, img_id);
    	Log.i("SdcardListFragment",	"New Button Finish");
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SdcardListFragment", "onCreate()............");     
        Log.i("SdcardListFragment", "Setting List~~");
        dm = new DisplayMetrics();
        SdcardFileData fileData = new SdcardFileData(filePath);
        Log.i("SdcardListFragment", "FileList_size:" + fileData.getFileList().size());
        for(int i=0; i < fileData.getFileList().size(); i++)
        	array.add(new SdcardFileTransform(fileData.getFileList().get(i)));
        
        setListAdapter(new AnyObjectAdapter(getActivity(), array));
        Log.i("SdcardListFragment", "Setting List Finish!!");
	}
	
	public ImageButton getBtn(){
    	return remoteBtn.getButton();
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("SdcardListFragment", "onActivityCreate()............");
        //Set listener of list item
        getListView().setOnItemClickListener(new ListOnClick(article, getActivity(), array, dm, headline));
        Log.i("SdcardListFragment", "Set item listener finish");
    }
    
    @Override
    public void onStart() {
        super.onStart();
        Log.i("SdcardListFragment", "onStrat()..........");
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("SdcardListFragment", "onAttach()............");
    }
    
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("HeadlineFragment", "onCreateView()............");
        return inflater.inflate(R.layout.headline_view, container, false);
    }*/
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("SdcardListFragment", "onDestroyView()............");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SdcardListFragment", "onDestroy()............");
        CustomizeImageButton.initBtnCounter();
        array.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("SdcardListFragment", "onDetach()............");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("SdcardListFragment", "onPause()............");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("SdcardListFragment", "onResume()............");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
        Log.i("SdcardListFragment", "onStop()............");
    }
	
}
