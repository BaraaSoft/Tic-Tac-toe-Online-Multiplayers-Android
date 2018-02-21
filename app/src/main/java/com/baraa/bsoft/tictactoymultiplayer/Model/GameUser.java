package com.baraa.bsoft.tictactoymultiplayer.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by baraa on 17/02/2018.
 */

@IgnoreExtraProperties
public class GameUser implements Serializable {
    private String name;
    private String id;
    private boolean online;
    private String imgUrl;
    private long score;
    private String friends;
    public GameUser(){

    }


    public GameUser(String name, String id) {
        this();
        this.name = name;
        this.id = id;
        this.online = false;
        this.score =0;
    }


    @Override
    public boolean equals(Object obj) {

        boolean result = false;
        if (obj == null || obj.getClass() != getClass()) {
            result = false;
        } else {
            GameUser gameUser = (GameUser) obj;
            if (this.id == gameUser.getId()) {
                result = true;
            }
        }
        return result;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isOnline() {
        return online;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }
}
