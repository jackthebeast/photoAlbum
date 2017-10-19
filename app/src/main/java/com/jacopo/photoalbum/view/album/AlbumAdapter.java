package com.jacopo.photoalbum.view.album;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.databinding.ItemAlbumBinding;
import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.viewmodel.album.ItemAlbumViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacop on 19/10/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumAdapterViewHolder> {

    private List<Album> albumList;

    public AlbumAdapter(){
        this.albumList = new ArrayList<>();
    }

    public static class AlbumAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemAlbumBinding itemAlbumBinding;

        public AlbumAdapterViewHolder(ItemAlbumBinding itemAlbumBinding){
            super(itemAlbumBinding.itemAlbum);
            this.itemAlbumBinding = itemAlbumBinding;
        }

        void bindAlbum(Album album){
            if(itemAlbumBinding.getAlbumViewModel() == null){
                itemAlbumBinding.setAlbumViewModel(new ItemAlbumViewModel(itemView.getContext(), album ));
            }else{
                itemAlbumBinding.getAlbumViewModel().setAlbum(album);
            }
        }
    }

    @Override
    public void onBindViewHolder(AlbumAdapterViewHolder holder, int position){
        holder.bindAlbum(albumList.get(position));
    }

    @Override
    public AlbumAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_album, parent, false);
        return new AlbumAdapterViewHolder(itemAlbumBinding);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setAlbumList(List<Album> albumList){
        this.albumList = albumList;
        notifyDataSetChanged();
    }
}
