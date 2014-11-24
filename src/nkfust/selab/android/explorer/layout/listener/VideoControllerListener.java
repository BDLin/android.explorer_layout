package nkfust.selab.android.explorer.layout.listener;

import android.view.MotionEvent;
import nkfust.selab.android.explorer.layout.model.TouchListener;
import nkfust.selab.android.explorer.layout.model.VideoControllerView;

public class VideoControllerListener implements TouchListener {

	private VideoControllerView controller;
	
	public VideoControllerListener(VideoControllerView controller){
		this.controller = controller;
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN)
			controller.show();
	}
}
