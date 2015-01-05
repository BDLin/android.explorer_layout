package nkfust.selab.android.explorer.layout.model;

import android.app.Application;
import android.util.Log;

import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PlugPDFException.InvalidLicense;

//import com.epapyrus.plugpdf.core.PlugPDF;
//import com.epapyrus.plugpdf.core.PlugPDFException.InvalidLicense;

public class SampleApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
 
		try {
			// Initialize PlugPDF with a license key.
			Log.i("SampleApplication", "init()");
			PlugPDF.init(getApplicationContext(),
					"5HF52DBACG4HFDE7ED34H58H646GDAA6HCD2G2A3DFCHF24H7299FBF5");
		} catch (InvalidLicense ex) {
			Log.e("KS", "error ", ex);
			// Handle invalid license exceptions.
		}
	}
}
