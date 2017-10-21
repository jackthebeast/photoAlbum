package com.jacopo.photoalbum.data.user;

import com.jacopo.photoalbum.data.FactoryUtils;
import com.jacopo.photoalbum.model.User;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jacop on 19/10/2017.
 */

public class UserFactory {

    public static final String USER_URL = "users";

    public static UserService create(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FactoryUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(UserService.class);
    }

    public static String buildUserPath(int userId){
        return "/" + userId;
    }

    public static User searchUserById(List<User> users, int userId) {
        for(User user : users){
            if(user.id == userId)
                return user;
        }
        return null;
    }
}
