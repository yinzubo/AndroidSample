package com.bitech.androidsample.module.main.presenter;

import com.bitech.androidsample.base.BasePresenterImpl;
import com.bitech.androidsample.bean.User;
import com.bitech.androidsample.module.main.model.LoginInterceptorImpl;
import com.bitech.androidsample.module.main.view.ILoginView;

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
public class LoginPresenerImpl extends BasePresenterImpl<ILoginView, User>  {

    public LoginPresenerImpl(ILoginView view) {
        super(view);
    }

    public void login(String name, String password) {
        LoginInterceptorImpl loginInterceptor = new LoginInterceptorImpl();
        subscription = loginInterceptor.login(name, password, this);
    }

    @Override
    public void requestSucess(User data) {
        super.requestSucess(data);
        baseView.login(true);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        baseView.login(false);
    }
}
