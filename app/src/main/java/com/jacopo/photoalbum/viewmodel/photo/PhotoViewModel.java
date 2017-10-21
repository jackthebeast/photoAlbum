package com.jacopo.photoalbum.viewmodel.photo;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jacopo.photoalbum.model.Photo;

/**
 * Created by jacop on 19/10/2017.
 */

public class PhotoViewModel{

    private final Photo photo;

    private Context context;

    public PhotoViewModel(Context context, Photo photo){
        this.context = context;
        this.photo = photo;

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getImageUrl() {
        return photo.url;
    }

    public String getTitle(){
        return photo.title;
    }

}
