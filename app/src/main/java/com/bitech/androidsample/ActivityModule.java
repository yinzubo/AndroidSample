package com.bitech.androidsample;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * <p></p>
 * Created on 2016/4/7 14:15.
 *
 * @author Lucy
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity=activity;
    }

    @Provides
    @ActivityScope
    Activity activity(){
        return activity;
    }
}
