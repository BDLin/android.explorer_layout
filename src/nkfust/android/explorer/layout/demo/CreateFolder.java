package nkfust.android.explorer.layout.demo;

import java.io.File;

import poisondog.string.ExtractParentUrl;

import nkfust.android.explorer.layout.R;
import nkfust.android.explorer.layout.modle.ContentFragment;
import nkfust.android.explorer.layout.modle.TabFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

public class CreateFolder {

	private EditText editText;
	private Context context;
	private SdcardListFragment sdFrag;

	public CreateFolder(Context context, SdcardListFragment sdFrag) {
		this.sdFrag = sdFrag;
		this.context = context;
		editText = new EditText(context);
		editText.setHint("NewFolder");
	}

	public void DisplayDialog() {
		new AlertDialog.Builder(context)
				.setTitle("Create Folder")
				.setMessage("Please input the folder name.")
				.setView(editText)
				.setNegativeButton("Cancel", null)
				.setPositiveButton("Done",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								//
								File file = new File(SdcardListFragment
										.getPath() + "/" + editText.getText());
								if (!file.exists()) {
									file.mkdirs();
									Log.i("CreateFolder", "Path:" + new ExtractParentUrl().process(SdcardListFragment
											.getPath()));
									sdFrag.setAdapter(SdcardListFragment.getPath());
								} else
									new AlertDialog.Builder(context)
											.setTitle("Error!!")
											.setMessage("The folder name is repeat!!")
											.setNegativeButton("OK", null)
											.show();
							}
						}).show();
	}
}
