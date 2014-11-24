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
package nkfust.selab.android.explorer.layout.model;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.listener.VideoControllerListener;
import nkfust.selab.android.explorer.layout.listener.VideoDoFullScreenListener;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class VideoPlayerView extends RelativeLayout implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, VideoControllerView.MediaPlayerControl {

	private SurfaceView videoSurface;
	private MediaPlayer player;
	private VideoControllerView controller;
	
	private Context context;
	private LocalData local;
	private Fragment frag, content;
	
	private List<TouchListener> listenerList;
	
	public VideoPlayerView(Context context, LocalData local, Fragment tabFrag, Fragment contentFrag) {
		super(context);
		this.context = context;
		this.local = local;
		this.frag = tabFrag;
		this.content = contentFrag;
		LayoutInflater.from(context).inflate(R.layout.video_player, this);
		init();
	}

    public void init() {
        
        videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
        SurfaceHolder videoHolder = videoSurface.getHolder();
        videoHolder.addCallback(this);

        player = new MediaPlayer();
        controller = new VideoControllerView(context, videoSurface, frag);
        
        listenerList = new ArrayList<TouchListener>();
    	addListener(new VideoControllerListener(controller));
    	addListener(new VideoDoFullScreenListener(controller));
        
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(URLDecoder.decode(local.getUrl()).replace("file:", ""));
            player.setOnPreparedListener(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getVideoHeight(){
    	return player.getVideoHeight();
    }
    
    public void addListener(TouchListener listener){
    	listenerList.add(listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	for(TouchListener listener : listenerList)
    		listener.onTouch(event);
    	
        return true;
    }

    // Implement SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	player.setDisplay(holder);
        player.prepareAsync();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        
    }
    // End SurfaceHolder.Callback

    // Implement MediaPlayer.OnPreparedListener
    @Override
    public void onPrepared(MediaPlayer mp) {
        controller.setMediaPlayer(this);
        controller.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
        VideoControllerView.setVideoSize(player.getVideoHeight(), player.getVideoWidth());
        VideoControllerView.setContentSize(content.getView().getHeight(), content.getView().getWidth());
        controller.setScreenSize();
        player.start();
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
    
    public void releasePlayer(){
    	player.stop();
    	player.reset();
    	player.release();
    	controller.removeHandler();
    }

    @Override
    public int getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return player.getDuration();
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public void pause() {
        player.pause();
    }

    @Override
    public void seekTo(int i) {
        player.seekTo(i);
    }

    @Override
    public void start() {
        player.start();
    }

    @Override
    public boolean isFullScreen() {
        return controller.isFullScreen();
    }

    @Override
    public void toggleFullScreen() {
        
    }
    
    public void setScreenSize(){
    	controller.setScreenSize();
    }
    // End VideoMediaController.MediaPlayerControl
}
