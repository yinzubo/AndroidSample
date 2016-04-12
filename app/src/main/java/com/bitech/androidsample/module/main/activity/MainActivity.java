package com.bitech.androidsample.module.main.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.bitech.androidsample.R;
import com.bitech.androidsample.annotation.ActivityInject;
import com.bitech.androidsample.base.BaseActivity;
import com.bitech.androidsample.module.main.adapter.MainRecyclerAdapter;
import com.bitech.androidsample.module.main.presenter.LoginPresener;
import com.bitech.androidsample.module.main.view.ILoginView;
import com.bitech.androidsample.utils.Rxbus;
import com.bitech.androidsample.widgets.AutoLoadMoreRecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

@ActivityInject(contentViewId = R.layout.activity_main, isSlidr = false,
        toolBarIndicator = R.drawable.ic_android)
public class MainActivity extends BaseActivity implements ILoginView {

    @Inject
    LoginPresener loginPresener;

    @Bind(R.id.content_main_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.content_main_recyclerview)
    AutoLoadMoreRecyclerView recyclerView;

    private MainRecyclerAdapter adapter;

    @Override
    protected void initView() {

        initInject();
        initData();

    }

    private void initInject() {
        getActivityComponent().inject(this);
        loginPresener.attacthView(this);//与Activity关联
    }

    private void initData(){

        List<String> datas=new ArrayList<>();
        for(int i=0;i<20;i++){
            datas.add("recycler测试");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());//默认的动画

        adapter=new MainRecyclerAdapter(this,datas);
        recyclerView.setAdapter(adapter);

        //加载更多监听
        recyclerView.setOnLoadMoreListener(new AutoLoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                logger.i("---------加载更多数据-----------");
                Toast.makeText(MainActivity.this,"加载更多",Toast.LENGTH_LONG).show();
            }
        });
        //刷新监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }
    //登录成功与失败的判断
    @Override
    public void login(boolean isSuccess) {


    }

}
