package nkfust.selab.android.explorer.layout.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class PhotoAlterDialogFragment extends DialogFragment {

	private String aTitle;
	private String[] aItems;
	
	public PhotoAlterDialogFragment(String title, String[] items){
		aTitle = title;
		aItems = items;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle(aTitle)
                .setItems(aItems, null)
                .setNegativeButton("Cancel", null)
                .create();
    }
}