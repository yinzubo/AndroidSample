package com.bitech.androidsample.http.manager;

import com.bitech.androidsample.app.App;
import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.http.service.Service;
import com.bitech.androidsample.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>网络请求的管理类</p>
 * Created on 2016/4/6 14:57.
 *
 * @author Lucy
 */
public class RetrofitManager {

    //缓存的有效期
    public static final long CACHE_STALE_ESC = 60 * 60 * 24 * 2;
    //cache-control设置，only-if-cache请求缓存而不请求服务器，配合max-stale使用
    public static final String CACHE_CONTROL_CACHE = "only-if-cached,max-stale=" + CACHE_STALE_ESC;
    //cache-control设置，max-age=0时不会使用缓存直接请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private Service service;

    private static OkHttpClient okHttpClient;

    public static RetrofitManager builder() {
        return new RetrofitManager();
    }

    //初始化
    private RetrofitManager() {

        initOkHttpClient();
        //使用Okhttp 以及rxjava做回调
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getHost()).client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        //实例化Service对象
        service = retrofit.create(Service.class);
    }

    //初始化OkhttpClient
    private void initOkHttpClient() {

        if (okHttpClient == null) {
            synchronized (RetrofitManager.class) {
                File fileCache = new File(App.getContent().getCacheDir(), "httpCache");
                Cache cache = new Cache(fileCache, 1024 * 1024 * 100);

                Interceptor rewriteCacheControlInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //无网络连接
                        if (!NetUtil.isConnected(App.getContent())) {
                            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                        }

                        Response response = chain.proceed(request);
                        if (NetUtil.isConnected(App.getContent())) {
                            //有网络时
                            String cacheControl = request.cacheControl().toString();
                            return response.newBuilder().header("Cache-Control", cacheControl).removeHeader("Prama").build();
                        } else {
                            //无网络时
                            return response.newBuilder().header("Cache-Control", "public,only-if-cache," + CACHE_STALE_ESC).removeHeader("Prama").build();
                        }

                    }
                };

                okHttpClient=new OkHttpClient.Builder().cache(cache)
                        .addNetworkInterceptor(rewriteCacheControlInterceptor)
                        .addInterceptor(rewriteCacheControlInterceptor)
                        .connectTimeout(30, TimeUnit.SECONDS).build();
            }
        }
    }

    private String getHost() {

        return "";
    }

    public Observable<User> login(String name,String password){
        return service.login(name,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    public Observable<User> update(User user){
        return service.update(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
