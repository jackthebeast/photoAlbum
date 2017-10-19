package com.jacopo.photoalbum;

import android.app.Application;
import android.content.Context;

import com.jacopo.photoalbum.data.album.AlbumFactory;
import com.jacopo.photoalbum.data.album.AlbumService;
import com.jacopo.photoalbum.model.Album;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jacop on 19/10/2017.
 */

public class AlbumApplication extends Application{

    private AlbumService albumService;
    private Scheduler scheduler;

    public static AlbumApplication getApp(Context context){
        return (AlbumApplication) context.getApplicationContext();
    }


    public static AlbumApplication createApp(Context context) {
        return AlbumApplication.getApp(context);
    }
    public Scheduler  subscribeScheduler(){
        if(scheduler == null)
            scheduler = Schedulers.io();
        return scheduler;
    }

    public AlbumService getAlbumService(){
        if(albumService == null)
            albumService = AlbumFactory.create();
        return albumService;
    }


}
