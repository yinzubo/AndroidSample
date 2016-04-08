package com.bitech.androidsample;

import android.content.Context;

import com.bitech.androidsample.http.manager.RetrofitManager;
import com.bitech.androidsample.http.service.Service;
import com.bitech.androidsample.utils.Logger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <p>单例，全局使用
 * 可以在此定义一些全局参数
 * 例如SharedPreferences
 * 数据库的操作，单例的网络连接等
 * </p>
 * Created on 2016/4/7 14:03.
 *
 * @author Lucy
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();
    //网络访问的接口
    Service providerApiService();

}
