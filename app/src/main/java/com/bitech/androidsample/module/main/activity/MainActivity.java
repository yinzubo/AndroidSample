package com.bitech.androidsample.module.main.activity;


import com.bitech.androidsample.R;
import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.base.BaseActivity;
import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.http.manager.RetrofitManager;
import com.bitech.androidsample.module.main.presenter.LoginPresenerImpl;
import com.bitech.androidsample.module.main.view.ILoginView;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@ActivityInject(contentViewId = R.layout.activity_main, isSlidr = false)
public class MainActivity extends BaseActivity implements ILoginView {

    @Inject
    LoginPresenerImpl loginPresener;

    @Override
    protected void initView() {

        initInject();

        logger.i("LoginPresenerImpl="+loginPresener);
        loginPresener.login("fuc","a123456");
    }

    private void initInject(){
        getActivityComponent().inject(this);
        loginPresener.attacthView(this);//与Activity关联
    }
    //登录成功与失败的判断
    @Override
    public void login(boolean isSuccess) {

        logger.i("MainActivity----请求成功----"+isSuccess);
    }
}
