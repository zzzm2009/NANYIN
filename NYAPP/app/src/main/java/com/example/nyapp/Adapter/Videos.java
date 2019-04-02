package com.example.nyapp.Adapter;

import java.io.Serializable;

/**
 * Created by xuxiaojin on 2019/4/2.
 */

public class Videos implements Serializable {
    private String videoName;
    private String videoUrl;

    public Videos(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
