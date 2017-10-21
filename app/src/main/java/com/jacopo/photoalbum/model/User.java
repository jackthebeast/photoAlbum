package com.jacopo.photoalbum.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jacop on 21/10/2017.
 */

public class User implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("username")
    public String username;
}
