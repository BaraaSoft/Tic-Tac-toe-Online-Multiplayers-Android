package com.baraa.bsoft.tictactoymultiplayer.Model;

import java.io.Serializable;

/**
 * Created by baraa on 17/02/2018.
 */

public class GameUser implements Serializable {
    private String name;
    private String id;
    private boolean online;
    private String imgUrl;
    public GameUser(){

    }

    public GameUser(String name, String id) {
        this();
        this.name = name;
        this.id = id;
        this.online = false;
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
}
