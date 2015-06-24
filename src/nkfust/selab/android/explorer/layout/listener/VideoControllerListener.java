package nkfust.selab.android.explorer.layout.listener;

import android.view.MotionEvent;
import nkfust.selab.android.explorer.layout.model.TouchListener;
import nkfust.selab.android.explorer.layout.view.VideoControllerView;
/**
 * This class is a listener ,its function is show video controller view when playing video. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class VideoControllerListener implements TouchListener {

	private VideoControllerView mController;
	
	/**
	 * @param controller	The current use video controller view.
	 */
	public VideoControllerListener(VideoControllerView controller){
		mController = controller;
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN)
			mController.show();
	}
}
