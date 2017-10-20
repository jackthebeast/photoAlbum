package com.jacopo.photoalbum.data.photo;

import com.jacopo.photoalbum.data.FactoryUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jacop on 19/10/2017.
 */

public class PhotoFactory {

    public static final String PHOTO_URL = "photos";
    private static final String PARAM_QUERY_ALBUM = "albumId";

    public static PhotoService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FactoryUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PhotoService.class);
    }

    public static String buildAlbumQueryParam(int albumId){
        return "?" + PARAM_QUERY_ALBUM + "=" + albumId;
    }
}
