package nkfust.selab.android.explorer.layout.listener;

import nkfust.selab.android.explorer.layout.model.TouchListener;
import nkfust.selab.android.explorer.layout.view.VideoControllerView;
import android.view.MotionEvent;
/**
 * This class function is screen zoom full when playing video. 
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class VideoDoFullScreenListener implements TouchListener {

	private VideoControllerView mController;
	private long lastClickTime = 0;

	/**
	 * @param controller	The current use video controller view.
	 */
	public VideoDoFullScreenListener(VideoControllerView controller) {
		mController = controller;
	}

	@Override
	public void onTouch(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			long now = System.currentTimeMillis();
			if ((now - lastClickTime) < 300) {
				mController.doToggleFullscreen();
				now = 0;
			}
			lastClickTime = now;
			break;
		}
	}
}
