package com.bitech.androidsample.module.main.model;

import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.callback.RequestCallback;
import com.bitech.androidsample.http.manager.RetrofitManager;
import com.bitech.androidsample.module.main.presenter.LoginPresenerImpl;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * <p>使用RxJava来异步操作网络访问</p>
 * Created on 2016/4/6 16:42.
 *
 * @author Lucy
 */
public class LoginInterceptorImpl implements ILoginInterceptor<User>{


    private RetrofitManager retrofitManager;

    public LoginInterceptorImpl(){
        this.retrofitManager=RetrofitManager.builder();
    }
    @Override
    public Subscription login(String name, String password, final RequestCallback<User> callback) {

        return retrofitManager.login(name,password).doOnSubscribe(new Action0() {
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
