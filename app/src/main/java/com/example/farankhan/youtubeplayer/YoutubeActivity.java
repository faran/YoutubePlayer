package com.example.farankhan.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
            implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyDZKnbjRJDwsI2Jcy4qpEZFzcIpEsRXWRE";
    static final String YOUTUBE_VIDEO_ID = "twamPxxwsTw";
    static final String YOUTUBE_PLAYLIST = "PLQ-Vwkr4PmmtYJu5ADB9BgMSX5br8WtzK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

//        Button button1 = new Button(this);
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
//        button1.setText("Button added");
//        layout.addView(button1);


        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitialisationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "Initialised youtube player successfully", Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();

        }else {
            String errorMessage = String.format("There was an error initialising the youtube player(%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "Good, video is playing ok", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Video is paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "Video is stoped", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Click ad now and make video creator richer", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "video has started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "Congrats another video watched", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };


}
