package com.jacopo.photoalbum.view.gallery;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jacopo.photoalbum.R;
import com.jacopo.photoalbum.databinding.ItemGalleryBinding;
import com.jacopo.photoalbum.model.Photo;
import com.jacopo.photoalbum.viewmodel.gallery.ItemGalleryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacop on 19/10/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryAdapterViewHolder> {

    private List<Photo> galleryList;

    public GalleryAdapter(){
        this.galleryList = new ArrayList<>();
    }

    public static class GalleryAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemGalleryBinding itemGalleryBinding;

        public GalleryAdapterViewHolder(ItemGalleryBinding itemGalleryBinding){
            super(itemGalleryBinding.itemGallery);
            this.itemGalleryBinding = itemGalleryBinding;
        }

        void bindPhoto(Photo photo){
            if(itemGalleryBinding.getGalleryViewModel() == null){
                itemGalleryBinding.setGalleryViewModel(new ItemGalleryViewModel(itemView.getContext(), photo ));
            }else{
                itemGalleryBinding.getGalleryViewModel().setPhoto(photo);
            }
        }
    }

    @Override
    public void onBindViewHolder(GalleryAdapterViewHolder holder, int position){
        holder.bindPhoto(galleryList.get(position));
    }

    @Override
    public GalleryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGalleryBinding itemGalleryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gallery, parent, false);
        return new GalleryAdapterViewHolder(itemGalleryBinding);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public void setGalleryList(List<Photo> galleryList){
        this.galleryList = galleryList;
        notifyDataSetChanged();
    }
}
