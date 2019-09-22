package com.squareIT.belajarpascal;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareIT.belajarpascal.model.VideoItem;

public class VideoDetailActivity extends YouTubeBaseActivity {

    private static final String TAG = "VideoDetailActivity";

    private ImageView imageViewBackButton;

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private TextView titleVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        titleVideo = (TextView) findViewById(R.id.title_detail_video);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/montserrat.ttf");
        titleVideo.setTypeface(type);

        VideoItem videoItem = getIntent().getParcelableExtra("VIDEO_ITEM");

        titleVideo.setText(videoItem.getVideoTitle());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_video_detail_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoDetailActivity.super.onBackPressed();
            }
        });

        //Youtube
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadPlaylist(getIntent().getStringExtra("VIDEO_ID"));
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                Log.e(TAG, "onInitializationSuccess: " + "success");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "onInitializationFailure: " + "failed");
            }
        };

        youTubePlayerView.initialize(VideoItem.getApiKey(), onInitializedListener);
    }
}
