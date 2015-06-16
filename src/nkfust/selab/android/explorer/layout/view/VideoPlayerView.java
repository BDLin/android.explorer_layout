/* Copyright (C) 2014 Zi-Xiang Lin <bdl9437@gmail.com>
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
package nkfust.selab.android.explorer.layout.view;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.listener.VideoControllerListener;
import nkfust.selab.android.explorer.layout.listener.VideoDoFullScreenListener;
import nkfust.selab.android.explorer.layout.model.TabFragment;
import nkfust.selab.android.explorer.layout.model.TouchListener;
import poisondog.string.ExtractPath;
import poisondog.vfs.IFile;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
/**
 * This class is a video player view.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class VideoPlayerView extends RelativeLayout implements
		SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,
		VideoControllerView.MediaPlayerControl {

	private SurfaceView mVideoSurface;
	private MediaPlayer mPlayer;
	private VideoControllerView mController;

	private Context mContext;
	private IFile mIFile;

	private List<TouchListener> listenerList;

	public VideoPlayerView(Context context, IFile ifile) {
		super(context);
		mContext = context;
		mIFile = ifile;
		LayoutInflater.from(context).inflate(R.layout.video_player, this);
		init();
	}

	private void init() {

		mVideoSurface = (SurfaceView) findViewById(R.id.videoSurface);
		SurfaceHolder videoHolder = mVideoSurface.getHolder();
		videoHolder.addCallback(this);

		mPlayer = new MediaPlayer();
		mController = new VideoControllerView(mContext, mVideoSurface);

		listenerList = new ArrayList<TouchListener>();
		addListener(new VideoControllerListener(mController));
		addListener(new VideoDoFullScreenListener(mController));

		try {
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mPlayer.setDataSource(new ExtractPath().process(URLDecoder.decode(mIFile.getUrl())));
			mPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The function is add listener to process the event that happen touch  video view range.
	 * @param listener The listener is process the event that happen touch  video view range.
	 */
	public void addListener(TouchListener listener) {
		listenerList.add(listener);
	}

	/**
	 * This function is set video view display size ratio consistent with screen size.
	 */
	public void setScreenSize() {
		mController.setScreenSize();
	}

	/**
	 * Release video player used resource.
	 */
	public void releasePlayer() {
		if(mPlayer != null){
			mPlayer.stop();
			mPlayer.reset();
			mPlayer.release();
			mController.removeHandler();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(TabFragment.getFrameLayout() != null){
			new VideoControllerListener(mController).onTouch(event);
			return true;
		}
		
		for (TouchListener listener : listenerList)
			listener.onTouch(event);
		return true;
	}

	// Implement SurfaceHolder.Callback
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mPlayer.setDisplay(holder);
		mPlayer.prepareAsync();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	// End SurfaceHolder.Callback

	// Implement MediaPlayer.OnPreparedListener
	@Override
	public void onPrepared(MediaPlayer mp) {
		mController.setMediaPlayer(this);
		mController.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
		mController.setVideoSize(mPlayer.getVideoHeight(),mPlayer.getVideoWidth());
		mController.setScreenSize();
		mPlayer.start();
	}

	// End MediaPlayer.OnPreparedListener

	// Implement VideoMediaController.MediaPlayerControl
	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		return mPlayer.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		return mPlayer.getDuration();
	}

	@Override
	public boolean isPlaying() {
		return mPlayer.isPlaying();
	}

	@Override
	public void pause() {
		mPlayer.pause();
	}

	public void stop() {
		mPlayer.stop();
	}

	@Override
	public void seekTo(int i) {
		mPlayer.seekTo(i);
	}

	@Override
	public void start() {
		try {
			mPlayer.prepare();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPlayer.start();
	}

	@Override
	public boolean isFullScreen() {
		return mController.isFullScreen();
	}

	@Override
	public void toggleFullScreen() {}
	// End VideoMediaController.MediaPlayerControl
}
