package com.bitech.androidsample;

import android.app.Activity;

import com.bitech.androidsample.module.main.activity.MainActivity;

import dagger.Component;

/**
 * <p></p>
 * Created on 2016/4/7 14:14.
 *
 * @author Lucy
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();

    void inject(MainActivity mainActivity);
}
