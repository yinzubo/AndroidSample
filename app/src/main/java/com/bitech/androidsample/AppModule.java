package com.bitech.androidsample;

import android.content.Context;

import com.bitech.androidsample.app.App;
import com.bitech.androidsample.http.manager.RetrofitManager;

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
    Context providerContext(){
        return app;
    }

    @Provides
    RetrofitManager providerRetrofitManager(){
        return new RetrofitManager();
    }
}
