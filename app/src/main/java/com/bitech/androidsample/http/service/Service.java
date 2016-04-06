package com.bitech.androidsample.http.service;

import com.bitech.androidsample.bean.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <p>使用retrofit</p>
 * Created on 2016/4/6 14:48.
 *
 * @author Lucy
 */
public interface Service {

    @GET("/login")
    Observable<User> login(@Query("name")String name,@Query("password")String password);

    @POST("/update")
    Observable<User> update(@Body User user);
}
