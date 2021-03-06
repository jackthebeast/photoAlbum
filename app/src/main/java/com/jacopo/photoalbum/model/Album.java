package com.jacopo.photoalbum.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jacop on 19/10/2017.
 */

public class Album implements Serializable{

    public Album(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    @SerializedName("userId")
    public int userId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("username")
    public String username;
}
