package com.example.nyapp.Activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nyapp.Adapter.VideoAdapter;
import com.example.nyapp.Adapter.Videos;
import com.example.nyapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    private List<Videos> mvideoList=new ArrayList<>();
    private ListView videoListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mvideoList.clear();
        initVideoList();

        /**
         * 导航栏
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.video_list_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        VideoAdapter videoAdapter=new VideoAdapter(VideoActivity.this,R.layout.video_list,mvideoList);
        videoListView= ((ListView) findViewById(R.id.video_listview));
        videoListView.setAdapter(videoAdapter);

        /**
         * videoListView item 点击事件
         */
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(VideoActivity.this, VideoSituationActivity.class);
                Videos video=mvideoList.get(i);
                intent.putExtra("video", (Serializable) video);
                startActivity(intent);
            }
        });
    }

    private void initVideoList() {
        for (int i=1;i<=10;i++){
            String videoname=new String("视频");
            Videos videos=new Videos(videoname+i);
            mvideoList.add(videos);
        }
    }
}
