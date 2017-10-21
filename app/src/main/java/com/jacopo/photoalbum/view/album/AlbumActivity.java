package com.jacopo.photoalbum.view.album;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.databinding.AlbumActivityBinding;
import com.jacopo.photoalbum.viewmodel.album.AlbumViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jacop on 19/10/2017.
 */

public class AlbumActivity extends AppCompatActivity implements Observer {

    private AlbumActivityBinding albumActivityBinding;
    private AlbumViewModel albumViewModel;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        initDataBinding();
        setupObserver(albumViewModel);
        setupAlbumListView(albumActivityBinding.listAlbum);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setupAlbumListView(RecyclerView listAlbum){
        AlbumAdapter adapter = new AlbumAdapter();
        listAlbum.setAdapter(adapter);
        listAlbum.setLayoutManager(new LinearLayoutManager(this));
        listAlbum.addItemDecoration(new AlbumDivider(this));
    }

    private void initDataBinding(){
        albumActivityBinding = DataBindingUtil.setContentView(this, R.layout.album_activity);
        albumViewModel = new AlbumViewModel(this);
        albumActivityBinding.setMainViewModel(albumViewModel);
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof AlbumViewModel) {
            AlbumAdapter albumAdapter = (AlbumAdapter) albumActivityBinding.listAlbum.getAdapter();
            AlbumViewModel albumViewModel = (AlbumViewModel) observable;
            albumAdapter.setAlbumList(albumViewModel.getAlbumList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        albumViewModel.reset();
    }

}
