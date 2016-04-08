package com.bitech.androidsample.module.main.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.bitech.androidsample.R;
import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.base.BaseActivity;
import com.bitech.androidsample.module.main.presenter.LoginPresener;
import com.bitech.androidsample.module.main.view.ILoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityInject(contentViewId = R.layout.activity_main, isSlidr = false)
public class MainActivity extends BaseActivity implements ILoginView {

    @Inject
    LoginPresener loginPresener;

    @Bind(R.id.content_main_textview)
    TextView contentMainTextview;

    @Override
    protected void initView() {

        initInject();

        contentMainTextview.setText("buffer knife--");

        loginPresener.login("fuc", "a123456");
    }

    private void initInject() {
        getActivityComponent().inject(this);
        loginPresener.attacthView(this);//与Activity关联
    }

    //登录成功与失败的判断
    @Override
    public void login(boolean isSuccess) {


    }

}
