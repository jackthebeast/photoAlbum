package com.jacopo.photoalbum.data.album;

import com.jacopo.photoalbum.data.FactoryUtils;
import com.jacopo.photoalbum.data.user.UserFactory;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jacop on 19/10/2017.
 */

public class AlbumFactory {

    public static final String ALBUM_URL = "albums";

    public static AlbumService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FactoryUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(AlbumService.class);
    }

    public static void matchWhithAlbums(List<Album> albumList, List<User> users){
        for(Album album : albumList){
            User user = UserFactory.searchUserById(users, album.userId);
            if(user == null)
                album.username = "Unknown";
            else
                album.username = user.username;
        }
    }
}
