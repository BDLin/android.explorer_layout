package nkfust.selab.android.explorer.layout.model;

import java.io.IOException;
import java.net.URLDecoder;

import nkfust.selab.android.explorer.layout.R;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.util.Log;
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
	private long lastClickTime = 0;
	
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	switch (event.getAction() & MotionEvent.ACTION_MASK) {
    		case MotionEvent.ACTION_DOWN:
    			controller.show();
    		    break;
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
        Log.i("VideoPlayer","getHeight:" + content.getView().getHeight());
   	 	Log.i("VideoPlayer","getWidth:" + content.getView().getWidth());
        VideoControllerView.setContentSize(content.getView().getHeight(), content.getView().getWidth());
        setSurfaceSize();
        controller.setScreenSize();
        player.start();
    }
    // End MediaPlayer.OnPreparedListener
    
    public void setSurfaceSize(){
//    	DisplayMetrics metrics = new DisplayMetrics(); 
//     	WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//     	windowManager.getDefaultDisplay().getMetrics(metrics);
//     	FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) videoSurface.getLayoutParams();
//     	int contentWidth = content.getView().getWidth();
//     	int contentHeight = content.getView().getHeight();
//     	float videoProportion = (float) player.getVideoWidth() / (float) player.getVideoHeight();
//     	float contentScreenProportion = (float) contentWidth / (float) contentHeight;
//     	if (videoProportion > contentScreenProportion) {
//            params.width = contentWidth;
//            params.height = (int) ((float) contentWidth / videoProportion);
//        } else {
//            params.width = (int) (videoProportion * (float) contentHeight);
//            params.height = contentHeight;
//        }
//     	params.setMargins(0, 0, 0, 0);
//        videoSurface.setLayoutParams(params);
    }

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
