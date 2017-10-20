package com.jacopo.photoalbum.view.gallery;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.databinding.GalleryActivityBinding;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.viewmodel.gallery.GalleryViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jacop on 19/10/2017.
 */

public class GalleryActivity extends AppCompatActivity implements Observer {

    private static final String PARAM_ALBUM = "PARAM_ALBUM";

    private GalleryActivityBinding galleryActivityBinding;
    private GalleryViewModel galleryViewModel;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        getIntentExtra();

        initDataBinding();
        setupObserver(galleryViewModel);
        setupGalleryListView(galleryActivityBinding.listGallery);
    }

    private void getIntentExtra() {
        Album album = (Album) getIntent().getSerializableExtra(PARAM_ALBUM);
        if(album == null)
           album = new Album(1,1,"titolo");

        this.album = album;
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setupGalleryListView(RecyclerView listGallery){
        GalleryAdapter adapter = new GalleryAdapter();
        listGallery.setAdapter(adapter);
        listGallery.setLayoutManager(new GridLayoutManager(this, calculateNumberOfColumns()));
    }

    private int calculateNumberOfColumns() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //getDimension returns a value already scaled by device's density
        return (int) (metrics.widthPixels / getResources().getDimension(R.dimen.photo_thumbnail));
    }

    public static void launchGallery(Context context, Album album) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(PARAM_ALBUM, album);
        context.startActivity(intent);
    }

    private void initDataBinding(){
        galleryActivityBinding = DataBindingUtil.setContentView(this, R.layout.gallery_activity);
        galleryViewModel = new GalleryViewModel(this, album);
        galleryActivityBinding.setMainViewModel(galleryViewModel);
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof GalleryViewModel) {
            GalleryAdapter galleryAdapter = (GalleryAdapter) galleryActivityBinding.listGallery.getAdapter();
            GalleryViewModel galleryViewModel = (GalleryViewModel) observable;
            galleryAdapter.setGalleryList(galleryViewModel.getGalleryList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        galleryViewModel.reset();
    }

}
