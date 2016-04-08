package com.bitech.androidsample.module.main.presenter;

import com.bitech.androidsample.ActivityScope;
import com.bitech.androidsample.base.BasePresenter;
import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.callback.RequestCallback;
import com.bitech.androidsample.http.service.Service;
import com.bitech.androidsample.module.main.model.LoginModel;
import com.bitech.androidsample.module.main.view.ILoginView;

import javax.inject.Inject;

/**
 * <p>实现类
 * 保存View层的引用，当数据发生改变时，通过引用改变视图
 * 保存Model层的引用，当是视图改变时，通过引用改变数据
 * <p/>
 * 改变视图时，就在IView接口中定义方法
 * <p/>
 * 实现MVP设计模式
 * </p>
 * Created on 2016/4/6 16:36.
 *
 * @author Lucy
 */
@ActivityScope
public class LoginPresener extends BasePresenter<ILoginView, User> {

    private LoginModel loginInterceptor;
    private Service apiService;//http访问接口

    @Inject
    public LoginPresener(Service apiService) {
        this.apiService=apiService;
        loginInterceptor=new LoginModel(apiService);
    }
    //登录
    public void login(String name, String password) {

        loginInterceptor.login(name, password, new RequestCallback<User>());
    }


}
