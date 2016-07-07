package com.bitech.androidsample;

import android.content.Context;
import android.util.Log;

import com.bitech.androidsample.app.App;
import com.bitech.androidsample.http.manager.RetrofitManager;
import com.bitech.androidsample.http.service.Service;
import com.bitech.androidsample.utils.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <p></p>
 * Created on 2016/4/7 14:12.
 *
 * @author Lucy
 */
@Module
public class AppModule {

    private final App app;

    public AppModule(App app){
        this.app=app;
    }

    @Provides
    @Singleton
    Context context(){
        return app;
    }

    @Provides
    @Singleton
    Service providerApiService(RetrofitManager retrofitManager){
        return retrofitManager.getService();
    }

}
