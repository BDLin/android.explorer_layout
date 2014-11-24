package nkfust.selab.android.explorer.layout.listener;

import nkfust.selab.android.explorer.layout.model.TouchListener;
import nkfust.selab.android.explorer.layout.model.VideoControllerView;
import android.view.MotionEvent;

public class VideoDoFullScreenListener implements TouchListener {

	private VideoControllerView controller;
	private long lastClickTime = 0;
	
	public VideoDoFullScreenListener(VideoControllerView controller){
		this.controller = controller;
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		  case MotionEvent. ACTION_UP:
          case MotionEvent. ACTION_POINTER_UP:
          	long now = System.currentTimeMillis();
          	if ((now - lastClickTime) < 300){
          		controller.doToggleFullscreen();
          		now = 0;
          	}
          	lastClickTime = now;
  			break;
		}
	}
}
