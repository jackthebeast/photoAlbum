package com.jacopo.photoalbum.viewmodel.album;

import android.content.Context;
import android.databinding.BaseObservable;
import android.renderscript.BaseObj;
import android.view.View;

import com.jacopo.photoalbum.model.Album;

/**
 * Created by jacop on 19/10/2017.
 */

public class ItemAlbumViewModel extends BaseObservable {

    private Album album;
    private Context context;

    public ItemAlbumViewModel( Context context, Album album){
        this.context = context;
        this.album = album;
    }

    public void setAlbum(Album album){
        this.album = album;
        notifyChange();
    }

    public void onItemClick(View view) {
        //TODO
    }


    public String getTitle(){
        return album.title;
    }


}
