package com.jacopo.photoalbum.viewmodel.photo;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jacopo.photoalbum.AlbumApplication;
import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.data.FactoryUtils;
import com.jacopo.photoalbum.data.photo.PhotoFactory;
import com.jacopo.photoalbum.data.photo.PhotoService;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
