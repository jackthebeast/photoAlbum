package com.jacopo.photoalbum.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jacop on 19/10/2017.
 */

public class Album {

    @SerializedName("userId")
    public int userId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;
}
