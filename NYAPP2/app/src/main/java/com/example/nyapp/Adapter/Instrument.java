package com.example.nyapp.Adapter;

import java.io.Serializable;

/**
 * Created by xuxiaojin on 2019/4/9.
 */

public class Instrument implements Serializable {
    private int imageId;
    private String name;
    private String content;

    public Instrument(int imageId, String name, String content) {
        this.imageId = imageId;
        this.name = name;
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
