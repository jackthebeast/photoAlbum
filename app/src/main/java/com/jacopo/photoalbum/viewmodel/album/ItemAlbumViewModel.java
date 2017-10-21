package com.jacopo.photoalbum.viewmodel.album;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.util.Pair;
import android.view.View;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.view.gallery.GalleryActivity;

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
        Pair<View, String> title = new Pair<>(view.findViewById(R.id.album_label_title),view.getContext().getResources().getString(R.string.album_transition_title));
        Pair<View, String> username = new Pair<>(view.findViewById(R.id.album_item_by),view.getContext().getResources().getString(R.string.album_transition_username));
        GalleryActivity.launchGallery(view.getContext(), album, title, username);
    }

    public String getTitle(){
        return album.title;
    }

    public String getUserName(){
        String toReturn = context.getResources().getString(R.string.by) + " " ;
            toReturn += album.username;
        return toReturn;
    }


}
