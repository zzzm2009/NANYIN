package com.example.nyapp.Adapter;

/**
 * Created by xuxiaojin on 2019/4/9.
 */

public class Club {
    private String clubname;
    private String clubaddress;

    public Club(String clubname, String clubaddress) {
        this.clubname = clubname;
        this.clubaddress = clubaddress;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getClubaddress() {
        return clubaddress;
    }

    public void setClubaddress(String clubaddress) {
        this.clubaddress = clubaddress;
    }
}
