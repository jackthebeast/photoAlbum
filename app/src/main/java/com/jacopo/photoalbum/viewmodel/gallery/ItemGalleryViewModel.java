package com.jacopo.photoalbum.viewmodel.gallery;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.Photo;

/**
 * Created by jacop on 19/10/2017.
 */

public class ItemGalleryViewModel extends BaseObservable {

    private Photo photo;
    private Context context;

    public ItemGalleryViewModel( Context context, Photo photo){
        this.context = context;
        this.photo = photo;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadThumbnail(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
        int width = ((View)imageView.getParent()).getWidth();
        imageView.getLayoutParams().width = width;
        imageView.getLayoutParams().height = width;
    }

    public String getThumbnailUrl() {
        return photo.thumbnailUrl;
    }


    public void setPhoto(Photo photo){
        this.photo = photo;
        notifyChange();
    }

    public void onItemClick(View view) {
        //TODO
    }


    public String getTitle(){
        return photo.title;
    }


}
