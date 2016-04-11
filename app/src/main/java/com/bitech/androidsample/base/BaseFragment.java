package com.bitech.androidsample.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Annotation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthonycr.grant.PermissionsManager;
import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.app.App;
import com.squareup.leakcanary.RefWatcher;

/**
 * <p></p>
 * Created on 2016/4/11 13:33.
 *
 * @author Lucy
 */

public abstract class BaseFragment extends Fragment implements BaseView {


    protected int contentViewId;
    protected View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getClass().isAnnotationPresent(ActivityInject.class)){
            ActivityInject annotation=getClass().getAnnotation(ActivityInject.class);
            contentViewId=annotation.contentViewId();
        }else{
            throw  new RuntimeException("Class must add annotations of ActivityInject.class");
        }
        contentView=inflater.inflate(contentViewId,null);
        initView(contentView);
        return contentView;
    }

    protected abstract  void initView(View contentView);


    public BaseFragment(){
        //监控
        RefWatcher refWatcher= App.getRefWatcher();
        refWatcher.watch(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults);
    }
}
