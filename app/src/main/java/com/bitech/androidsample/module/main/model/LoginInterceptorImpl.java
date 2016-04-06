package com.bitech.androidsample.module.main.model;

import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.callback.RequestCallback;
import com.bitech.androidsample.http.manager.RetrofitManager;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * <p></p>
 * Created on 2016/4/6 16:42.
 *
 * @author Lucy
 */
public class LoginInterceptorImpl implements ILoginInterceptor<User>{

    @Override
    public Subscription login(String name, String password, final RequestCallback<User> callback) {
        return RetrofitManager.builder().login(name,password).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                callback.beforeRequest();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        callback.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        callback.requestSucess(user);
                    }
                });
    }
}
