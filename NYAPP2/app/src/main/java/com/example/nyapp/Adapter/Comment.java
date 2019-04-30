package com.example.nyapp.Adapter;

import java.util.Date;

/**
 * Created by xuxiaojin on 2019/4/30.
 */

public class Comment {
    private  int commentID;
    private  String username;
    private  String context;
    private  String commenttime;

    public Comment(int commentID, String username, String context, String commenttime) {
        this.commentID = commentID;
        this.username = username;
        this.context = context;
        this.commenttime = commenttime;
    }

    public Comment(String username, String context, String commenttime) {
        this.username = username;
        this.context = context;
        this.commenttime = commenttime;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }
}
