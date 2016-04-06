package com.bitech.androidsample.base;

import com.bitech.androidsample.callback.RequestCallback;

import rx.Subscription;

/**
 * <p></p>
 * Created on 2016/4/6 16:27.
 *
 * @author Lucy
 */
public class BasePresenterImpl<T extends BaseView, V> implements BasePresenter, RequestCallback<V> {

    protected T baseView;
    protected Subscription subscription;

    public BasePresenterImpl(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void beforeRequest() {

    }

    @Override
    public void requestError(String msg) {


    }

    @Override
    public void requestComplete() {

    }

    @Override
    public void requestSucess(V data) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        baseView = null;

    }
}
