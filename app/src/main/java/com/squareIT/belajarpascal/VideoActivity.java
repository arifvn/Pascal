package com.squareIT.belajarpascal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.VideoAdapter;
import com.squareIT.belajarpascal.model.VideoItem;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private RecyclerView recyclerView;
    private ArrayList<VideoItem> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private VideoAdapter videoAdapter;
    private VideoItem videoItem;
    private Intent intent;
    private String vidKeyUrl;

    private final static String key = "VIDEO_ITEM";
    private String vidKey = "VIDEO_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_video_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.super.onBackPressed();
            }
        });

        setVideoItemArrayList();
        setRecyclerView();
    }

    private void setVideoItemArrayList() {
        arrayList = new ArrayList<VideoItem>();

        arrayList.add(new VideoItem("Belajar Dasar Pascal", "Video Belajar 1",
                getResources().getColor(R.color.darkGreen)));
        arrayList.add(new VideoItem("Belajar Pascal", "Video Belajar 2",
                getResources().getColor(R.color.colorAccent)));
        arrayList.add(new VideoItem("Membuat Program Rumus Persegi Panjang", "Video Belajar 3",
                getResources().getColor(R.color.red)));
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_video);

        linearLayoutManager = new LinearLayoutManager(this);

        videoAdapter = new VideoAdapter(arrayList, this);
        videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        vidKeyUrl = "PLD78F22F2FFA8850D";
                        videoItem = new VideoItem("Belajar Dasar Pascal", "Video Belajar 1",
                                getResources().getColor(R.color.darkGreen));
                        intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                        intent.putExtra(key, videoItem);
                        intent.putExtra(vidKey, vidKeyUrl);
                        startActivity(intent);
                        break;
                    case 1:
                        vidKeyUrl = "PL866mefiQUItRL3itvAe4NEz6H6eyw4hT";
                        videoItem = new VideoItem("Belajar Pascal", "Video Belajar 2",
                                getResources().getColor(R.color.colorAccent));
                        intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                        intent.putExtra(key, videoItem);
                        intent.putExtra(vidKey, vidKeyUrl);
                        startActivity(intent);
                        break;
                    case 2:
                        vidKeyUrl = "UU8dMHYPqimfh3cWLoj42I0A";
                        videoItem = new VideoItem("Membuat Program Rumus Persegi Panjang", "Video Belajar 3",
                                getResources().getColor(R.color.red));
                        intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                        intent.putExtra(key, videoItem);
                        intent.putExtra(vidKey, vidKeyUrl);
                        startActivity(intent);
                        break;
                }
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(videoAdapter);
    }


}
