package com.bitech.androidsample.callback;

import android.util.Log;

import com.bitech.androidsample.utils.Logger;

import javax.inject.Inject;

/**
 * <p></p>
 * Created on 2016/4/8 11:19.
 *
 * @author Lucy
 */
public class RequestCallback<T> implements IRequestCallback<T>{

    private static final Logger logger=Logger.getLogger();

    @Override
    public void beforeRequest() {
        logger.i("-------------before http request-----------------");
    }

    @Override
    public void requestError(String msg) {
        logger.i("-------------http request error-----------------");
        logger.i(msg);
    }

    @Override
    public void requestSucess(T data) {
        logger.i("-------------http request success-----------------");
        logger.i(data);
    }

    @Override
    public void requestComplete() {
        logger.i("-------------http request complete-----------------");

    }
}
