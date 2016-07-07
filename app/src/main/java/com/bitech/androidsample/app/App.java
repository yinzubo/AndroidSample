package com.bitech.androidsample.app;

import android.app.Application;
import android.content.Context;

import com.bitech.androidsample.AppComponent;
import com.bitech.androidsample.AppModule;
import com.bitech.androidsample.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * <p></p>
 * Created on 2016/4/5 14:06.
 *
 * @author Lucy
 */
public class App extends Application {

    private static App mInstance;//App的实例
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        //内存泄漏监测
        refWatcher= LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(){
        return mInstance.refWatcher;
    }
    public static Context getContext() {
        return mInstance;
    }

    //设置Dagger，与Application相关联，生命周期与其一样
    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(mInstance)).build();
    }
}
