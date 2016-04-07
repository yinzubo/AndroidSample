package com.bitech.androidsample.app;

import android.app.Application;
import android.content.Context;

import com.bitech.androidsample.AppComponent;
import com.bitech.androidsample.AppModule;
import com.bitech.androidsample.DaggerAppComponent;

/**
 * <p></p>
 * Created on 2016/4/5 14:06.
 *
 * @author Lucy
 */
public class App extends Application {

    private static App mInstance;//App的实例

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }


    public  static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(mInstance)).build();
    }
}
