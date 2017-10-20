package com.jacopo.photoalbum.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jacop on 19/10/2017.
 */

public class Photo implements Serializable{

    //@SerializedName("userId")
    public int userId;

    //@SerializedName("id")
    public int id;

    //@SerializedName("title")
    public String title;

    //@SerializedName("url")
    public String url;

    //@SerializedName("thumbnailUrl")
    public String thumbnailUrl;
}
