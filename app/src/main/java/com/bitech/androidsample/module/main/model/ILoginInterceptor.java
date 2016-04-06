package com.bitech.androidsample.module.main.model;

import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.callback.RequestCallback;

import rx.Observable;
import rx.Subscription;

/**
 * <p></p>
 * Created on 2016/4/6 16:41.
 *
 * @author Lucy
 */
public interface ILoginInterceptor<T> {

    Subscription login(String name, String password, RequestCallback<T> callback);
}
