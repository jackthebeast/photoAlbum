package com.jacopo.photoalbum.viewmodel.album;

import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.jacopo.photoalbum.AlbumApplication;
import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.data.FactoryUtils;
import com.jacopo.photoalbum.data.album.AlbumFactory;
import com.jacopo.photoalbum.data.album.AlbumService;
import com.jacopo.photoalbum.model.Album;

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

public class AlbumViewModel extends Observable{
    
    public ObservableInt albumProgress;
    public ObservableInt albumRecycler;
    
    private List<Album> albumList;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public AlbumViewModel(Context context){
        this.context = context;
        this.albumList = new ArrayList<>();
        albumRecycler = new ObservableInt(View.GONE);
        albumProgress = new ObservableInt(View.GONE);
        compositeDisposable = new CompositeDisposable();

        initializeViews();
        fetchAlbumList();
    }
    
    private void initializeViews(){
        albumRecycler.set(View.GONE);
        albumProgress.set(View.VISIBLE);
    }

    private void setAlbumDataSet(List<Album> albums){
        albumList.addAll(albums);
        setChanged();
        notifyObservers();
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    private void fetchAlbumList() {

        AlbumApplication albumApplication = AlbumApplication.createApp(context);
        AlbumService albumService = albumApplication.getAlbumService();

        Disposable disposable = albumService.fetchAlbum(FactoryUtils.BASE_URL + AlbumFactory.ALBUM_URL)
                .subscribeOn(albumApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Album>>() {
                    @Override
                    public void accept(List<Album> albums) throws Exception {
                        setAlbumDataSet(albums);
                        albumProgress.set(View.GONE);
                        albumRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(context, context.getResources().getText(R.string.loading_error), Toast.LENGTH_LONG).show();
                        albumProgress.set(View.GONE);
                        albumRecycler.set(View.GONE);
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
