package com.jacopo.photoalbum.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jacop on 19/10/2017.
 */

public class Photo implements Serializable{

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    @SerializedName("albumId")
    public int albumId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;
}
