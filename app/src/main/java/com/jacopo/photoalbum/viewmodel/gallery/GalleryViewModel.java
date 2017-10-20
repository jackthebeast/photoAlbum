package com.jacopo.photoalbum.viewmodel.gallery;

import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

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

public class GalleryViewModel extends Observable{

    private final Album album;
    public ObservableInt galleryProgress;
    public ObservableInt galleryRecycler;
    
    private List<Photo> galleryList;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public GalleryViewModel(Context context, Album album){
        this.context = context;
        this.galleryList = new ArrayList<>();
        galleryRecycler = new ObservableInt(View.GONE);
        galleryProgress = new ObservableInt(View.GONE);
        compositeDisposable = new CompositeDisposable();
        this.album = album;

        initializeViews();
        fetchGalleryList();
    }
    
    private void initializeViews(){
        galleryRecycler.set(View.GONE);
        galleryProgress.set(View.VISIBLE);
    }

    private void setGalleryDataSet(List<Photo> photos){
        galleryList.addAll(photos);
        setChanged();
        notifyObservers();
    }

    public List<Photo> getGalleryList() {
        return galleryList;
    }

    private void fetchGalleryList() {

        AlbumApplication albumApplication = AlbumApplication.createApp(context);
        PhotoService photoService = albumApplication.getPhotoService();

        Disposable disposable = photoService.fetchGallery(FactoryUtils.BASE_URL + PhotoFactory.PHOTO_URL + PhotoFactory.buildAlbumQueryParam(album.id))
                .subscribeOn(albumApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Photo>>() {
                    @Override
                    public void accept(List<Photo> photos) throws Exception {
                        setGalleryDataSet(photos);
                        galleryProgress.set(View.GONE);
                        galleryRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(context, context.getResources().getText(R.string.loading_error), Toast.LENGTH_LONG).show();
                        galleryProgress.set(View.GONE);
                        galleryRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    
}
