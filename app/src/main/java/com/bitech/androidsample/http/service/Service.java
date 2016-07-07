package com.bitech.androidsample.http.service;

import com.bitech.androidsample.bean.User;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * <p>使用retrofit</p>
 * Created on 2016/4/6 14:48.
 *
 * @author Lucy
 */
public interface Service {

    @GET("/User.ashx")
    Observable<User> login(@Query("name")String name, @Query("password")String password);

    @POST("/update")
    Observable<User> update(@Body User user);

    @Multipart
    @POST("/upload")
    Observable<String> upload();
}
