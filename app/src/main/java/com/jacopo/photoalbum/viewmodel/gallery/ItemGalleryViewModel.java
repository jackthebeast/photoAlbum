package com.jacopo.photoalbum.viewmodel.gallery;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.Photo;
import com.jacopo.photoalbum.view.photo.PhotoActivity;

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

    @BindingAdapter({"thumbnailUrl"})
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
        Pair<View, String> image = new Pair<>(view.findViewById(R.id.gallery_item_icon),view.getContext().getResources().getString(R.string.photo_transition_image));
        Pair<View, String> text = new Pair<>(view.findViewById(R.id.gallery_label_title),view.getContext().getResources().getString(R.string.photo_transition_text));
        PhotoActivity.launchPhoto(context, photo, image, text);
    }


    public String getTitle(){
        return photo.title;
    }


}
