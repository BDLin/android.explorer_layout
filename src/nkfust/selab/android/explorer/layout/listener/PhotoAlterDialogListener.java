package nkfust.selab.android.explorer.layout.listener;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

public class PhotoAlterDialogListener implements OnClickListener {

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which){
		  case 0:
			  Log.i("PhotoAlterListener", "index: 0");
			  break;
		  case 1:
			  Log.i("PhotoAlterListener", "index: 1");
			  break;
		  default:
			  Log.i("PhotoAlterListener", "index: none");
			  break;
		}
	}
}
