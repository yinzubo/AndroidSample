package com.bitech.androidsample.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import com.bitech.androidsample.ActivityComponent;
import com.bitech.androidsample.ActivityModule;
import com.bitech.androidsample.BuildConfig;
import com.bitech.androidsample.DaggerActivityComponent;
import com.bitech.androidsample.R;

import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.app.App;
import com.bitech.androidsample.app.AppManager;
import com.bitech.androidsample.utils.Logger;
import com.bitech.androidsample.utils.slidr.SlidrUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * <p></p>
 * Created on 2016/4/5 13:47.
 *
 * @author Lucy
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public static final Logger logger= Logger.getLogger();

    private int contentViewId;
    private boolean isSlidr;//是否开启滑动关闭

    private Toolbar toolbar;
    private String toolBarTitle;
    private int toolBarIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        App.getRefWatcher().watch(this);//监测Activity运行时是否发生内存泄漏

        if (getClass().isAnnotationPresent(ActivityInject.class)) {
            ActivityInject annotation = getClass().getAnnotation(ActivityInject.class);
            contentViewId = annotation.contentViewId();
            isSlidr = annotation.isSlidr();
            toolBarTitle = annotation.toolBarTitle();
            toolBarIndicator = annotation.toolBarIndicator();

        } else {
            throw new RuntimeException("there is a runtime exception,because activity has to annotation @ActivityInject");
        }

        //是否开启苛责模式
/*        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }*/

        logger.i("设置contentViewId:" + contentViewId);
        setContentView(contentViewId);
        ButterKnife.bind(this);
        //初始化toolbar
        initToolbar();
        if (isSlidr) {
            SlidrUtil.initSlidrDefaultConfig(this, false);
        }

        initView();
    }

    //初始化toolbar
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle(toolBarTitle);
            if (toolBarIndicator != -1) {
                getSupportActionBar().setHomeAsUpIndicator(toolBarIndicator);
            } else {
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_back);
            }
        }
    }

    protected void setToolBarTitle(String toolBarTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(toolBarTitle);
        }
    }

    protected void setToolBarTitle(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(resId);
        }
    }

    protected void setToolBarIndicator(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(resId);
        }
    }

    protected ActionBar getToolbar() {
        return getSupportActionBar();
    }

    protected abstract void initView();

    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(String className) {
        startActivity(className, null);
    }

    protected void startActivity(String className, Bundle bundle) {
        Intent intent = new Intent(className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgess() {

    }

    //使用Dagger2的设置
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getAppComponent())
                .build();
    }
}
