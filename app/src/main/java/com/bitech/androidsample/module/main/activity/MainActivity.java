package com.bitech.androidsample.module.main.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.bitech.androidsample.R;
import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.base.BaseActivity;
import com.bitech.androidsample.module.main.presenter.LoginPresener;
import com.bitech.androidsample.module.main.view.ILoginView;
import com.bitech.androidsample.utils.Rxbus;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

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

       // loginPresener.login("fuc", "a123456");

        Observable<String> observable=Rxbus.getInstance().register("login");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                logger.i("----------login rxbus:=="+s);
            }
        });

        Rxbus.getInstance().post("login","login---------------rxbus");

        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, new String[]{}, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                //申请成功
            }

            @Override
            public void onDenied(String permission) {
                //申请失败
            }
        });
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
