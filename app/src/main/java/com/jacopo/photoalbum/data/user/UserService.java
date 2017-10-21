package com.jacopo.photoalbum.data.user;

import com.jacopo.photoalbum.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jacop on 19/10/2017.
 */

public interface UserService {

    @GET
    Observable<List<User>> fetchUsers(@Url String url);
}
