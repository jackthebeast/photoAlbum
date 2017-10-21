package com.jacopo.photoalbum.view.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.databinding.GalleryActivityBinding;
import com.jacopo.photoalbum.databinding.PhotoActivityBinding;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.Photo;
import com.jacopo.photoalbum.view.gallery.GalleryAdapter;
import com.jacopo.photoalbum.viewmodel.gallery.GalleryViewModel;
import com.jacopo.photoalbum.viewmodel.photo.PhotoViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jacop on 19/10/2017.
 */

public class PhotoActivity extends AppCompatActivity {

    private static final String PARAM_PHOTO = "PARAM_PHOTO";

    private PhotoActivityBinding photoActivityBinding;
    private PhotoViewModel photoViewModel;
    private Photo photo;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        getIntentExtra();

        initDataBinding();
    }

    private void getIntentExtra() {
        Photo photo = (Photo) getIntent().getSerializableExtra(PARAM_PHOTO);
        if(photo == null)   //fake data for developing purpouse
            photo = new Photo(1,1,"title","http://placehold.it/600/92c952", "http://placehold.it/150/92c952");

        this.photo = photo;
    }

    public static void launchPhoto(Context context, Photo photo, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(PARAM_PHOTO, photo);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, sharedElements );

        context.startActivity(intent, options.toBundle());
    }

    private void initDataBinding(){
        photoActivityBinding = DataBindingUtil.setContentView(this, R.layout.photo_activity);
        photoViewModel = new PhotoViewModel(this, photo);
        photoActivityBinding.setMainViewModel(photoViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
