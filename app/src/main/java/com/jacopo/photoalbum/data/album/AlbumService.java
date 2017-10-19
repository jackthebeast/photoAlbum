package com.jacopo.photoalbum.data.album;

import com.jacopo.photoalbum.model.Album;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jacop on 19/10/2017.
 */

public interface AlbumService {

    @GET
    Observable<List<Album>> fetchAlbum(@Url String url);
}
