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
import java.util.Random;

import nkfust.selab.android.explorer.layout.R;
import nkfust.selab.android.explorer.layout.listener.ForwardOrBackwardListener;
import nkfust.selab.android.explorer.layout.listener.PlayMusicListener;
import nkfust.selab.android.explorer.layout.listener.PreviousOrNextListener;
import nkfust.selab.android.explorer.layout.listener.ShuffleOrRepeatListener;
import nkfust.selab.android.explorer.layout.processer.SongsManager;
import nkfust.selab.android.explorer.layout.processer.Utilities;
import poisondog.net.URLUtils;
import poisondog.string.ExtractPath;
import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;
import poisondog.vfs.LocalData;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
/**
 * This class is a music player view.
 * @author Zi-Xiang Lin <bdl9437@gmail.com>
 */
public class MusicPlayerView extends RelativeLayout implements
		OnCompletionListener, SeekBar.OnSeekBarChangeListener {

	private ImageButton btnPlay;
	private ImageButton btnForward;
	private ImageButton btnBackward;
	private ImageButton btnNext;
	private ImageButton btnPrevious;
	private ImageButton btnRepeat;
	private ImageButton btnShuffle;
	private SeekBar songProgressBar;
	private TextView songTitleLabel;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;
	// Media Player
	private MediaPlayer mp;
	// Handler to update UI timer, progress bar etc,.
	private Handler mHandler = new Handler();;
	private Utilities utils;
	
	private int currentSongIndex = 0;
	private List<IFile> songsList = new ArrayList<IFile>();
	private List<IFile> mIFileList = new ArrayList<IFile>();

	private IFile mIFile;
	private SongsManager songManager;

	private long totalDuration, currentDuration;

	private Context mContext;
	private ShuffleOrRepeatListener srListener;
	private PreviousOrNextListener poListener;
	private ForwardOrBackwardListener fbListener;
	private PlayMusicListener playListener;
	private IFileFactory mFactory;

	/**
	 * @param context The activity parent.
	 * @param iFile   The iFile is a song file that want to play music.
	 * @param aList   The aList is a file list at the current list.
	 * @param factory The factory is a file factory can generate file. 
	 */
	public MusicPlayerView(Context context, IFile iFile, List<IFile> aList, IFileFactory factory){
		super(context);
		mContext = context;
		mIFile = iFile;
		mIFileList = aList;
		mFactory = factory;
		LayoutInflater.from(context).inflate(R.layout.player, this);
		init();
	}

	private void init() {

		// All player buttons
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		btnForward = (ImageButton) findViewById(R.id.btnForward);
		btnBackward = (ImageButton) findViewById(R.id.btnBackward);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
		btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
		btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
		songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
		songTitleLabel = (TextView) findViewById(R.id.songTitle);
		songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
		songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);

		// Mediaplayer
		mp = new MediaPlayer();
		utils = new Utilities();

		// Listeners
		songProgressBar.setOnSeekBarChangeListener(this); // Important
		mp.setOnCompletionListener(this); // Important

		// By default play first song
		songManager = new SongsManager(mContext, mIFile, mFactory);
		playSong(mIFile);

		// All Listener
		srListener = new ShuffleOrRepeatListener(mContext, btnRepeat, btnShuffle);
		poListener = new PreviousOrNextListener(this, btnNext, btnPrevious);
		fbListener = new ForwardOrBackwardListener(btnForward, btnBackward, mp);
		playListener = new PlayMusicListener(btnPlay, mp);

		// Set Button Listener
		btnPlay.setOnClickListener(playListener);

		btnForward.setOnClickListener(fbListener);
		btnBackward.setOnClickListener(fbListener);

		btnNext.setOnClickListener(poListener);
		btnPrevious.setOnClickListener(poListener);

		btnRepeat.setOnClickListener(srListener);
		btnShuffle.setOnClickListener(srListener);
	}

	/**
	 * Function to play a song
	 * 
	 * @param iFile The iFile is a file of song.
	 * */
	public void playSong(IFile iFile) {

		mIFile = iFile;
		updateCurrentSongIndex();
		
		// Getting all songs list
		songsList = songManager.getPlayList();
		
		// Play song
		try {
			mp.reset();
			mp.setDataSource(new ExtractPath().process(URLDecoder.decode(mIFile.getUrl())));
			mp.prepare();
			mp.start();
			// Displaying Song title
			String songTitle = URLDecoder.decode(mIFile.getName());
			songTitleLabel.setText(songTitle);

			// Changing Button Image to pause image
			btnPlay.setImageResource(R.drawable.btn_pause);

			totalDuration = mp.getDuration();
			currentDuration = mp.getCurrentPosition();
			// set Progress bar values
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);

			// Updating progress bar
			updateProgressBar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * On Song Playing completed if repeat is ON play same song again if shuffle
	 * is ON play random song
	 * */
	@Override
	public void onCompletion(MediaPlayer arg0) {

		// check for repeat is ON or OFF
		if (ShuffleOrRepeatListener.isRepeat()) {
			// repeat is on play same song again
			playSong(mIFile);
		} else if (ShuffleOrRepeatListener.isShuffle()) {
			// shuffle is on - play a random song
			Random rand = new Random();
			currentSongIndex = rand.nextInt(songsList.size());
			playSong(songsList.get(currentSongIndex));
		} else {
			// no repeat or shuffle ON - play next song
			for (int i = currentSongIndex; i < mIFileList.size(); i++)
				if (i != (mIFileList.size() - 1)
						&& URLUtils.guessContentType(
								((LocalData) mIFileList.get(i + 1)).getName()).startsWith("audio/")) {
					playSong(mIFileList.get(i + 1));
					currentSongIndex = i + 1;
					break;
				} else if (i == (mIFileList.size() - 1)) {
					i = -2;
				}
		}
	}

	
	/**
	 * Update timer on seekbar 
	 */
	private void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}

	/**
	 * Background Runnable thread 
	 */
	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			currentDuration = mp.getCurrentPosition();

			// Displaying Total Duration time
			songTotalDurationLabel.setText(""
					+ utils.milliSecondsToTimer(totalDuration));
			// Displaying time completed playing
			songCurrentDurationLabel.setText(""
					+ utils.milliSecondsToTimer(currentDuration));

			// Updating progress bar
			int progress = (int) (utils.getProgressPercentage(currentDuration,totalDuration));
			// Log.d("Progress", ""+progress);
			songProgressBar.setProgress(progress);

			// Running this thread after 100 milliseconds
			mHandler.postDelayed(this, 100);
		}
	};

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromTouch) {
	}

	/**
	 * When user starts moving the progress handler 
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
	}

	/**
	 * When user stops moving the progress hanlder 
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(),totalDuration);

		// forward or backward to certain seconds
		mp.seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();
	}
	
	/**
	 * If files have change or sort at current folder path, 
	 * can use this function update to list. 
	 * @param list The list content is all file at current folder path.
	 */
	public void updateTabList(List<IFile> list) {
		mIFileList = list;
		updateCurrentSongIndex();
	}

	public void setCurrentSongIndex(int index){
		currentSongIndex = index;
	}
	
	public int getCurrentSongIndex() {
		return currentSongIndex;
	}
	
	/**
	 * @return The list is all file at current folder path.
	 */
	public List<IFile> getTabList(){
		return mIFileList;
	}
	
	/**
	 * @return The list of all mp3 file at the current folder path.
	 */
	public List<IFile> getSongList(){
		return songsList;
	}
	
	/**
	 * @return The current folder path.
	 */
	public String getSongsPath(){
		return songManager.getmusicDataPath();
	}

	/**
	 * End to music player.
	 */
	public void endPlayer() {
		if (mp != null) {
			mHandler.removeCallbacks(mUpdateTimeTask);
			mp.release();
		}
	}
	
	private void updateCurrentSongIndex(){
		for (int i = 0; i < mIFileList.size(); i++) {
			try {
				if (mIFileList.get(i).getName().equals(mIFile.getName()))
					setCurrentSongIndex(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}