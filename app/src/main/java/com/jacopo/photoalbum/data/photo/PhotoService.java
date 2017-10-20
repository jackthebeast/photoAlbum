package com.jacopo.photoalbum.data.photo;

import com.jacopo.photoalbum.model.Album;
import com.jacopo.photoalbum.model.Photo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jacop on 19/10/2017.
 */

public interface PhotoService {

    @GET
    Observable<List<Photo>> fetchGallery(@Url String url);
}
